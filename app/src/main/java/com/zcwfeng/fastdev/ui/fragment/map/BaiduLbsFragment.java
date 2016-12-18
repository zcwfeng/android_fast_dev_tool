package com.zcwfeng.fastdev.ui.fragment.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.map.MapView;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;


/**
 * Created by David.zhang on 2016/10/24.
 * Description：api key = ezXEx7aVgik1TY3teulwXhcaxGFPctZs
 */
public class BaiduLbsFragment extends BaseFragment {

    private View rootView;
    private MapView mMapView;

    public BaiduLbsFragment() {
    }

    public static BaiduLbsFragment newInstance() {

        Bundle args = new Bundle();

        BaiduLbsFragment fragment = new BaiduLbsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_lbs_map_baidu, container, false);

        //获取地图控件引用
        mMapView = (MapView) rootView.findViewById(R.id.bmapView);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

}
