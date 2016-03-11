package com.zcwfeng.componentlibs.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.zcwfeng.componentlibs.ui.basic.BaseActivity;

/**
 * ==========================================
 * Created by David Zhang on 2015/08/30.
 * Description：
 * Copyright © 2015 张传伟. All rights reserved.
 * Modified by:
 * Modified Content:
 * <p>
 * <p>
 * <<<<(-......                    .. .'-~(~~~~~~--''''''''''''
 * <<<<~'......                    ...  .-~(~~~~------''''-''''
 * <<<(-..'..........             ........'~~~~-------'''--''''
 * <+<~'..'...........            .  ..... .-~~-------'''''''''
 * <<(-'..............                ..... '-~~--------'''''''
 * <<(-''''............              ........'-~---------''-'''
 * +<~''--''...........             ..........'---------------'
 * <<-''----'.  .......            ..  ........'-~-------------
 * <(-''----'.  ............        ............'-~-------~----
 * <(-'------'. .............        ............-~~~----------
 * <~-'------'.. .............        .... ..'...'-~~~~~~------
 * <~'--------'.   .............       ...........'-~~~-~------
 * (~---------'..    ...............    ........'..'-~~--------
 * (~-------''''..    ................    ..........'-~--------
 * ~~~-----''''''..     ............................''---------
 * ~~~~----'.''''..     .............................'---''----
 * ~~------'...'''... ..  ...........................'''-------
 * -~~~----'....''......   ...........................''-------
 * ~~~--''-'.....'''.....   ..''......................''-------
 * ~~~-----'.....''......    ...''.....................''------
 * -~~~~~--.... ...........   ...'''...................''-~~~~~
 * -~~~--''....  ..    ....     ...''''.................'-~~~~~
 * ~~-'........  .                ...''''................'-~~((
 * -'.  ..         .         ...     ....''''............''~~~(
 * '.......'''''''.'....        ..    .....''...... .......'-((
 * '..-(<=szzhDDhzzss=+++<(~~'..   ..   ...  ......  .......-~~
 * -~<zBNNNNNNBDDDhDDBBBNNNNDhs+<<(~-'''..... ..  ..........''-
 * +===zhhDDDBBBBDhhzzzDDDhhhDBBNNNBhz=+(-'........   ........'
 * +=++=shDDhhDDDDBBDhzs===szzhhzssssszhBDz<~'.    ............
 * <<((<=szzzzzhDDDDDhs==++<++=========+=zhBDh=(-...     ......
 * (~-~(<+=zzzsszzs=sz=+<<+<<<+++++=++=s===szhDBh=~'...........
 * (-'-~~(<==szs=++====+<<+<<<+++++++=======szzzhDDz+~'........
 * (-.'''((+=zsss=++==s=<(<<<<+++++++========szsszzhBBz+~-.....
 * (-'.''~(<+ssss=====s=<(+<<<++++=++=====+==szsssszzhDNNDs+<~-
 * ~-'.'.-((<=ss==sssss=<((<<<+==+++++====+=sssssssszzDBDDDBBDh
 * ~-'''.'~(<+=s++===ss=+<(<<<+++++++++======ssss====sDDsszzzhh
 * (~-'..'~((<+=++++==zz=<(<+++<<+++=============++<+hh=++=====
 * <(-'.''-(((<==<<++=szz=++++++=============+++<<<+zs+<<++++++
 * =<~-''.'(((<++<<++++shhhzs====sss========+++<<<+sh+<<(<<<+==
 * +=+~-'.-~(~(<<<<+++=shhDDzss===s==+===ss=+++<<+=B=<<(<<+++==
 * ++=+(~-'-((~(+++=++=shDDDhssszzs=+++=zz=+=+++<=Bs+(<+++=====
 * ++=++(~--~(~~(+==+=zzhDDBBDzs===+++szhzs===++=Dz+<+=s==+<<<<
 * <+++=+(~-~~~~(+=+=zhhhDDDBBDhss===szhhzs====+hD=====++<<((<+
 * <<<+=+(~-~~~~(++sszhhhhhhhDBBDDzhhDDhzsss===hDs====+<((~~(+s
 * <<<<+++(--~~-~<szzzszzzzhhhDBDBDDDDhzsssss=hBz==+++<<<(~(+ss
 * <(<<<++<-'-~~-(szs===sszzhDDDhBDDhhzzssss=zBhs+==+<<<<<<+szz
 * ((((<<++(~--~~(+=======sszhhDshDhzzzzzzsssBDs=+=+<<<<<<+=sss
 * ((((<<<+=<--~~~<===+=+=szzszhshhs==ssss=sBBh==+++<<<<<++ss==
 * ((((((<++<<~--~(<=++++=szs=szshDs======sBBBs+++++<<++++====+
 * ((((((<++++<~~~((+<+++=zzs=szshDs====szhNBz+++=+++++++=====+
 * <p>
 * ==========================================
 *
 */


public class AsToolBar extends Toolbar {

    static final String TAG = "AsToolbar";


    public AsToolBar(Context context) {
        super(context);
    }

    public AsToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AsToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private long lastClickTime = 0;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean handler = super.onTouchEvent(ev);

        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (lastClickTime != 0) {
                if (System.currentTimeMillis() - lastClickTime <= 500) {
                    BaseActivity activity = BaseActivity.getRunningActivity();
                    if (activity != null && activity instanceof OnToolbarDoubleClick)
                        ((OnToolbarDoubleClick) activity).onToolbarDoubleClick();
                }
            }

            lastClickTime = System.currentTimeMillis();
        }

        return handler;
    }

    public interface OnToolbarDoubleClick {

        public boolean onToolbarDoubleClick();

    }
}
