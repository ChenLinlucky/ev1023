package com.example.evening1023.mvp.ui.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evening1023.R;
import com.example.evening1023.bean.NewsFenleiZuo;

import java.util.List;

public class ZuoAdapter extends RecyclerView.Adapter<ZuoAdapter.zuoHolder>{
    private Context context;
    private List<NewsFenleiZuo.DataBean> list;
    public ZuoAdapter(Context context, List<NewsFenleiZuo.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public zuoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.zuo_item, null);
        zuoHolder holder = new zuoHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull zuoHolder holder, int position) {
        holder.zuoname.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class zuoHolder extends RecyclerView.ViewHolder{

        private final TextView zuoname;

        public zuoHolder(View itemView) {
            super(itemView);
            zuoname = itemView.findViewById(R.id.zuo_name);
        }
    }
}
