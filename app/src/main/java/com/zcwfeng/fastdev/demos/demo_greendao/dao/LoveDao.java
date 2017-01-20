package com.zcwfeng.fastdev.demos.demo_greendao.dao;

import com.zcwfeng.fastdev.basic.MyApplication;
import com.zcwfeng.fastdev.demos.demo_greendao.bean.Shop;
import com.zcwfeng.fastdev.demos.demo_greendao.bean.ShopDao;

import java.util.List;

/**
 * Created by handsome on 2016/4/19.
 */
public class LoveDao {

    /**
     * 添加数据，如果有重复则覆盖
     *
     * @param shop
     */
    public static void insertLove(Shop shop) {
        MyApplication.getDaoInstant().getShopDao().insert(shop);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void deleteLove(long id) {
        MyApplication.getDaoInstant().getShopDao().deleteByKey(id);
    }

    /**
     * 更新数据
     *
     * @param shop
     */
    public static void updateLove(Shop shop) {
        MyApplication.getDaoInstant().getShopDao().update(shop);
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<Shop> queryLove() {
        return MyApplication.getDaoInstant().getShopDao().queryBuilder().where(ShopDao.Properties.Type.eq(Shop.TYPE_LOVE)).list();
    }

    /**
     * 查询全部数据
     */
    public static List<Shop> queryAll() {
        return MyApplication.getDaoInstant().getShopDao().loadAll();
    }

}