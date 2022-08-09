package com.example.financial.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.financial.R;
import com.example.financial.db.MyDBHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText et_name, et_pwd, et_email, et_phone;
    Button btn_register, btn_cancel;
    MyDBHelper myDBHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

        btnRegister();
        btnCancel();

    }

    private void btnCancel() {
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void btnRegister() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("name", et_name.getText().toString());
                values.put("email", et_email.getText().toString());
                values.put("pwd", et_pwd.getText().toString());
                values.put("phone", et_phone.getText().toString());
                db.insert("tb_userinfo", null, values);
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initView(){
        et_name = findViewById(R.id.et_name_rg);
        et_pwd = findViewById(R.id.et_pwd_rg);
        et_email = findViewById(R.id.et_email_rg);
        et_phone = findViewById(R.id.et_phone_rg);
        btn_register = findViewById(R.id.bt_ok_rg);
        btn_cancel = findViewById(R.id.bt_cancel_rg);
        myDBHelper = new MyDBHelper(RegisterActivity.this);
        db = myDBHelper.getWritableDatabase();
    }


}