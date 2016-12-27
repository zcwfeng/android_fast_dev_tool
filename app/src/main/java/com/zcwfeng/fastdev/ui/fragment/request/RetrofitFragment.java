package com.zcwfeng.fastdev.ui.fragment.request;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;
import com.zcwfeng.fastdev.ui.fragment.request.demo.Contributor;
import com.zcwfeng.fastdev.ui.fragment.request.demo.GitHubClient;
import com.zcwfeng.fastdev.ui.fragment.request.demo.ServiceGenerator;
import com.zcwfeng.fastdev.ui.fragment.request.demo2.IpResult;
import com.zcwfeng.fastdev.ui.fragment.request.demo2.MyApiService;
import com.zcwfeng.fastdev.ui.fragment.request.demo2.SkyService;
import com.zcwfeng.fastdev.ui.fragment.request.demo2.SouguBean;
import com.zcwfeng.fastdev.ui.fragment.request.demo2.WeatherDemo;
import com.zcwfeng.fastdev.ui.fragment.request.lib.BaseResponse;
import com.zcwfeng.fastdev.ui.fragment.request.lib.BaseSubscriber;
import com.zcwfeng.fastdev.ui.fragment.request.lib.CallBack;
import com.zcwfeng.fastdev.ui.fragment.request.lib.ExceptionHandle;
import com.zcwfeng.fastdev.ui.fragment.request.lib.RetrofitClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by David.zhang on 2016/10/24.
 * Description：
 */
public class RetrofitFragment extends BaseFragment {

    private View rootView;
    private View btn, btn_get, btn_post, btn_download, btn_upload, btn_myApi, btn_changeHostApi,btn_skyApi;

    String url1 = "http://img0.imgtn.bdimg.com/it/u=205441424,1768829584&fm=21&gp=0.jpg";
    String url2 = "http://wap.dl.pinyin.sogou.com/wapdl/hole/201607/05/SogouInput_android_v8.3_sweb.apk?frm=new_pcjs_index";
    String url3 = "http://apk.hiapk.com/web/api.do?qt=8051&id=723";

    String url4 = "http://www.weather.com.cn/data/sk/101010100.html";
    public static RetrofitFragment newInstance() {
        Bundle args = new Bundle();
        RetrofitFragment fragment = new RetrofitFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_retrofit, container, false);
        initView();
        initData();
        return rootView;
    }

    private void initView() {
        btn = rootView.findViewById(R.id.bt_getdata);

        btn_get = rootView.findViewById(R.id.bt_get);
        btn_post = rootView.findViewById(R.id.bt_post);
        btn_download = rootView.findViewById(R.id.bt_download);
        btn_upload = rootView.findViewById(R.id.bt_upload);
        btn_myApi = rootView.findViewById(R.id.bt_my_api);
        btn_changeHostApi = rootView.findViewById(R.id.bt_changeHostApi);
        btn_skyApi = rootView.findViewById(R.id.bt_sky_api);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //"http://ip.taobao.com/service/getIpInfo.php?ip=21.22.11.33";
                RetrofitClient.getInstance(getActivity()).createBaseApi().getData(new BaseSubscriber<IpResult>(getActivity()) {

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.e("zcw", e.code + " "+ e.message);
                        Toast.makeText(getActivity(), e.message, Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onNext(IpResult responseBody) {
                        Log.e("zcw",responseBody.toString());
                        Toast.makeText(getActivity(), responseBody.toString(), Toast.LENGTH_LONG).show();
                    }
                }, "21.22.11.33");

            }
        });


        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, String> maps = new HashMap<String, String>();

                maps.put("ip", "21.22.11.33");
                //"http://ip.taobao.com/service/getIpInfo.php?ip=21.22.11.33";
                RetrofitClient.getInstance(getActivity()).createBaseApi().get("service/getIpInfo.php"
                        , maps, new BaseSubscriber<IpResult>(getActivity()) {


                            @Override
                            public void onError(ExceptionHandle.ResponeThrowable e) {


                                Log.e("zcw", e.getMessage());
                                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onNext(IpResult responseBody) {
                                Log.e("zcw",responseBody.toString());

                                Toast.makeText(getActivity(), responseBody.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });


        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, String> maps = new HashMap<String, String>();

                maps.put("ip", "21.22.11.33");
                //"http://ip.taobao.com/service/getIpInfo.php?ip=21.22.11.33";
                RetrofitClient.getInstance(getActivity()).createBaseApi().post("service/getIpInfo.php"
                        , maps, new BaseSubscriber<ResponseBody>(getActivity()) {

                            @Override
                            public void onError(ExceptionHandle.ResponeThrowable e) {
                                Log.e("zcw", e.getMessage());
                                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onNext(ResponseBody responseBody) {
                                Log.e("zcw",responseBody.toString());

                                Toast.makeText(getActivity(), responseBody.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });


        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //// TODO: 2016-08-03 Select your file , then RetrofitClient.getInstance(getActivity()).createBaseApi().upload

                // ；；；；； 略
                /**
                 * look   {@link # http://www.jianshu.com/p/acfefb0a204f}
                 */
            }
        });

        btn_download.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RetrofitClient.getInstance(getActivity()).createBaseApi().download(url3, new CallBack() {

                            @Override
                            public void onStart() {
                                super.onStart();
                                Toast.makeText(getActivity(), url1 + "  is  start", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onProgress(long fileSizeDownloaded) {
                                super.onProgress(fileSizeDownloaded);
                                Toast.makeText(getActivity(), " downLoadeing, download:" + fileSizeDownloaded, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onSucess(String path, String name, long fileSize) {
                                Toast.makeText(getActivity(), name + " is  downLoaded", Toast.LENGTH_SHORT).show();

                            }
                        }
                );
            }
        });


        btn_myApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //create  you APiService
                MyApiService service = RetrofitClient.getInstance(getActivity(), "http://ip.taobao.com/").create(MyApiService.class);

                // execute and add observable
                RetrofitClient.getInstance(getActivity()).execute(

                        service.getData("21.22.11.33"), new BaseSubscriber<BaseResponse<IpResult>>(getActivity()) {

                            @Override
                            public void onError(ExceptionHandle.ResponeThrowable e) {
                                Log.e("Lyk", e.getMessage());
                                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onNext(BaseResponse<IpResult> responseBody) {

                                if (responseBody.isOk()) {
                                    IpResult ip = responseBody.getData();
                                    Toast.makeText(getActivity(), ip.toString(), Toast.LENGTH_LONG).show();
                                }

                            }
                        });
            }
        });


        btn_changeHostApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //create  you APiService
                MyApiService service = RetrofitClient.getInstance(getActivity(), "http://lbs.sougu.net.cn/").create(MyApiService.class);

                // execute and add observable to RxJava
                RetrofitClient.getInstance(getActivity(), "http://lbs.sougu.net.cn/").execute(
                        service.getSougu(), new BaseSubscriber<SouguBean>(getActivity()) {

                            @Override
                            public void onError(ExceptionHandle.ResponeThrowable e) {
                                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();

                            }
                            @Override
                            public void onNext(SouguBean souguBean) {

                                Toast.makeText(getActivity(), souguBean.toString(), Toast.LENGTH_LONG).show();

                            }
                        });
            }
        });



        btn_skyApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create  you APiService
                RetrofitClient client =  RetrofitClient.getInstance(getActivity(), "http://www.weather.com.cn/");
                client.changeApiHeader(new HashMap());
                SkyService service =  client.create(SkyService.class);


                // execute and add observable to RxJava
                RetrofitClient.getInstance(getActivity(), "http://www.weather.com.cn/").execute(
                        service.getSkyDemo(), new BaseSubscriber<WeatherDemo>(getActivity()) {



                            @Override
                            public void onError(ExceptionHandle.ResponeThrowable e) {
                                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();

                            }


                            @Override
                            public void onNext(WeatherDemo souguBean) {

                                Toast.makeText(getActivity(), souguBean.toString(), Toast.LENGTH_LONG).show();

                            }
                        });
            }
        });
    }


    public void initData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Create a very simple REST adapter which points the GitHub API endpoint.
                GitHubClient client = ServiceGenerator.createService(GitHubClient.class);

                // Fetch and print a list of the contributors to this library.

                // Call<List<Contributor>> call = client.contributors("zcwfeng", "zcw_ijkplayer");

                // Call<List<Contributor>> call = client.contributors("holandx", "study_spring_mvc");

                Call<List<Contributor>> call = client.contributors("square", "retrofit");

                try {
                    List<Contributor> contributors = call.execute().body();
                    for (Contributor contributor : contributors) {
                        System.out.println(
                                contributor.login + " (" + contributor.contributions + ")");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();


//        1. 创建OkHttpClient
//        2. 创建Retrofit实例
//        3. 获取我们写的API interface
//        4. 在代码中异步调用

    }
}
