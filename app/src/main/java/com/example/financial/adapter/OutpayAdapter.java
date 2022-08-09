package com.example.financial.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financial.R;
import com.example.financial.bean.OutpayBean;
import com.example.financial.other.OutManageActivity;

import java.util.List;

public class OutpayAdapter extends RecyclerView.Adapter<OutpayAdapter.ViewHolder> {
    Context mycontext;
    List<OutpayBean> arr2;

    public OutpayAdapter(Context mycontext, List<OutpayBean> arr2) {
        this.mycontext = mycontext;
        this.arr2 = arr2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mycontext).inflate(R.layout.recy_item_out, parent, false);
        ViewHolder mholder = new ViewHolder(view);
        return mholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OutpayBean outpayBean = arr2.get(position);
        holder.item_payee.setText("付款 - 给" + outpayBean.getPayee());
        holder.item_type.setText(outpayBean.getType());
        holder.item_time.setText(outpayBean.getTime());
        holder.item_remark.setText(outpayBean.getRemark());
        holder.item_money.setText(" - " + outpayBean.getMoney());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mycontext, OutManageActivity.class);
                intent.putExtra("sero", outpayBean);
                mycontext.startActivity(intent);
                ((Activity)mycontext).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arr2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_payee, item_type, item_time, item_remark, item_money;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_payee = itemView.findViewById(R.id.item_pay_out);
            item_type = itemView.findViewById(R.id.item_type_out);
            item_time = itemView.findViewById(R.id.item_time_out);
            item_remark = itemView.findViewById(R.id.item_remark_out);
            item_money = itemView.findViewById(R.id.item_money_out);
        }
    }
}
