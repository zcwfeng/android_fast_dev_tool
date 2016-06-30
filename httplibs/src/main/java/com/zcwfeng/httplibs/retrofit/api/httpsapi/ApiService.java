package com.zcwfeng.httplibs.retrofit.api.httpsapi;

import com.zcwfeng.httplibs.retrofit.api.response.GetIpInfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("service/getIpInfo.php")
    Call<GetIpInfoResponse> getIpInfo(@Query("ip") String ip);
}