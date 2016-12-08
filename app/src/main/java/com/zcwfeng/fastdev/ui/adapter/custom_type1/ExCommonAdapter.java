package com.zcwfeng.fastdev.ui.adapter.custom_type1;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class ExCommonAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPES_HEADER = 1000;
    public static final int VIEW_TYPES_FOOTER = 1001;

    private View mHeaderView;
    private View mFooterView;

    private Context mContext;
    private int mMainLayoutResId;

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private LayoutInflater mLayoutInflater;
    private View mContentView;

    public ExCommonAdapter(Context context) {
        this.mContext = context;
        this.mData = new ArrayList<>();
    }

    public ExCommonAdapter(Context context, int layoutId) {
        this(context);
        this.mMainLayoutResId = layoutId;
    }

    @Override
    public ExViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ExViewHolder exViewHolder;
        mContext = parent.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);

        switch (viewType) {
            case VIEW_TYPES_HEADER:
                exViewHolder = new ExViewHolder(mHeaderView);
                break;
            case VIEW_TYPES_FOOTER:
                exViewHolder = new ExViewHolder(mFooterView);
                break;
            default:
                exViewHolder = onCreateDefViewHolder(parent, viewType);
                initItemClickListener(exViewHolder);
        }
        return exViewHolder;
    }

    private void initItemClickListener(final ExViewHolder exViewHolder) {
        if (mOnItemClickListener != null) {
            exViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, exViewHolder.getLayoutPosition() - getHeaderViewsCount());
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            exViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mOnItemLongClickListener.onItemLongClick(v, exViewHolder.getLayoutPosition() - getHeaderViewsCount());
                }
            });
        }
    }

    protected ExViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return createBaseViewHolder(parent, mMainLayoutResId);
    }

    protected ExViewHolder createBaseViewHolder(ViewGroup parent, int layoutResId) {
        if (mContentView == null) {
            return new ExViewHolder(getItemView(layoutResId, parent));
        }
        return new ExViewHolder(mContentView);
    }

    protected View getItemView(int layoutResId, ViewGroup parent) {
        return mLayoutInflater.inflate(layoutResId, parent, false);
    }

    public int getHeaderViewsCount() {
        return mHeaderView == null ? 0 : 1;
    }

    public int getFooterViewsCount() {
        return mFooterView == null ? 0 : 1;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int location = holder.getLayoutPosition() - getHeaderViewsCount();
        if (location >= 0 && location < mData.size()) {
            convert((ExViewHolder) holder, mData.get(location));
        }
    }

    protected abstract void convert(final ExViewHolder viewHolder, final T item);

    @Override
    public int getItemCount() {
        return mData.size() + getHeaderViewsCount() + getFooterViewsCount();
    }

    @Override
    public int getItemViewType(int position) {

        if (null != mHeaderView && 0 == position) {
            return VIEW_TYPES_HEADER;
        }

        if (null != mFooterView && getItemCount() - 1 == position) {
            return VIEW_TYPES_FOOTER;
        }

        return getDefItemViewType(position - getHeaderViewsCount());
    }

    protected int getDefItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int type = holder.getItemViewType();
        if (type == VIEW_TYPES_HEADER || type == VIEW_TYPES_FOOTER) {
            setFullSpan(holder);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    return (type == VIEW_TYPES_HEADER || type == VIEW_TYPES_FOOTER) ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    protected void setFullSpan(RecyclerView.ViewHolder holder) {
        if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            params.setFullSpan(true);
        }
    }

    protected List<T> mData;

    public void setData(List<T> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void setData(T data, int position) {
        mData.set(position, data);
        notifyItemChanged(position + getHeaderViewsCount());
    }

    public void remove(int position) {
        mData.remove(position);
        notifyItemRemoved(position + getHeaderViewsCount());
    }


    public void addDataNoticeAll(T data, int position) {
        if (data == null) {
            return;
        }
        mData.add(position, data);
        notifyItemInserted(0);
//        notifyDataSetChanged();
    }

    public void addDataNoticeAll(List<T> data) {
        if (data == null) {
            return;
        }
        mData.addAll(data);
        notifyDataSetChanged();
        ;
    }

    public void addData(T data, int position) {
        if (data == null) {
            return;
        }
        mData.add(position, data);
        notifyItemInserted(position + getHeaderViewsCount());
    }

    public void addData(List<T> datas) {
        if (datas == null) {
            return;
        }
        int oldSize = getDataSize();
        mData.addAll(datas);
        notifyItemRangeInserted(oldSize + 1, datas.size());
    }

    public boolean removeAll(List<T> datas) {
        if (datas == null) {
            return false;
        }
        boolean removeAll = mData.removeAll(datas);
        notifyDataSetChanged();
        return removeAll;
    }


    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return mData.get(position);
    }

    public int getDataSize() {
        return mData.size();
    }

    public List<T> getData() {
        return mData;
    }

    public void setHeaderView(View headerView) {
        this.mHeaderView = headerView;
    }

    public void setFooterView(View footerView) {
        this.mFooterView = footerView;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public Context getContext() {
        return mContext;
    }

    public void onDestroy() {
        mOnItemClickListener = null;
        mOnItemLongClickListener = null;
        mFooterView = null;
        mHeaderView = null;
        mData = null;
        mContext = null;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, int position);
    }
}
