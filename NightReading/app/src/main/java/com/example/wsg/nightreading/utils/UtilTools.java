package com.example.wsg.nightreading.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.wsg.nightreading.R;

/**
 * 项目名：NightReading
 * 包名：com.example.wsg.nightreading.utils
 * 文件名：UtilTools
 * 创建者：wsg
 * 创建时间：2017/9/5  15:54
 * 描述：统一工具类
 */

public class UtilTools {





    //获取版本号
    public static String getVersion(Context mContext){
        PackageManager pm = mContext.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(mContext.getPackageName(),0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return mContext.getString(R.string.text_unknown);
        }
    }
}
