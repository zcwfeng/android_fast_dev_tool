package com.zcwfeng.fastdev.demos.zoomimageheader.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.activity.MainActivity;

public class HomeActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    recyclerView.setAdapter(new ListAdapter());
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

  }

  class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());

      return new ViewHolder(inflater.inflate(R.layout.item_home,parent,false));
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          Intent intent = new Intent(HomeActivity.this,MainActivity.class);

          startActivity(intent);
        }
      });
    }

    @Override public int getItemCount() {
      return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

      public ViewHolder(View view) {
        super(view);
      }
    }

  }

}