package com.example.evening1023.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;

import com.example.evening1023.bean.NewsFenleiYou;
import com.example.evening1023.bean.NewsFenleiZuo;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.example.evening1023.mvp.contract.MainContract;


@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
    //左
    @SuppressLint("CheckResult")
    public void zuo(){
        Observable<NewsFenleiZuo> newsFenleiZuoObservable = mModel.requestZuo();
        newsFenleiZuoObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsFenleiZuo>() {
                    @Override
                    public void accept(NewsFenleiZuo newsFenleiZuo) throws Exception {
                        mRootView.ZuoData(newsFenleiZuo);
                    }
                });
    }
    //右
    @SuppressLint("CheckResult")
    public void you(){
        mModel.requestYou().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsFenleiYou>() {
                    @Override
                    public void accept(NewsFenleiYou newsFenleiYou) throws Exception {
                        mRootView.YouData(newsFenleiYou);
                    }
                });
    }
}
