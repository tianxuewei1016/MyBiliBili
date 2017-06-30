package com.xuewei.mybilibili.app;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * 作者：田学伟 on 2017/6/30 19:04
 * QQ：93226539
 * 作用：
 */

public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
        ZXingLibrary.initDisplayOpinion(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
//        // Normal app init code...
    }

    public static  Context getInstance(){
        return mContext;
    }
}

