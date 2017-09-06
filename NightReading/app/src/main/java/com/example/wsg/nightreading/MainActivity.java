package com.example.wsg.nightreading;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.wsg.nightreading.fragment.DoubanFragment;
import com.example.wsg.nightreading.fragment.FriendFragment;
import com.example.wsg.nightreading.fragment.UserFragment;
import com.example.wsg.nightreading.fragment.ZhiHuFragment;
import com.example.wsg.nightreading.utils.ShareUtils;
import com.example.wsg.nightreading.utils.StaticClass;

public class MainActivity extends FragmentActivity {

    private RadioGroup mRadioGroup;
    private Fragment[]mFragments={new ZhiHuFragment(),new DoubanFragment(),new FriendFragment(),new UserFragment()};
    private RadioButton mRadioButtonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ShareUtils.putBoolean(this, StaticClass.SHARE_IS_LOGIN,true);

        initView();
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group_button);
        mRadioButtonHome = (RadioButton) findViewById(R.id.radio_button_home);

        mRadioButtonHome = (RadioButton) findViewById(R.id.radio_button_home);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            Fragment mFragment = null;
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.radio_button_home:
                        mFragment = mFragments[0];
                        break;
                    case R.id.radio_button_discovery:
                        mFragment = mFragments[1];
                        break;
                    case R.id.radio_button_attention:
                        mFragment = mFragments[2];
                        break;
                    case R.id.radio_button_profile:
                        mFragment = mFragments[3];
                        break;
                }
                if(mFragments!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container,mFragment).commit();
                }
            }
        });
        // 保证第一次会回调OnCheckedChangeListener
        mRadioButtonHome.setChecked(true);

    }
}
