package com.example.evening1023.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ExpandableListView;

import com.example.evening1023.R;
import com.example.evening1023.bean.NewsFenleiYou;
import com.example.evening1023.bean.NewsFenleiZuo;
import com.example.evening1023.di.component.DaggerMainComponent;
import com.example.evening1023.di.module.MainModule;
import com.example.evening1023.mvp.contract.MainContract;
import com.example.evening1023.mvp.presenter.MainPresenter;
import com.example.evening1023.mvp.ui.activity.adapter.YouAdapter;
import com.example.evening1023.mvp.ui.activity.adapter.ZuoAdapter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.recy_zuo)
    RecyclerView recyZuo;
    @BindView(R.id.ex_listview)
    ExpandableListView exListview;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.zuo();
        mPresenter.you();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    //左
    @Override
    public void ZuoData(NewsFenleiZuo newsFenleiZuo) {
        List<NewsFenleiZuo.DataBean> data = newsFenleiZuo.getData();
        // Toast.makeText(this, "data:" + data, Toast.LENGTH_SHORT).show();
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyZuo.setLayoutManager(manager);
        ZuoAdapter adapter = new ZuoAdapter(MainActivity.this, data);
        recyZuo.setAdapter(adapter);
    }

    //右
    @Override
    public void YouData(NewsFenleiYou newsFenleiYou) {
        List<NewsFenleiYou.DataBean> data = newsFenleiYou.getData();
        //Toast.makeText(this, "data:" + data, Toast.LENGTH_SHORT).show();
        YouAdapter adapter = new YouAdapter(MainActivity.this,newsFenleiYou);
        exListview.setAdapter(adapter);
        //默认展开
        int count = exListview.getCount();
        for (int i = 0; i < count; i++) {
            exListview.expandGroup(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
