package com.example.wsg.nightreading.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wsg.nightreading.R;

/**
 * 项目名：NightReading
 * 包名：com.example.wsg.nightreading.fragment
 * 文件名：FriendFragment
 * 创建者：wsg
 * 创建时间：2017/9/6  19:54
 * 描述：TODO
 */

public class FriendFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, null);
        findView(view);
        return view;
    }

    private void findView(View view) {
    }
}
