package com.example.evening1023.mvp.model.api.service;

import com.example.evening1023.bean.NewsFenleiYou;
import com.example.evening1023.bean.NewsFenleiZuo;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    //左分类
    @GET("product/getCatagory")
    Observable<NewsFenleiZuo> zuo();
    //右分类
    @GET("product/getProductCatagory")
    Observable<NewsFenleiYou> you();
}
