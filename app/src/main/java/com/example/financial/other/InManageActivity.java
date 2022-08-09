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
import com.example.financial.activity.InComeDetailActivity;
import com.example.financial.bean.IncomeBean;
import com.example.financial.db.MyDBHelper;

public class InManageActivity extends AppCompatActivity {
    private EditText et_money, et_time, et_payer, et_remark;
    private Spinner sp_type;
    private Button bt_modify, bt_delete;
    private MyDBHelper myDBHelper;
    private SQLiteDatabase db;
    private IncomeBean incomeBean;
    String[] indata = {"学习 - 奖金", "比赛 - 奖励", "业余 - 兼职", "基本 - 工资", "福利 - 分红", "加班 - 津贴", "其他"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_manage);

        initView();
        getDataDisplay();
        btnModify();
        btnDelete();
    }

    private void initView() {
        et_money = findViewById(R.id.et_money_inmag);
        et_time = findViewById(R.id.et_time_inmag);
        et_payer = findViewById(R.id.et_payer_inmag);
        et_remark = findViewById(R.id.et_remark_inmag);
        sp_type = findViewById(R.id.sp_type_inmag);
        bt_modify = findViewById(R.id.bt_modify_inmag);
        bt_delete = findViewById(R.id.bt_delete_inmag);
        myDBHelper = new MyDBHelper(InManageActivity.this);
        db = myDBHelper.getWritableDatabase();
    }

    private void getDataDisplay() {
        incomeBean = (IncomeBean) getIntent().getSerializableExtra("seri");
        et_money.setText(incomeBean.getMoney() + "");
        et_time.setText(incomeBean.getTime());
        boolean isFind = false;
        for(int i = 0; i < indata.length; i++){
            if(incomeBean.getType().equals(indata[i])){
                sp_type.setSelection(i + 1);
                isFind = true;
                break;
            }
        }
        if(!isFind){
            sp_type.setSelection(0);
        }
        et_payer.setText(incomeBean.getPayer());
        et_remark.setText(incomeBean.getRemark());
    }

    private void btnModify() {
        bt_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("inmoney", et_money.getText().toString());
                values.put("intime", et_time.getText().toString());
                values.put("intype", sp_type.getSelectedItem().toString());
                values.put("inpayer", et_payer.getText().toString());
                values.put("inremark", et_remark.getText().toString());

                db.update("in_come", values, "id=?", new String[]{incomeBean.getId() + ""});
                Toast.makeText(InManageActivity.this, "修改成功", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(InManageActivity.this, InComeDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void btnDelete() {
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete("in_come", "id=?", new String[]{incomeBean.getId() + ""});
                Toast.makeText(InManageActivity.this, "删除成功", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(InManageActivity.this, InComeDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}