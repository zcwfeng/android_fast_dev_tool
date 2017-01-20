package com.zcwfeng.fastdev.ui.fragment.database;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.demos.demo_greendao.adapter.ShopListAdapter;
import com.zcwfeng.fastdev.demos.demo_greendao.bean.Shop;
import com.zcwfeng.fastdev.demos.demo_greendao.dao.LoveDao;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcw on 2016/11/21.
 */

public class GreenDaoFragment extends BaseFragment implements View.OnClickListener {
    private Button bt_add, bt_delete, bt_update, bt_query;
    private ListView lv_content;
    private ShopListAdapter adapter;
    private List<Shop> shops;
    private static int i = 10;    private View rootView;
    public static GreenDaoFragment newInstance() {
        
        Bundle args = new Bundle();
        
        GreenDaoFragment fragment = new GreenDaoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_greendao, container, false);
        initView();
        queryDate();
        return rootView;
    }

    private void initView() {
        bt_add = (Button) rootView.findViewById(R.id.bt_add);
        bt_delete = (Button) rootView.findViewById(R.id.bt_delete);
        bt_update = (Button) rootView.findViewById(R.id.bt_update);
        bt_query = (Button) rootView.findViewById(R.id.bt_query);
        lv_content = (ListView) rootView.findViewById(R.id.lv_content);
        bt_add.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
        bt_update.setOnClickListener(this);
        bt_query.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
                addDate();
                break;
            case R.id.bt_delete:
                deleteDate();
                break;
            case R.id.bt_update:
                updateDate();
                break;
            case R.id.bt_query:
                queryDate();
                break;
        }
    }

    private void deleteDate() {
        LoveDao.deleteLove(shops.get(0).getId());

        queryDate();
    }

    private void queryDate() {
        shops = new ArrayList<>();
        shops = LoveDao.queryLove();
        adapter = new ShopListAdapter(getActivity(), shops);
        lv_content.setAdapter(adapter);
    }

    private void addDate() {
        Shop shop = new Shop();
        shop.setType(Shop.TYPE_LOVE);
        shop.setAddress("广东深圳");
        shop.setImage_url("https://img.alicdn.com/bao/uploaded/i2/TB1N4V2PXXXXXa.XFXXXXXXXXXX_!!0-item_pic.jpg_640x640q50.jpg");
        shop.setPrice("19.40");
        shop.setSell_num(15263);
        shop.setName("正宗梅菜扣肉 聪厨梅干菜扣肉 家宴常备方便菜虎皮红烧肉 2盒包邮" + i++);
        LoveDao.insertLove(shop);

        queryDate();
    }

    private void updateDate() {
        Shop shop = shops.get(0);
        shop.setName("我是修改的名字");
        LoveDao.updateLove(shop);

        queryDate();
    }
}
