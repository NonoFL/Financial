package com.example.financial.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.financial.R;
import com.example.financial.db.MyDBHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText et_name, et_pwd;
    private Button btn_newregister, btn_login;
    private MyDBHelper myDBHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        btn_login();
        btnNewRegister();
    }

    private void btnNewRegister() {
        btn_newregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void btn_login() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputName = et_name.getText().toString();
                String inputpwd = et_pwd.getText().toString();

                if(inputName.equals("") || inputpwd.equals("")){
                    Toast.makeText(LoginActivity.this, "用户或密码不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    Cursor cursor = db.rawQuery("select * from tb_userinfo where name = ? and pwd = ?", new String[]{inputName, inputpwd});
                    if(cursor.moveToNext()){
                        @SuppressLint("Range") String getName = cursor.getString(cursor.getColumnIndex("name"));
                        @SuppressLint("Range") String getpwd = cursor.getString(cursor.getColumnIndex("pwd"));
                        if(inputName.equalsIgnoreCase(getName) && inputpwd.equalsIgnoreCase(getpwd)){
                            SharedPreferences.Editor editor = getSharedPreferences("userinfo", 0).edit();
                            editor.putString("username", inputName);
                            editor.putString("userpwd", inputpwd);
                            editor.commit();
                            Toast.makeText(LoginActivity.this, "欢迎登录", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "用户名或密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                        et_name.setText("");
                        et_pwd.setText("");
                    }
                }
            }
        });
    }

    private void initView() {
        et_name = findViewById(R.id.et_name_lg);
        et_pwd = findViewById(R.id.et_pwd_lg);
        btn_newregister = findViewById(R.id.bt_newregister_lg);
        btn_login = findViewById(R.id.bt_login_lg);
        myDBHelper = new MyDBHelper(LoginActivity.this);
        db = myDBHelper.getWritableDatabase();
    }
}