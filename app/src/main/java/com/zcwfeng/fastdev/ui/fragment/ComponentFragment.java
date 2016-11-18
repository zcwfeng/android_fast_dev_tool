package com.zcwfeng.fastdev.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.fragment.dummy.DummyContent;
import com.zcwfeng.fastdev.ui.fragment.myinterface.OnListFragmentInteractionListener;

/**
 * Created by David.zhang on 2016/10/24.
 * Description：
 */
public class ComponentFragment extends BaseFragment {
    private DummyContent dummyContent;





    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ComponentFragment() {

    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ComponentFragment newInstance(int columnCount) {
        ComponentFragment fragment = new ComponentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dummyContent = new DummyContent(getResources().getStringArray(R.array.component_index));
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_component, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(dummyContent.ITEMS, mListener,3));
        }

        return view;
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    public void setmListener(OnListFragmentInteractionListener lsn){
        mListener = lsn;
    }
}
