// IActivityAidl.aidl
package com.zcwfeng.fastdev.binder;
import com.zcwfeng.fastdev.binder.Rect;
// Declare any non-default types here with import statements

interface IActivityAidl {
    void performAction();
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

}
