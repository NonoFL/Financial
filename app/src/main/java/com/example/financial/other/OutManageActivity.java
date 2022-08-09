package com.example.financial.other;

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
import com.example.financial.activity.PayDetailActivity;
import com.example.financial.bean.OutpayBean;
import com.example.financial.db.MyDBHelper;

public class OutManageActivity extends AppCompatActivity {
    private EditText et_money, et_payee, et_time, et_remark;
    private Spinner sp_type;
    private Button bt_modify, bt_delete;
    private MyDBHelper myDBHelper;
    private SQLiteDatabase db;
    private OutpayBean outpayBean;
    String[] outdata = {"电影 - 娱乐", "美食 - 畅饮", "欢乐 - 购物", "手机 - 充值", "交通 - 出行", "教育 - 培训", "社交 - 礼仪", "生活 - 日用", "其他"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_manage);

        initView();
        getDataDisplay();
        btnModify();
        btnDelete();

    }

    private void initView() {
        et_money = findViewById(R.id.et_money_outmag);
        et_time = findViewById(R.id.et_time_outmag);
        et_payee = findViewById(R.id.et_payer_outmag);
        et_remark = findViewById(R.id.et_remark_outmag);
        sp_type = findViewById(R.id.sp_type_outmag);
        bt_modify = findViewById(R.id.bt_modify_outmag);
        bt_delete = findViewById(R.id.bt_delete_outmag);
        myDBHelper = new MyDBHelper(OutManageActivity.this);
        db = myDBHelper.getWritableDatabase();
    }

    private void getDataDisplay() {
        outpayBean = (OutpayBean) getIntent().getSerializableExtra("sero");
        et_money.setText(outpayBean.getMoney() + "");
        et_time.setText(outpayBean.getTime());
        boolean isFind = false;
        for(int i = 0; i < outdata.length; i++){
            if(outpayBean.getType().toString().equals(outdata[i])){
                sp_type.setSelection(i + 1);
                isFind = true;
                break;
            }
        }
        if(!isFind){
            sp_type.setSelection(0);
        }
        et_payee.setText(outpayBean.getPayee().toString());
        et_remark.setText(outpayBean.getRemark().toString());
    }

    private void btnModify() {
        bt_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("outmoney", et_money.getText().toString());
                values.put("outtime", et_time.getText().toString());
                values.put("outtype", sp_type.getSelectedItem().toString());
                values.put("outpayee", et_payee.getText().toString());
                values.put("outremark", et_remark.getText().toString());
                db.update("pay_out", values, "id=?", new String[]{outpayBean.getId() + ""});
                Toast.makeText(OutManageActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OutManageActivity.this, PayDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void btnDelete() {
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete("pay_out", "id=?", new String[]{outpayBean.getId() + ""});
                Toast.makeText(OutManageActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OutManageActivity.this, PayDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}