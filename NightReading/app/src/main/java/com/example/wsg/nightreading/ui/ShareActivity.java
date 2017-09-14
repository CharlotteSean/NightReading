package com.example.wsg.nightreading.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wsg.nightreading.R;
import com.example.wsg.nightreading.base.BaseActivity;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * 项目名：NightReading
 * 包名：com.example.wsg.nightreading.ui
 * 文件名：ShareActivity
 * 创建者：wsg
 * 创建时间：2017/9/9  17:17
 * 描述：分享界面
 */

public class ShareActivity  extends BaseActivity implements View.OnClickListener {
    private IUiListener userInfoListener;
    private EditText share_et;
    private ImageView share_qq_f;
    private ImageView share_qq_z;
    private ImageView share_wx_f;
    private ImageView share_wx_z;
    private Tencent mTencent;
    private String APP_ID = "1106410774";
    private IUiListener shareListener;
    private String SCOPE = "all";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        initQqLogin();
        initView();
    }

    private void initView() {

        share_et=(EditText)findViewById(R.id.activity_share_et);

        //QQ好友分享
        share_qq_f=(ImageView)findViewById(R.id.share_qq_f);
        share_qq_f.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.share_qq_f:
                qqSharetoF();
                break;
        }

    }

    private void qqSharetoF() {
        Bundle params = new Bundle();
        //这条分享消息被好友点击后的跳转URL。
        //分享的消息摘要，最长50个字
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,share_et.getText().toString().trim() );

        mTencent.shareToQQ(this, params, shareListener);
    }




    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null != mTencent)
            mTencent.onActivityResult(requestCode, resultCode, data);
    }



    //初始化QQ登录分享的需要的资源
    private void initQqLogin(){
        mTencent =  Tencent.createInstance(APP_ID, this);
        //创建QQ分享回调接口
        //qq分享的回调接口
         shareListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                //分享成功后回调
                Toast.makeText(ShareActivity.this, "分享成功！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(UiError uiError) {
                //分享失败后回调
            }

            @Override
            public void onCancel() {
                //取消分享后回调
            }
        };


    };

}



