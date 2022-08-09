package com.example.financial.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.financial.R;
import com.example.financial.activity.InComeDetailActivity;
import com.example.financial.bean.IncomeBean;
import com.example.financial.other.InManageActivity;

import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.ViewHolder> {

    Context mcontext;
    List<IncomeBean> arr2;

    public IncomeAdapter(InComeDetailActivity inComeDetailActivity, List<IncomeBean> arr1) {
        this.mcontext = inComeDetailActivity;
        this.arr2 = arr1;
    }

    @Override
    public IncomeAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.recy_item_in, viewGroup, false);
        ViewHolder mholder = new ViewHolder(view);
        return mholder;
    }

    @Override
    public void onBindViewHolder(IncomeAdapter.ViewHolder viewHolder, int i) {
        final IncomeBean incomeBean = arr2.get(i);
//        IncomeBean incomeBean = arr2.get(i);
        viewHolder.item_payer.setText("收款 - 来自" + incomeBean.getPayer());
        viewHolder.item_type.setText(incomeBean.getType());
        viewHolder.item_time.setText(incomeBean.getTime());
        viewHolder.item_remark.setText(incomeBean.getRemark());
        viewHolder.item_money.setText(" + " + incomeBean.getMoney());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, InManageActivity.class);
                intent.putExtra("seri", incomeBean);
                mcontext.startActivity(intent);
                ((Activity) mcontext).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_payer, item_type, item_time, item_remark, item_money;
        public ViewHolder(View itemView) {
            super(itemView);
            item_payer = itemView.findViewById(R.id.item_pay_in);
            item_type = itemView.findViewById(R.id.item_type_in);
            item_time = itemView.findViewById(R.id.item_time_in);
            item_remark = itemView.findViewById(R.id.item_remark_in);
            item_money = itemView.findViewById(R.id.item_money_in);
        }
    }
}