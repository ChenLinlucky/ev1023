package com.example.evening1023.mvp.model;

import android.app.Application;

import com.example.evening1023.bean.NewsFenleiYou;
import com.example.evening1023.bean.NewsFenleiZuo;
import com.example.evening1023.mvp.model.api.service.ApiService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.example.evening1023.mvp.contract.MainContract;

import io.reactivex.Observable;


@ActivityScope
public class MainModel extends BaseModel implements MainContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public MainModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<NewsFenleiZuo> requestZuo() {
        Observable<NewsFenleiZuo> zuo = mRepositoryManager.obtainRetrofitService(ApiService.class).zuo();
        return zuo;
    }

    @Override
    public Observable<NewsFenleiYou> requestYou() {
        Observable<NewsFenleiYou> you = mRepositoryManager.obtainRetrofitService(ApiService.class).you();
        return you;
    }
}