package com.zcwfeng.fastdev.glide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.zcwfeng.componentlibs.ui.basic.BaseActivity_deprecated;
import com.zcwfeng.fastdev.R;

/**
 * Created by David.zhang on 16/5/11.
 * Description：
 */
public class GlideLibDemos extends BaseActivity_deprecated implements View.OnClickListener{

    private View testSwitchLayout;

    private View demo1;
    private GridView mGridView;
    public static String[] eatFoodyImages = {
            "http://i.imgur.com/rFLNqWI.jpg",
            "http://i.imgur.com/C9pBVt7.jpg",
            "http://i.imgur.com/rT5vXE1.jpg",
            "http://i.imgur.com/aIy5R2k.jpg",
            "http://i.imgur.com/MoJs9pT.jpg",
            "http://i.imgur.com/S963yEM.jpg",
            "http://i.imgur.com/rLR2cyc.jpg",
            "http://i.imgur.com/SEPdUIx.jpg",
            "http://i.imgur.com/aC9OjaM.jpg",
            "http://i.imgur.com/76Jfv9b.jpg",
            "http://i.imgur.com/fUX7EIB.jpg",
            "http://i.imgur.com/syELajx.jpg",
            "http://i.imgur.com/COzBnru.jpg",
            "http://i.imgur.com/Z3QjilA.jpg",
    };

    Button testSwitch1;
    Button testSwitch2;
    Button testSwitch3;


    public static void launch(Context from){
        Intent intent = new Intent(from,GlideLibDemos.class);
        from.startActivity(intent);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_libdemos);

        initView();
    }

    private void initView() {

        testSwitchLayout = findViewById(R.id.test_switch_layout);

        testSwitch1 = (Button) findViewById(R.id.test_switch1);
        demo1 = findViewById(R.id.test_layout1);
        mGridView = (GridView) findViewById(R.id.usage_example_gridview);



        mGridView.setAdapter(new ImageListAdapter(this, eatFoodyImages));

        findViewById(R.id.reset).setOnClickListener(this);
        testSwitch1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_switch1:
                demo1.setVisibility(View.VISIBLE);
                testSwitchLayout.setVisibility(View.GONE);

                break;
            case R.id.reset:
                testSwitchLayout.setVisibility(View.VISIBLE);
                break;
        }
    }
}
