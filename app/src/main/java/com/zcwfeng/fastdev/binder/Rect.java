package com.zcwfeng.fastdev.binder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by David.zhang on 16/5/6.
 * Description：
 */
public class Rect implements Parcelable {
    public int left;
    public int top;
    public int right;
    public int bottom;
    public static final Parcelable.Creator<Rect> CREATOR = new Parcelable.Creator<Rect>() {
        public Rect createFromParcel(Parcel in) {
            return new Rect(in);
        }


        public Rect[] newArray(int size) {
            return new Rect[size];
        }
    };


    public Rect() {
    }


    private Rect(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        left = in.readInt();
        top = in.readInt();
        right = in.readInt();
        bottom = in.readInt();
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(left);
        out.writeInt(top);
        out.writeInt(right);
        out.writeInt(bottom);
    }




}

