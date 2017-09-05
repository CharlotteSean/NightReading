package com.example.wsg.nightreading.utils;

import android.util.Log;

/**
 * 项目名：NightReading
 * 包名：com.example.wsg.nightreading.utils
 * 文件名：L
 * 创建者：wsg
 * 创建时间：2017/9/5  15:42
 * 描述：Logs的封装
 */

public class L {
    //开关
    public static final  boolean DEBUG = true;
    //TAG
    public static final String TAG = "Defined";

    //五个等级  DIWE

    public static void d(String text){
        if(DEBUG){
            Log.d(TAG,text);
        }
    }

    public static void i(String text){
        if(DEBUG){
            Log.i(TAG,text);
        }
    }

    public static void w(String text){
        if(DEBUG){
            Log.w(TAG,text);
        }
    }

    public static void e(String text){
        if(DEBUG){
            Log.e(TAG,text);
        }
    }

    public static void v(String text){
        if(DEBUG){
            Log.v(TAG,text);
        }
    }

}
