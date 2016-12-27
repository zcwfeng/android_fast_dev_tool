package com.zcwfeng.fastdev.ui.fragment.request.demo2;


import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by zcw on 2016/12/27.
 */

public interface SkyService {

    /**
     *普通写法
     */
    @GET("data/sk/101010100.html")
    Observable<WeatherDemo> getSkyDemo();
}
