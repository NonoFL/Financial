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

public class NewInComeActivity extends AppCompatActivity {

    private EditText et_money, et_time, et_payer, et_remark;
    private Spinner sp_type;
    private Button bt_save, bt_cancel;
    private MyDBHelper myDBHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_in_come);

        initView();
        btnSave();
        btnCancel();
    }



    private void initView() {
        et_money = findViewById(R.id.et_money_newin);
        et_time = findViewById(R.id.et_time_newin);
        et_payer = findViewById(R.id.et_payer_in);
        et_remark = findViewById(R.id.et_remark_in);
        sp_type = findViewById(R.id.sp_type_newin);
        bt_save = findViewById(R.id.bt_save_newin);
        bt_cancel = findViewById(R.id.bt_cancel_newin);
        myDBHelper = new MyDBHelper(NewInComeActivity.this);
        db = myDBHelper.getWritableDatabase();
    }

    private void btnSave() {
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("inmoney", et_money.getText().toString());
                values.put("intime", et_time.getText().toString());
                values.put("intype", sp_type.getSelectedItem().toString());
                values.put("inpayer", et_payer.getText().toString());
                values.put("inremark", et_remark.getText().toString());
                db.insert("in_come", null, values);
                Toast.makeText(NewInComeActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewInComeActivity.this, NewInComeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void btnCancel() {
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewInComeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}