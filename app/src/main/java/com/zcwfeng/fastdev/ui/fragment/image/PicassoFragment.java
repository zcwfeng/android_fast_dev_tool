package com.zcwfeng.fastdev.ui.fragment.image;

import android.media.ExifInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;

/**
 * Created by David.zhang on 2016/10/24.
 * Description：
 */
public class PicassoFragment extends BaseFragment {

    final String TAG = PicassoFragment.class.getSimpleName();
    private View rootView;

    public static PicassoFragment newInstance() {

        Bundle args = new Bundle();

        PicassoFragment fragment = new PicassoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_picasso, container, false);

        Button getImageInfo;
        getImageInfo = (Button) rootView.findViewById(R.id.getImageInfo);
        getImageInfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    ExifInterface exifInterface = new ExifInterface(
                            "/sdcard/startvlive_logo.png");
                    String FFNumber = exifInterface
                            .getAttribute(ExifInterface.TAG_APERTURE);
                    String FDateTime = exifInterface
                            .getAttribute(ExifInterface.TAG_DATETIME);
                    String FExposureTime = exifInterface
                            .getAttribute(ExifInterface.TAG_EXPOSURE_TIME);
                    String FFlash = exifInterface
                            .getAttribute(ExifInterface.TAG_FLASH);
                    String FFocalLength = exifInterface
                            .getAttribute(ExifInterface.TAG_FOCAL_LENGTH);
                    String FImageLength = exifInterface
                            .getAttribute(ExifInterface.TAG_IMAGE_LENGTH);
                    String FImageWidth = exifInterface
                            .getAttribute(ExifInterface.TAG_IMAGE_WIDTH);
                    String FISOSpeedRatings = exifInterface
                            .getAttribute(ExifInterface.TAG_ISO);
                    String FMake = exifInterface
                            .getAttribute(ExifInterface.TAG_MAKE);
                    String FModel = exifInterface
                            .getAttribute(ExifInterface.TAG_MODEL);
                    String FOrientation = exifInterface
                            .getAttribute(ExifInterface.TAG_ORIENTATION);
                    String FWhiteBalance = exifInterface
                            .getAttribute(ExifInterface.TAG_WHITE_BALANCE);

                    Log.i(TAG, "FFNumber:" + FFNumber);
                    Log.i(TAG, "FDateTime:" + FDateTime);
                    Log.i(TAG, "FExposureTime:" + FExposureTime);
                    Log.i(TAG, "FFlash:" + FFlash);
                    Log.i(TAG, "FFocalLength:" + FFocalLength);
                    Log.i(TAG, "FImageLength:" + FImageLength);
                    Log.i(TAG, "FImageWidth:" + FImageWidth);
                    Log.i(TAG, "FISOSpeedRatings:" + FISOSpeedRatings);
                    Log.i(TAG, "FMake:" + FMake);
                    Log.i(TAG, "FModel:" + FModel);
                    Log.i(TAG, "FOrientation:" + FOrientation);
                    Log.i(TAG, "FWhiteBalance:" + FWhiteBalance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        return rootView;
    }
}
