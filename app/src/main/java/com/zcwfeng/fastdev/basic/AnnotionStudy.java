package com.zcwfeng.fastdev.basic;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.activity.BaseActivity;

/**
 * Created by zcw on 2017/2/17.
 */

public class AnnotionStudy extends BaseActivity {


    private int flavour;
    public static final int VANILLA = 0;
    public static final int CHOCOLATE = 1;
    public static final int STRAWBERRY = 2;
    @IntDef(flag = true,value = {VANILLA, CHOCOLATE, STRAWBERRY})
    public @interface Flavour {
    }

    @Flavour
    public int getFlavour() {
        return flavour;
    }

    public void setFlavour(@Flavour int flavour) {
        this.flavour = flavour;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sayHello(R.string.app_name);


        setFlavour(AnnotionStudy.VANILLA & AnnotionStudy.CHOCOLATE);
    }

    void sayHello(@StringRes int id) {
        Toast.makeText(this, "Hello " + getString(id), Toast.LENGTH_LONG).show();
    }
}
