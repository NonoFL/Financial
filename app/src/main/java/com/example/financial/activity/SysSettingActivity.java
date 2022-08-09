package com.example.financial.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.financial.R;
import com.example.financial.bean.IncomeBean;
import com.example.financial.db.MyDBHelper;
import com.example.financial.view.LoginActivity;
import com.example.financial.view.MainActivity;

public class SysSettingActivity extends AppCompatActivity {

    private TextView txt_user;
    private EditText et_ypwd, et_xpwd, et_zpwd;
    private Button bt_modify, bt_cancel;
    private MyDBHelper myDBHelper;
    private SQLiteDatabase db;
    private String name;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sys_setting);

        initView();
        displayInfo();
        btnModify();
        btnCancel();

    }

    private void initView() {
        txt_user = findViewById(R.id.txt_name_sys);
        et_xpwd = findViewById(R.id.et_xpwd_sys);
        et_ypwd = findViewById(R.id.et_ypwd_sys);
        et_zpwd = findViewById(R.id.et_zpwd_sys);
        bt_modify = findViewById(R.id.bt_modify_sys);
        bt_cancel = findViewById(R.id.bt_cancel_sys);
        myDBHelper = new MyDBHelper(SysSettingActivity.this);
        db = myDBHelper.getWritableDatabase();
    }

    private void displayInfo() {

        name = getSharedPreferences("userinfo", 0).getString("username", "");
        pwd = getSharedPreferences("userinfo", 0).getString("userpwd", "");
        txt_user.setText(name);
    }

    private void btnModify() {
        bt_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ypwd = et_ypwd.getText().toString();
                String xpwd = et_xpwd.getText().toString();
                String zpwd = et_zpwd.getText().toString();

                if(ypwd.equals("")){
                    Toast.makeText(SysSettingActivity.this, "请输入原始密码", Toast.LENGTH_SHORT).show();
                }else if(!ypwd.equals(pwd)){
                    Toast.makeText(SysSettingActivity.this, "原始密码错误", Toast.LENGTH_SHORT).show();
                }else if(xpwd.equals("")){
                    Toast.makeText(SysSettingActivity.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                }else if(ypwd.equals(xpwd)){
                    Toast.makeText(SysSettingActivity.this, "所输入的新密码和原密码不能相同", Toast.LENGTH_SHORT).show();
                }else if(zpwd.equals("")){
                    Toast.makeText(SysSettingActivity.this, "请再次输入新密码", Toast.LENGTH_SHORT).show();
                }else if(!xpwd.equals(zpwd)){
                    Toast.makeText(SysSettingActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                }else{
                    ContentValues values = new ContentValues();
                    values.put("pwd", xpwd);
                    db.update("tb_userinfo", values, "name=?", new String[]{name});
                    Toast.makeText(SysSettingActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SysSettingActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void btnCancel() {
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SysSettingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}