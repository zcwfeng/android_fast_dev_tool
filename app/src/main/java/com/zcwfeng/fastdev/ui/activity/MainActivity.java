package com.zcwfeng.fastdev.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;
import com.zcwfeng.fastdev.ui.fragment.ChatFragment;
import com.zcwfeng.fastdev.ui.fragment.ComponentFragment;
import com.zcwfeng.fastdev.ui.fragment.HomeFragment;
import com.zcwfeng.fastdev.ui.fragment.VideoFragment;
import com.zcwfeng.fastdev.ui.fragment.dummy.DummyContent;
import com.zcwfeng.fastdev.ui.fragment.myinterface.OnListFragmentInteractionListener;

public class MainActivity extends BaseActivity implements OnListFragmentInteractionListener {
    private int[] mainBottomIds = new int[]{
            R.id.main_icon,
            R.id.chat_icon,
            R.id.video_icon,
            R.id.component_icon};
    private int[] mainBottomImageRes = new int[]{
            R.drawable.home_n,
            R.drawable.home_p,
            R.drawable.chat_n,
            R.drawable.chat_p,
            R.drawable.video_n,
            R.drawable.video_p,
            R.drawable.component_n,
            R.drawable.component_p};
    private ImageView[] mainBottomLayouts = new ImageView[4];
    private BaseFragment[] baseFragments = new BaseFragment[4];

    public static void launch(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUIView();
        check4Update();
    }


    /**
     * 检查强制更新或者热更新
     */
    private void check4Update() {

    }

    private int nowIndex = 0;


    private void initUIView() {
        for (int i = 0; i < mainBottomIds.length; i++) {
            mainBottomLayouts[i] = (ImageView) findViewById(mainBottomIds[i]);
            final int finalI = i;
            mainBottomLayouts[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalI == 0) {
                    } else if (finalI == 1) {
//                        MobclickAgent.onEvent(LiveApplication.getInstance(), UmengConstant.Calendar);
                    } else if (finalI == 2) {
//                        MobclickAgent.onEvent(LiveApplication.getInstance(), UmengConstant.Channel);
                    } else if (finalI == 3) {
//                        MobclickAgent.onEvent(LiveApplication.getInstance(), UmengConstant.Channel);
                    }
                    if (nowIndex != finalI) {
                        switchFragment(finalI);
                    } else {
                        // TODO: 16/3/28 重复点击
                    }
                }
            });
        }
        if (getSupportFragmentManager().getFragments() != null) {
            getSupportFragmentManager().getFragments().clear();
        }
        switchFragment(0);
    }

    public void switchFragment(int selectItem) {
        mainBottomLayouts[selectItem].setImageResource(mainBottomImageRes[selectItem * 2 + 1]);
        if (selectItem != nowIndex) {
            mainBottomLayouts[nowIndex].setImageResource(mainBottomImageRes[nowIndex * 2]);
        }
        FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
        if (baseFragments[selectItem] == null) {
            switch (selectItem) {
                case 0:
                    baseFragments[selectItem] = HomeFragment.newInstance(0);
                    ((HomeFragment) baseFragments[selectItem]).setmListener(this);
                    break;
                case 1:
                    baseFragments[selectItem] = ChatFragment.newInstance();
                    break;
                case 2:
                    baseFragments[selectItem] = VideoFragment.newInstance();
                    break;
                case 3:
                    baseFragments[selectItem] = ComponentFragment.newInstance(0);
                    ((ComponentFragment) baseFragments[selectItem]).setmListener(this);
                    break;
            }
            mTransaction.add(R.id.layContent, baseFragments[selectItem]);
        }
        if (selectItem != nowIndex) {
            mTransaction.hide(baseFragments[nowIndex]);
            mTransaction.show(baseFragments[selectItem]);
        }
        mTransaction.commitAllowingStateLoss();
        nowIndex = selectItem;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item, int type) {

        if (type == 0) {
            switch (item.id) {
                case "1":
                    BaseActivity.launch(MainActivity.this, RequestActivity.class);
                    break;
                case "2":
                    BaseActivity.launch(MainActivity.this, ImageLibActivity.class);
                    break;
            }
        } else if (type == 1) {

        } else if (type == 2) {

        } else if (type == 3) {
            switch (item.id) {
                case "1":
                    break;
                case "2":
                    break;
            }
        }

    }


}
