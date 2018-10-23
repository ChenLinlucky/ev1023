package com.example.evening1023.mvp.ui.activity.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evening1023.R;
import com.example.evening1023.bean.NewsFenleiYou;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

public class YouShopAdapter extends RecyclerView.Adapter<YouShopAdapter.shopHolder>{
    private Context context;
    private ArrayList<NewsFenleiYou.DataBean> fenleiYou;
    public YouShopAdapter(Context context, ArrayList<NewsFenleiYou.DataBean> fenleiYou) {
        this.context = context;
        this.fenleiYou = fenleiYou;
    }

    @NonNull
    @Override
    public shopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.you_item_shop, null);
        shopHolder holder = new shopHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull shopHolder holder, int position) {
       holder.shopname.setText(fenleiYou.get(position).getName());
        String[] split = fenleiYou.get(position).getList().get(position).getIcon().split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.simp.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return fenleiYou.size();
    }

    class shopHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView simp;
        private final TextView shopname;

        public shopHolder(View itemView) {
            super(itemView);
            simp = itemView.findViewById(R.id.you_simp);
            shopname = itemView.findViewById(R.id.you_shop_name);
        }
    }
}
