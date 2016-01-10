package com.zcwfeng.fastdev.CustomBus;

public interface IBus {
    
    // register event target
    boolean register(Object target);

    // unregister event target
    boolean unregister(Object target);

    // post event
    void post(Object event);
}