package com.zcwfeng.fastdev.ui.fragment.map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.zcwfeng.componentlibs.surport.utils.LogFormater;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;


/**
 * Created by David.zhang on 2016/10/24.
 * Description：api key = AIzaSyBHfk2gpQY0b4ljxxlwxsndIF_lulI78Lk
 */
public class GoogleLbsFragment extends BaseFragment implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener{
    private GoogleApiClient mGoogleApiClient;
    private View rootView;


    public GoogleLbsFragment() {
    }

    public static GoogleLbsFragment newInstance() {

        Bundle args = new Bundle();

        GoogleLbsFragment fragment = new GoogleLbsFragment();
        fragment.setArguments(args);

        return fragment;
    }
    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGoogleApiClient = new GoogleApiClient
                .Builder(getActivity())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_lbs_map_google, container, false);


        return rootView;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LogFormater.d("google","onConnected");
    }

    @Override
    public void onConnectionSuspended(int i) {
        LogFormater.d("google","onConnectionSuspended");

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        LogFormater.d("google","onConnectionFailed");

    }
}
