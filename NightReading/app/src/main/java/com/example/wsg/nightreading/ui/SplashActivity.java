package com.example.wsg.nightreading.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

import com.example.wsg.nightreading.MainActivity;
import com.example.wsg.nightreading.R;
import com.example.wsg.nightreading.utils.ShareUtils;
import com.example.wsg.nightreading.utils.StaticClass;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

/**
 * 项目名：NightReading
 * 包名：com.example.wsg.nightreading.ui
 * 文件名：SplashActivity
 * 创建者：wsg
 * 创建时间：2017/9/5  15:57
 * 描述：引导页面
 */

public class SplashActivity extends Activity {
    private ShimmerTextView my;
    private Shimmer shimmer;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case StaticClass.HANDLER_SPLASH :
                    if(isLogin()){
                        //已经登录的逻辑
                        //跳转到主界面
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                    else {
                        //未登录的逻辑
                        //跳转到登录页面
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    }

                    finish();
                    break;
            }
        }
    };


    /**
     * 1.延时2000ms
     * 2.判断程序是否登录
     * 3.自定义字体
     * 4.Activity全屏主题
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);


        initView();
        initData();
    }

    //初始化视图
    private void initView() {
        my=(ShimmerTextView)findViewById(R.id.shimmer_tv);
        //开始动画
        shimmer = new Shimmer();
        shimmer.start(my);
    }

    //初始化数据
    private void initData() {

        //延时2000ms
        mHandler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH, 2000);
    }


    //判断程序是否第一次运行
    private boolean isLogin() {
        boolean isLogin = ShareUtils.getBoolean(this,StaticClass.SHARE_IS_LOGIN,false);
        if(isLogin){
            //已经登录
            return true;
        }else {
            //未登录
            return false;
        }

    }


    //屏蔽返回键
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       //太占资源，需要销毁资源
        shimmer.cancel();
    }
}
