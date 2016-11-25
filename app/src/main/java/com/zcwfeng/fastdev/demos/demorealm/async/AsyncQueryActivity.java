package com.zcwfeng.fastdev.demos.demorealm.async;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.demos.demorealm.adapter.BaseAdapter;
import com.zcwfeng.fastdev.demos.demorealm.adapter.LikeCatAdapter;
import com.zcwfeng.fastdev.demos.demorealm.bean.Cat;
import com.zcwfeng.fastdev.demos.demorealm.util.DefaultItemTouchHelpCallback;
import com.zcwfeng.fastdev.demos.demorealm.util.ToastUtil;
import com.zcwfeng.fastdev.ui.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class AsyncQueryActivity extends BaseActivity {
    Toolbar mToolbar;
    RecyclerView mRecyclerView;

    private Realm mRealm;
    private List<Cat> mCats=new ArrayList<>();
    private LikeCatAdapter mAdapter;
    private RealmResults<Cat> cats;
    private RealmAsyncTask deleteTask;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setLayoutId(R.layout.activity_async_query);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        setToolbar(mToolbar,"异步查、改");
        mRealm= Realm.getDefaultInstance();
        initRecyclerView();
        getData();
        addListener();
    }

    private void initRecyclerView() {
        LinearLayoutManager manager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter=new LikeCatAdapter(this,mCats,R.layout.item_dog);
        mRecyclerView.setAdapter(mAdapter);
        setSwipeDelete();
    }


    private void getData() {
        cats=mRealm.where(Cat.class).findAllAsync();
        cats.addChangeListener(new RealmChangeListener<RealmResults<Cat>>() {
            @Override
            public void onChange(RealmResults<Cat> element) {
                Log.i("TAG","111111111");
               element= element.sort("id");
                List<Cat> datas=mRealm.copyFromRealm(element);
                mCats.clear();
               mCats.addAll(datas);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void addListener() {
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(AsyncQueryActivity.this,UpdateCatActivity.class);
                intent.putExtra("id",mCats.get(position).getId());
                startActivityForResult(intent,100);
            }
        });
    }



    private void setSwipeDelete() {
        DefaultItemTouchHelpCallback mCallback = new DefaultItemTouchHelpCallback(new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
            @Override
            public void onSwiped(int adapterPosition) {
                deleteCat(mCats.get(adapterPosition).getId());
                mCats.remove(adapterPosition);
                mAdapter.notifyItemRemoved(adapterPosition);
            }

            @Override
            public boolean onMove(int srcPosition, int targetPosition) {

                return false;
            }
        });
        mCallback.setDragEnable(false);
        mCallback.setSwipeEnable(true);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void deleteCat(final String id) {

      deleteTask=  mRealm.executeTransactionAsync(new Realm.Transaction() {

            @Override
            public void execute(Realm realm) {
                Cat cat=realm.where(Cat.class).equalTo("id",id).findFirst();

                if (cat!=null){
                   cat.deleteFromRealm();
               }

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                ToastUtil.showShortToast(AsyncQueryActivity.this,"删除成功");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                ToastUtil.showShortToast(AsyncQueryActivity.this,"删除失败");

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK&&requestCode==100){
            mCats.clear();
            getData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cats.removeChangeListeners();
        if (deleteTask!=null&&!deleteTask.isCancelled()){
            deleteTask.cancel();
        }
    }
}
