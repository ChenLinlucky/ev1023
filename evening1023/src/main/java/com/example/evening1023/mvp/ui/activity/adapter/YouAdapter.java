package com.example.evening1023.mvp.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.evening1023.R;
import com.example.evening1023.bean.NewsFenleiYou;

import java.util.ArrayList;

public class YouAdapter extends BaseExpandableListAdapter{

    private Context context;
    private NewsFenleiYou fenleiYou;
    public YouAdapter(Context context, NewsFenleiYou fenleiYou) {
        this.context = context;
        this.fenleiYou = fenleiYou;
    }


    @Override
    public int getGroupCount() {
        return fenleiYou.getData().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return fenleiYou.getData().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return fenleiYou.getData().get(groupPosition).getList().get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //条目名
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.you_item, null);
        TextView youname = view.findViewById(R.id.you_name);
        youname.setText(fenleiYou.getData().get(groupPosition).getName());
        return view;
    }

    //商品图片，商品名
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.you_recy, null);
        RecyclerView you_recy = view.findViewById(R.id.you_recy);
        GridLayoutManager manager = new GridLayoutManager(context, 3);
        you_recy.setLayoutManager(manager);

        YouShopAdapter adapter = new YouShopAdapter(context,(ArrayList<NewsFenleiYou.DataBean>)fenleiYou.getData());
        you_recy.setAdapter(adapter);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
