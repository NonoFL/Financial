package com.example.financial.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.financial.R;
import com.example.financial.db.MyDBHelper;
import com.example.financial.view.MainActivity;

public class NewPayActivity extends AppCompatActivity {

    EditText et_money, et_payer, et_time, et_remark;
    Spinner sp_type;
    Button bt_save, bt_cancel;
    MyDBHelper myDBHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pay);
        initView();
        btnSave();
        btnCancel();

    }

    private void btnSave() {
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("outmoney", et_money.getText().toString());
                values.put("outtime", et_time.getText().toString());
                values.put("outtype", sp_type.getSelectedItem().toString());
                values.put("outpayee", et_payer.getText().toString());
                values.put("outremark", et_remark.getText().toString());
                db.insert("pay_out", null, values);
                Toast.makeText(NewPayActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewPayActivity.this, NewPayActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void btnCancel() {
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewPayActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void initView(){
        et_money = findViewById(R.id.et_money_newout);
        et_payer = findViewById(R.id.et_payer_out);
        et_time = findViewById(R.id.et_time_newout);
        et_remark = findViewById(R.id.et_remark_out);
        sp_type = findViewById(R.id.sp_type_newout);
        bt_cancel = findViewById(R.id.bt_cancel_newout);
        bt_save = findViewById(R.id.bt_save_newout);
        myDBHelper = new MyDBHelper(NewPayActivity.this);
        db = myDBHelper.getWritableDatabase();
    }
}