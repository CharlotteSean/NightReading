package com.example.wsg.nightreading.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wsg.nightreading.R;
import com.example.wsg.nightreading.base.BaseActivity;
import com.example.wsg.nightreading.entity.MyUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import static com.example.wsg.nightreading.R.id.et_email;
import static com.example.wsg.nightreading.R.id.et_new;
import static com.example.wsg.nightreading.R.id.et_new_password;
import static com.example.wsg.nightreading.R.id.et_now;

/**
 * 项目名：NightReading
 * 包名：com.example.wsg.nightreading.ui
 * 文件名：ForgetPasswordActivity
 * 创建者：wsg
 * 创建时间：2017/9/6  16:30
 * 描述：忘记密码
 */

public class ForgetPasswordActivity extends BaseActivity {

    @BindView(et_now)
    EditText etNow;
    @BindView(et_new)
    EditText etNew;
    @BindView(et_new_password)
    EditText etNewPassword;
    @BindView(R.id.btn_update_password)
    Button btnUpdatePassword;
    @BindView(et_email)
    EditText etEmail;
    @BindView(R.id.btn_forget_password)
    Button btnForgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_passwprd);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_update_password, R.id.btn_forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //修改密码的逻辑
            case R.id.btn_update_password:

                //1.获取输入框的值
                String now = etNow.getText().toString().trim();
                String news = etNew.getText().toString().trim();
                String new_password = etNewPassword.getText().toString();
                //2.判断是否为空
                if(!TextUtils.isEmpty(now) & !TextUtils.isEmpty(news) & !TextUtils.isEmpty(new_password)){
                    //3.判断两次新密码是否一致
                    if(news.equals(new_password)){
                        //4.重置密码
                        MyUser.updateCurrentUserPassword(now, news, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e == null){
                                    Toast.makeText(ForgetPasswordActivity.this,
                                            R.string.reset_successfully, Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Toast.makeText(ForgetPasswordActivity.this, R.string.reset_failed, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(this, getString(R.string.text_two_input_not_consistent), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, getString(R.string.text_tost_empty), Toast.LENGTH_SHORT).show();
                }






                break;
            //忘记密码的逻辑
            case R.id.btn_forget_password:

                //1.获取输入框的邮箱
                final String email = etEmail.getText().toString().trim();
                //2.判断是否为空
                if (!TextUtils.isEmpty(email)) {
                    //3.发送邮件
                    MyUser.resetPasswordByEmail(email, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(ForgetPasswordActivity.this,
                                        getString(R.string.text_email_send_ok) + email, Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(ForgetPasswordActivity.this,
                                        R.string.text_email_send_no, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, getString(R.string.text_tost_empty), Toast.LENGTH_SHORT).show();
                }



                break;
        }
    }
}
