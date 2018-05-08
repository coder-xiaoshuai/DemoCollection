package com.zhangs.jpushdemo;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

public class JpushBaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //极光初始化
        JPushInterface.init(this);
    }
}
