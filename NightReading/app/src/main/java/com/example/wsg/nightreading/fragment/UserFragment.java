package com.example.wsg.nightreading.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wsg.nightreading.R;
import com.example.wsg.nightreading.entity.MyUser;
import com.example.wsg.nightreading.ui.AboutActivity;
import com.example.wsg.nightreading.ui.DonateActivity;
import com.example.wsg.nightreading.ui.LoginActivity;
import com.example.wsg.nightreading.ui.ShareActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 项目名：NightReading
 * 包名：com.example.wsg.nightreading.fragment
 * 文件名：UserFragment
 * 创建者：wsg
 * 创建时间：2017/9/6  19:39
 * 描述：个人中心
 */

public class UserFragment extends Fragment {
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.edit_user)
    TextView editUser;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_sex)
    EditText etSex;
    @BindView(R.id.et_age)
    EditText etAge;
    @BindView(R.id.et_desc)
    EditText etDesc;
    @BindView(R.id.btn_update_ok)
    Button btnUpdateOk;
    @BindView(R.id.tv_night)
    TextView tvNight;
    @BindView(R.id.tv_about)
    TextView tvAbout;
    @BindView(R.id.btn_exit_user)
    Button btnExitUser;
    Unbinder unbinder;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_donate)
    TextView tvDonate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, null);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
//        etUsername.setText();
//        etSex.setText();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.profile_image, R.id.edit_user, R.id.btn_update_ok, R.id.tv_night, R.id.tv_about, R.id.btn_exit_user,R.id.tv_share,R.id.tv_donate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.profile_image:
                break;
            case R.id.edit_user:
                break;
            case R.id.btn_update_ok:
                break;
            case R.id.tv_night:
                break;
            case R.id.tv_about:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;


            case R.id.btn_exit_user:
                //清除缓存用户对象
                MyUser.logOut();
                // 现在的currentUser是null了
                BmobUser currentUser = MyUser.getCurrentUser();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
            case R.id.tv_share:
                startActivity(new Intent(getActivity(), ShareActivity.class));
                break;
            case R.id.tv_donate:
                startActivity(new Intent(getActivity(), DonateActivity.class));
                break;
        }
    }




}
