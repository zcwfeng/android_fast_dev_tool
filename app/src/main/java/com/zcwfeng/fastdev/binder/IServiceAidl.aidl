// IServiceAidl.aidl
package com.zcwfeng.fastdev.binder;
import com.zcwfeng.fastdev.binder.IActivityAidl;
// Declare any non-default types here with import statements

interface IServiceAidl {
    void registerTestCall(IActivityAidl cb);
    void invokCallBack();
}
