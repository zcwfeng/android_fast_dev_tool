package com.zcwfeng.fastdev.ui.fragment.download;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zcwfeng.componentlibs.BaseApplication;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.demos.demorealm.util.ToastUtil;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by David.zhang on 2016/10/24.
 * Description：
 */
public class NormalDownloadFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;

    public static NormalDownloadFragment newInstance() {
        Bundle args = new Bundle();
        NormalDownloadFragment fragment = new NormalDownloadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_normal_download, container, false);
        rootView.findViewById(R.id.okhttp_download).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.okhttp_download:
                okHttpDownloadFile();
                break;
        }
    }


    public void okHttpDownloadFile() {
        ToastUtil.showShortToast(getActivity(), "下载");
        String parent = BaseApplication.getInstance().getExternalFilesDir(null).getPath() + "/fastdev";
        File file = new File(parent);
        if (!file.exists()) {
            file.mkdirs();
        }
        Map map = new HashMap();
        map.put("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Mobile Safari/537.36");
        String name = "main.png";
        OkHttpUtils.get()
                .headers(map)
                .url("http://img4.c.yinyuetai.com/others/admin/170112/0/-M-8e1c7a07b0026623a516e2a82991301a_0x0.png")
                .build()
                .execute(new FileCallBack(parent, name) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("zcw", "imgs dowonoad error" + e.getMessage());
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        Log.e("zcw", "imgs dowonoad success" + id);
                    }
                });
    }
}
