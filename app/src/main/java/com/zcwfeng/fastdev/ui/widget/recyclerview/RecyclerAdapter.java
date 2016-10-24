package com.zcwfeng.fastdev.ui.widget.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zcwfeng.fastdev.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangyangkai on 16/6/12.
 */
public class RecyclerAdapter extends RecyclerView.Adapter {


    private Context context;
    private LayoutInflater inflater;
    private List<Integer> lists = new ArrayList<>();


    public RecyclerAdapter(Context context, List<Integer> lists) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.lists = lists;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler,null);
        return new com.zcwfeng.fastdev.ui.widget.recyclerview.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.txt.setText("第" + String.valueOf(lists.get(position)) + "项");
        viewHolder.layout.scrollTo(0, 0);
    }

    @Override
    public int getItemCount() {
        if (lists != null) {
            return lists.size();
        } else {
            return 0;
        }
    }

    public void removeRecycle(int position) {
        lists.remove(position);
        notifyDataSetChanged();
        if (lists.size() == 0) {
            Toast.makeText(context, "已经没数据啦", Toast.LENGTH_SHORT).show();
        }
    }


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

}
