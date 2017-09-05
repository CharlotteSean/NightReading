package com.example.wsg.nightreading.application;

import android.app.Application;

import com.example.wsg.nightreading.utils.StaticClass;
import com.tencent.bugly.crashreport.CrashReport;

import cn.bmob.v3.Bmob;

/**
 * 项目名：NightReading
 * 包名：com.example.wsg.nightreading.application
 * 文件名：MyApplication
 * 创建者：wsg
 * 创建时间：2017/9/5  17:30
 * 描述：MyApplication
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_APP_ID, true);

        //默认初始化
        Bmob.initialize(this, "Your Application ID");
        // 注:自v3.5.2开始，数据sdk内部缝合了统计sdk，开发者无需额外集成，传渠道参数即可，不传默认没开启数据统计功能
        //Bmob.initialize(this, "Your Application ID","bmob");
    }
}
