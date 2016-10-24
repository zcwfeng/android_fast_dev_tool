package com.zcwfeng.fastdev.ui.fragment.request.demo2;

import com.zcwfeng.fastdev.ui.fragment.request.lib.BaseResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MyApiService {
    /**
     *普通写法
     */
    @GET("service/getIpInfo.php")
    Observable<BaseResponse<IpResult>> getData(@Query("ip") String ip);

    @GET("app.php?m=souguapp&c=appusers&a=network")
    Observable<SouguBean> getSougu();
}