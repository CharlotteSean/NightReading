package com.example.wsg.nightreading.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wsg.nightreading.MainActivity;
import com.example.wsg.nightreading.R;
import com.example.wsg.nightreading.base.BaseActivity;
import com.example.wsg.nightreading.entity.MyUser;
import com.example.wsg.nightreading.view.CustomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.wsg.nightreading.R.id.et_name;
import static com.example.wsg.nightreading.R.id.et_password;

/**
 * 项目名：NightReading
 * 包名：com.example.wsg.nightreading.ui
 * 文件名：LoginActivity
 * 创建者：wsg
 * 创建时间：2017/9/5  16:50
 * 描述：登录页面
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(et_name)
    EditText etName;
    @BindView(et_password)
    EditText etPassword;
    @BindView(R.id.keep_password)
    CheckBox keepPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btn_registered)
    Button btnRegistered;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    @BindView(R.id.btnLogin_qq)
    ImageView btnLoginQq;
    @BindView(R.id.btnLogin_weibo)
    ImageView btnLoginWeibo;


    private CustomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        dialog = new CustomDialog(this, 100, 100, R.layout.dialog_loding, R.style.Theme_dialog, Gravity.CENTER,R.style.pop_anim_style);
        dialog.setCancelable(false);

    }

    @OnClick({R.id.keep_password, R.id.btnLogin, R.id.btn_registered, R.id.tv_forget, R.id.btnLogin_qq, R.id.btnLogin_weibo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.keep_password:
                break;
            case R.id.btnLogin:
                //1.获取输入框的值
                String name = etName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                //2.判断是否为空
                if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(password)) {
                    dialog.show();
                    //登录
                    final MyUser user = new MyUser();
                    user.setUsername(name);
                    user.setPassword(password);
                    user.login(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            dialog.dismiss();
                            //判断结果
                            if (e == null) {
                                //判断邮箱是否验证
                                if (user.getEmailVerified()) {
                                    //跳转
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "请前往邮箱验证", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "登录失败：" + e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_registered:
                startActivity(new Intent(this,RegisteredActivity.class));
                break;
            case R.id.tv_forget:
                startActivity(new Intent(this,ForgetPasswordActivity.class));

                break;
            case R.id.btnLogin_qq:
                Toast.makeText(LoginActivity.this, "QQ登录：" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnLogin_weibo:
                Toast.makeText(LoginActivity.this, "微博登录：", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
