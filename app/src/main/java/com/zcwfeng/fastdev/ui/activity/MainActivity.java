package com.zcwfeng.fastdev.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zcwfeng.componentlibs.BaseApplication;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.adapter.custom_type1.ExCommonAdapter;
import com.zcwfeng.fastdev.ui.adapter.custom_type1.ExViewHolder;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;
import com.zcwfeng.fastdev.ui.fragment.ChatFragment;
import com.zcwfeng.fastdev.ui.fragment.ComponentFragment;
import com.zcwfeng.fastdev.ui.fragment.HomeFragment;
import com.zcwfeng.fastdev.ui.fragment.VideoFragment;
import com.zcwfeng.fastdev.ui.fragment.dummy.DummyContent;
import com.zcwfeng.fastdev.ui.fragment.myinterface.OnListFragmentInteractionListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.WeakHashMap;

public class MainActivity extends BaseActivity implements OnListFragmentInteractionListener,DrawerLayout.DrawerListener {
    Toolbar mToolBar;
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


    private RecyclerView mSlideRecyclerView;
    private ExCommonAdapter mSlideAdapter;

    private String[] githubKey= {
            "zcwfeng/zcw_ijkplayer",
            "zcwfeng/StarTV",
            "ReactiveX/RxJava",
            "square/retrofit",
            "square/okhttp"
//            "bumptech / glide",
//            "ReactiveX / RxAndroid",
//            "loopj / android-async-http",
//            "nickbutcher / plaid",
//            "navasmdc / MaterialDesignLibrary",
//            "alibaba / fastjson",
//            "liaohuqiu / android-Ultra-Pull-To-Refresh",
//            "realm / realm-java",
//            "alibaba / dubbo",
//            "JetBrains / kotlin",
//            "facebook / stetho",
//            "google / dagger",
//            "google / flexbox-layout",
//            "wasabeef / recyclerview-animators",
//            "DroidPluginTeam / DroidPlugin",
//            "hongyangAndroid / okhttputils",
//            "JackyAndroid / AndroidInterview-Q-A",
//            "singwhatiwanna / dynamic-load-apk"

    };
    private String[] githubValue= {
//            "https://github.com/zcwfeng/zcw_ijkplayer",
            "http://www.iqiyi.com/a_19rrh9swyh.html",
            "https://github.com/zcwfeng/StarTV",
            "https://github.com/ReactiveX/RxJava",
            "https://github.com/square/retrofit",
            "https://github.com/square/okhttp"
    };
    private WeakHashMap weakHashMap;
    private HashMap githubMap = new HashMap();

    private NavigationView mNavView;

    public static void launch(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        setToolbar(mToolBar,"快速开发库");
        initUIView();
        check4Update();

        initSlideData();
    }

    private void initSlideData() {
        for(int i= 0;i<githubKey.length;i++) {
            githubMap.put(githubKey[i],githubValue[i]);
        }
        weakHashMap = new WeakHashMap(githubMap);
        weakHashMap.put("github",githubMap);
        List githubs = new ArrayList<String>();
        Collections.addAll(githubs,githubKey);
        mSlideAdapter.setData(githubs);
    }


    /**
     * 检查强制更新或者热更新
     */
    private void check4Update() {

    }

    private int nowIndex = 0;


    private void initUIView() {

        mSlideRecyclerView = (RecyclerView) findViewById(R.id.main_draw_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mSlideRecyclerView.setLayoutManager(linearLayoutManager);
        mSlideRecyclerView.setAdapter(mSlideAdapter = new ExCommonAdapter<String>(this,R.layout.item_slide) {
            @Override
            protected void convert(ExViewHolder viewHolder, String item) {
                viewHolder.setText(R.id.title,item);
                ImageView view = viewHolder.getView(R.id.slide_head_icon);
//                if(item.contains("zcwfeng")){
//                    Glide.with(MainActivity.this).load("https://avatars0.githubusercontent.com/u/927238?v=3&u=f842420a412475b68237f479ff287ebd70c0fada&s=400")
//                            .into((ImageView) viewHolder.getView(R.id.head));
//                }else {
//                    Glide.with(MainActivity.this).load(getResources().getDrawable(R.drawable.head))
//                            .into((ImageView) viewHolder.getView(R.id.head));
//                }

                Glide.with(BaseApplication.getInstance()).load("").placeholder(getResources().getDrawable(R.drawable.head)).into(view);

                viewHolder.setOnClickListener(R.id.title, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WebActivity.launch(MainActivity.this, (String) githubMap.get(item));
                    }
                });
            }
        });




        mNavView = (NavigationView) findViewById(R.id.nav_view);

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
                    ((ChatFragment) baseFragments[selectItem]).setmListener(this);

                    break;
                case 2:
                    baseFragments[selectItem] = VideoFragment.newInstance();
                    ((VideoFragment) baseFragments[selectItem]).setmListener(this);
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
                case "3":
                    DatabasesActivity.launch(MainActivity.this,DatabasesActivity.class);
                    break;
                case "4":
                    break;
                case "5":
                    MyReactActivity.launch(MainActivity.this);
                    break;
                case "6":
                    MyReactActivity.launch(MainActivity.this);
                    break;
                case "7":
                    MySecurityActivity.launch(MainActivity.this);
                    break;
            }
        } else if (type == 1) {
            switch (item.id) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
            }
        } else if (type == 2) {
            switch (item.id) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
//                    http://www.imooc.com/course/ajaxmediainfo/?mid=12141&mode=flash

                    break;
            }
        } else if (type == 3) {
            switch (item.id) {
                case "1":
                    BaseActivity.launch(MainActivity.this,CustomViewActivity.class);
                    break;
                case "2":
                    BaseActivity.launch(MainActivity.this,ServiceActivity.class);
                    break;
                case "3":
                    BaseActivity.launch(MainActivity.this,LBSMapActivity.class);
                    break;
                case "4":
                    BaseActivity.launch(MainActivity.this,PushActivity.class);
                    break;
                case "5":
                    BaseActivity.launch(MainActivity.this,SkillActivity.class);
                    break;
                case "6":
                    BaseActivity.launch(MainActivity.this,SkillActivity.class);
                    break;
                case "7":
                    BaseActivity.launch(MainActivity.this,SkillActivity.class);
                    break;
                case "8":
                    BaseActivity.launch(MainActivity.this,SkillActivity.class);
                    break;
                case "9":
                    BaseActivity.launch(MainActivity.this,SkillActivity.class);
                    break;
                case "10":
                    BaseActivity.launch(MainActivity.this,SkillActivity.class);
                    break;
                case "11":
                    BaseActivity.launch(MainActivity.this,DownloadActivity.class);
                    break;
                case "12":
                    BaseActivity.launch(MainActivity.this,SkillActivity.class);
                    break;
                case "13":
                    BaseActivity.launch(MainActivity.this,SkillActivity.class);
                    break;
                case "14":
                    BaseActivity.launch(MainActivity.this,CameraActivity.class);
                    break;
                case "15":
                    BaseActivity.launch(MainActivity.this,LifeCycleActivity.class);
                    break;
            }
        }

    }


    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {
        List githubs = new ArrayList<String>();
        Collections.addAll(githubs,githubKey);
        mSlideAdapter.setData(githubs);
    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }


}
