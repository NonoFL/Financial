package com.example.financial.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.financial.R;
import com.example.financial.activity.DataAnalyseActivity;
import com.example.financial.activity.InComeDetailActivity;
import com.example.financial.activity.NewInComeActivity;
import com.example.financial.activity.NewPayActivity;
import com.example.financial.activity.PayDetailActivity;
import com.example.financial.activity.SysSettingActivity;

public class MainActivity extends AppCompatActivity {
    Button bt_newincom, bt_incomedetail, bt_newpay, bt_paytail, bt_dataanalyse, bt_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        btnOnClick();
    }

    private void btnOnClick() {
        bt_newincom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewInComeActivity.class);
                startActivity(intent);
            }
        });
        bt_incomedetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InComeDetailActivity.class);
                startActivity(intent);
            }
        });
        bt_newpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewPayActivity.class);
                startActivity(intent);
            }
        });
        bt_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SysSettingActivity.class);
                startActivity(intent);
            }
        });
        bt_paytail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PayDetailActivity.class);
                startActivity(intent);
            }
        });
        bt_dataanalyse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DataAnalyseActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        bt_newincom = findViewById(R.id.bt_newincome_main);
        bt_incomedetail = findViewById(R.id.bt_incomedetail_main);
        bt_paytail = findViewById(R.id.bt_paydetail_main);
        bt_newpay = findViewById(R.id.bt_newpay_main);
        bt_dataanalyse = findViewById(R.id.bt_dataanalyse_main);
        bt_setting = findViewById(R.id.bt_syssetting_main);
    }

}