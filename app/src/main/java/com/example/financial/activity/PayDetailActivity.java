package com.example.financial.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.example.financial.R;
import com.example.financial.adapter.OutpayAdapter;
import com.example.financial.bean.IncomeBean;
import com.example.financial.bean.OutpayBean;
import com.example.financial.db.MyDBHelper;

import java.util.ArrayList;
import java.util.List;

public class PayDetailActivity extends AppCompatActivity {
    RecyclerView recy_view;
    MyDBHelper myDBHelper;
    SQLiteDatabase db;
    List<OutpayBean> arr1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_detail);

        initView();
        initData();

        OutpayAdapter outpayAdapter = new OutpayAdapter(PayDetailActivity.this, arr1);
        StaggeredGridLayoutManager st = new StaggeredGridLayoutManager(StaggeredGridLayoutManager.VERTICAL, 1);
        recy_view.setLayoutManager(st);
        recy_view.setAdapter(outpayAdapter);
    }

    private void initView() {
        recy_view = findViewById(R.id.recy_view_outdetail);
        myDBHelper = new MyDBHelper(PayDetailActivity.this);
        db = myDBHelper.getWritableDatabase();
    }

    private void initData() {
        Cursor cursor = db.rawQuery("select * from pay_out", null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") int myId = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") double myMoney = cursor.getDouble(cursor.getColumnIndex("outmoney"));
            @SuppressLint("Range") String myType = cursor.getString(cursor.getColumnIndex("outtype"));
            @SuppressLint("Range") String myTime = cursor.getString(cursor.getColumnIndex("outtime"));
            @SuppressLint("Range") String myPayer = cursor.getString(cursor.getColumnIndex("outpayee"));
            @SuppressLint("Range") String myRemark = cursor.getString(cursor.getColumnIndex("outremark"));
            OutpayBean outpayBean = new OutpayBean(myId, myTime,myType, myPayer,myRemark, myMoney);
            arr1.add(outpayBean);
        }
        cursor.close();
    }
}