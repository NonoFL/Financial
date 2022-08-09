package com.example.financial.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.financial.R;
import com.example.financial.adapter.IncomeAdapter;
import com.example.financial.bean.IncomeBean;
import com.example.financial.db.MyDBHelper;

import java.util.ArrayList;
import java.util.List;

public class InComeDetailActivity extends AppCompatActivity {

    RecyclerView recyView;
    MyDBHelper myDBHelper;
    SQLiteDatabase db;
    List<IncomeBean> arr1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_come_detail);
        initView();
        initData();
        IncomeAdapter adapter = new IncomeAdapter(InComeDetailActivity.this, arr1);
        StaggeredGridLayoutManager st = new StaggeredGridLayoutManager(StaggeredGridLayoutManager.VERTICAL, 1);
        recyView.setLayoutManager(st);
        recyView.setAdapter(adapter);
    }

    private void initView() {
        recyView = findViewById(R.id.recy_view_detail);
        myDBHelper = new MyDBHelper(InComeDetailActivity.this);
        db = myDBHelper.getWritableDatabase();
    }

    private void initData() {
        Cursor cursor = db.rawQuery("select * from in_come", null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") int myId = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") double myMoney = cursor.getDouble(cursor.getColumnIndex("inmoney"));
            @SuppressLint("Range") String myType = cursor.getString(cursor.getColumnIndex("intype"));
            @SuppressLint("Range") String myTime = cursor.getString(cursor.getColumnIndex("intime"));
            @SuppressLint("Range") String myPayer = cursor.getString(cursor.getColumnIndex("inpayer"));
            @SuppressLint("Range") String myRemark = cursor.getString(cursor.getColumnIndex("inremark"));
            IncomeBean incomeBean = new IncomeBean(myId, myMoney, myTime, myType, myPayer, myRemark);
            arr1.add(incomeBean);
        }
        cursor.close();
    }
}