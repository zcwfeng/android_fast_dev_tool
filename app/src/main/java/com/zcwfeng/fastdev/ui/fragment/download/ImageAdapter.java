package com.zcwfeng.fastdev.ui.fragment.download;

import android.widget.ImageView;

import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;
import com.zcwfeng.fastdev.basic.MyApplication;
import com.zcwfeng.fastdev.demos.demorealm.util.ToastUtil;

/**
 * Created by lixinke on 16/6/1.
 */
public class ImageAdapter implements IWXImgLoaderAdapter {
    @Override
    public void setImage(String url, ImageView view, WXImageQuality quality, WXImageStrategy strategy) {
        //实现你自己的图片下载，否则图片无法显示。
        ToastUtil.showShortToast(MyApplication.getInstance(),"demo"+url);
    }
}