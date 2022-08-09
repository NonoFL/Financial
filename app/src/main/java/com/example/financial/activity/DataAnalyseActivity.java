package com.example.financial.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import com.example.financial.R;
import com.example.financial.db.MyDBHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class DataAnalyseActivity extends AppCompatActivity {
    LineChart income_chart, outpay_chart;
    MyDBHelper myDBHelper;
    SQLiteDatabase db;
    String[] indata = {"学习 - 奖金", "比赛 - 奖励", "业余 - 兼职", "基本 - 工资", "福利 - 分红", "加班 - 津贴", "其他"};
    String[] outdata = {"电影 - 娱乐", "美食 - 畅饮", "欢乐 - 购物", "手机 - 充值", "交通 - 出行", "教育 - 培训", "社交 - 礼仪", "生活 - 日用", "其他"};
    int[] indataMoney = new int[indata.length];
    int[] outdataPay = new int[outdata.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analyse);

        initView();
        inComeData();
        outComeData();
    }
    private void initView() {
        income_chart = findViewById(R.id.income_chart_data);
        outpay_chart = findViewById(R.id.outpay_chart_data);
        myDBHelper = new MyDBHelper(DataAnalyseActivity.this);
        db = myDBHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    private void inComeData() {
        Cursor cursor = db.rawQuery("select * from in_come", null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") Double mymoney = cursor.getDouble(cursor.getColumnIndex("inmoney"));
            String mytype = cursor.getString(cursor.getColumnIndex("intype"));
            for(int i = 0; i < indata.length; i++){
                if(mytype.equals(indata[i])){
                    indataMoney[i] += mymoney;
                    break;
                }
            }
        }

        XAxis xAxis = income_chart.getXAxis();          // 获取图标的x轴轴线
        YAxis yAxisLeft = income_chart.getAxisLeft();   // 获取图标的y侧轴线
        YAxis yAxisRight = income_chart.getAxisRight(); // 获取图标的y侧轴线
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  //  设置x轴线的位置为底部
        yAxisLeft.setAxisMinimum(0f);       //保证Y轴从0开始
        yAxisRight.setAxisMinimum(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return indata[(int) v];
            }
        });

        List<Entry> inentries = new ArrayList<>();
        for(int i = 0; i < indata.length; i++){
            inentries.add(new Entry(i, indataMoney[i]));
        }

        LineDataSet lineDataSet = new LineDataSet(inentries, "金额");
        lineDataSet.setValueTextSize(25);
        lineDataSet.setValueTextColor(Color.WHITE);
        lineDataSet.setDrawFilled(true);    // 设置折线图填充

        LineData data = new LineData(lineDataSet);
        income_chart.setData(data);
    }

    private void outComeData() {
        Cursor cursor = db.rawQuery("select * from pay_out", null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") Double mymoney = cursor.getDouble(cursor.getColumnIndex("outmoney"));
            @SuppressLint("Range") String mytype = cursor.getString(cursor.getColumnIndex("outtype"));

            for(int i = 0; i < outdata.length; i++){
                if(mytype.equals(outdata[i])){
                    outdataPay[i] += mymoney;
                    break;
                }
            }
        }
        XAxis xAxis = income_chart.getXAxis();          // 获取图标的x轴轴线
        YAxis yAxisLeft = income_chart.getAxisLeft();   // 获取图标的y侧轴线
        YAxis yAxisRight = income_chart.getAxisRight(); // 获取图标的y侧轴线
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  //  设置x轴线的位置为底部
        yAxisLeft.setAxisMinimum(0f);       //保证Y轴从0开始
        yAxisRight.setAxisMinimum(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return outdata[(int) v];
            }
        });

        List<Entry> inentries = new ArrayList<>();
        for(int i = 0; i < outdata.length; i++){
            inentries.add(new Entry(i, outdataPay[i]));
        }

        LineDataSet lineDataSet = new LineDataSet(inentries, "金额");
        lineDataSet.setValueTextSize(25);
        lineDataSet.setValueTextColor(Color.WHITE);
        lineDataSet.setDrawFilled(true);    // 设置折线图填充

        LineData data = new LineData(lineDataSet);
        outpay_chart.setData(data);


    }


}