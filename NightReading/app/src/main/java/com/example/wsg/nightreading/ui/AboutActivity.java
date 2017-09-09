package com.example.wsg.nightreading.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.example.wsg.nightreading.R;
import com.example.wsg.nightreading.base.BaseActivity;
import com.example.wsg.nightreading.utils.UtilTools;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名：NightReading
 * 包名：com.example.wsg.nightreading.ui
 * 文件名：AboutActivity
 * 创建者：wsg
 * 创建时间：2017/9/6  21:28
 * 描述：关于界面
 */

public class AboutActivity extends BaseActivity {
    @BindView(R.id.about_version)
    TextView aboutVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        aboutVersion.setText("版本："+UtilTools.getVersion(this));
    }
}
