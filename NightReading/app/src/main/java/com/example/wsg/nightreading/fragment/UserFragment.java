package com.example.wsg.nightreading.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wsg.nightreading.R;
import com.example.wsg.nightreading.ui.AboutActivity;
import com.example.wsg.nightreading.ui.DonateActivity;



/**
 * 项目名：NightReading
 * 包名：com.example.wsg.nightreading.fragment
 * 文件名：UserFragment
 * 创建者：wsg
 * 创建时间：2017/9/6  19:39
 * 描述：个人中心
 */

public class UserFragment extends Fragment implements View.OnClickListener {





    private TextView tv_about;
    private TextView tv_donate;

    private static UserFragment i = new UserFragment();
    //3.提供一个用于获取实例的方法，使用public static修饰
    public static UserFragment getInstance() {
        return i;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tv_about=(TextView)view.findViewById(R.id.tv_about);
        tv_about.setOnClickListener(this);
        tv_donate=(TextView)view.findViewById(R.id.tv_donate);
        tv_donate.setOnClickListener(this);
    }






    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.tv_about:
                startActivity(new Intent(getActivity(),AboutActivity.class));
                break;
            case R.id.tv_donate:
                startActivity(new Intent(getActivity(),DonateActivity.class));
                break;

        }

    }

}
