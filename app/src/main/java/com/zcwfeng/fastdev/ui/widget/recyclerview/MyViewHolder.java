package com.zcwfeng.fastdev.ui.widget.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zcwfeng.fastdev.R;

/**
 * Created by tangyangkai on 16/6/12.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {


    public TextView txt;
    public ImageView img;
    public LinearLayout layout;
    public MyViewHolder(View itemView) {
        super(itemView);
        txt= (TextView) itemView.findViewById(R.id.item_recycler_txt);
        img= (ImageView) itemView.findViewById(R.id.item_delete_img);
        layout= (LinearLayout) itemView.findViewById(R.id.item_recycler_ll);
    }
}
