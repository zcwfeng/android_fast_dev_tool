package com.zcwfeng.fastdev.ui.adapter.custom_type1;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ExViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;

    public ExViewHolder(View itemView) {
        super(itemView);
        this.views = new SparseArray<>();
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (null == view) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public ExViewHolder setText(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        if (view != null) {
            view.setText(text);
        }
        return this;
    }

    public ExViewHolder setText(int viewId, int intStr) {
        TextView view = getView(viewId);
        if (view != null) {
            view.setText(String.valueOf(intStr));
        }
        return this;
    }


    public ExViewHolder setVisible(int viewId, int visablity) {
        View view = getView(viewId);
        if (view != null) {
            view.setVisibility(visablity);
        }
        return this;
    }

    public ExViewHolder setTextColor(int viewId, int color) {
        TextView view = getView(viewId);
        if (view != null) {
            view.setTextColor(color);
        }
        return this;
    }

    public ExViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
        return this;
    }

    public ExViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnTouchListener(listener);
        }
        return this;
    }

    public ExViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        if (view != null) {
            view.setImageResource(drawableId);
        }
        return this;
    }

    public ExViewHolder setButton(int viewId, String text, int resId) {
        Button view = getView(viewId);
        if (view != null) {
            view.setBackgroundResource(resId);
            view.setText(text);
        }
        return this;
    }

    public ExViewHolder setButton(int viewId, String text, int resId, View.OnClickListener lsn) {
        Button view = getView(viewId);
        if (view != null) {
            view.setBackgroundResource(resId);
            view.setText(text);
            view.setOnClickListener(lsn);
        }
        return this;
    }


    public ExViewHolder setCheckBox(int viewId) {
        CheckBox view = getView(viewId);
        return this;
    }

    public ExViewHolder setCheckBox(int viewId, boolean isCheck) {
        CheckBox view = getView(viewId);
        if (view != null) {
            view.setChecked(isCheck);
        }
        return this;
    }

    public ExViewHolder setBackGround(int viewId, int resId) {
        View view = getView(viewId);
        if (view != null) {
            view.setBackgroundResource(resId);
        }
        return this;
    }

    public ExViewHolder addChildView(int viewId, View view) {
        ViewGroup view1 = (ViewGroup) getView(viewId);
        if (view1 != null && view != null) {
            view1.addView(view);
        }
        return this;
    }

    public ExViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        if (view != null) {
            view.setTag(tag);
        }
        return this;
    }

    public int getChildCount(int viewId) {
        return ((ViewGroup) getView(viewId)).getChildCount();
    }

    public View getChildAt(int viewId, int index) {
        return ((ViewGroup) getView(viewId)).getChildAt(index);
    }

    public void setEnableState(int viewId, boolean isEnable) {
        View view = getView(viewId);
        if (view != null) {
            view.setEnabled(isEnable);
        }
    }
}
