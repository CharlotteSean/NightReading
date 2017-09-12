package com.example.wsg.nightreading.ui;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.wsg.nightreading.R;
import com.example.wsg.nightreading.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名：NightReading
 * 包名：com.example.wsg.nightreading.ui
 * 文件名：DonateActivity
 * 创建者：wsg
 * 创建时间：2017/9/9  17:26
 * 描述：捐赠界面
 */

public class DonateActivity extends BaseActivity {
    @BindView(R.id.donate_iv)
    ImageView donateIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        ButterKnife.bind(this);
    }


    //微信支付的逻辑
    @OnClick(R.id.donate_iv)
    public void onViewClicked() {


    }
}
