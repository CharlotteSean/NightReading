package com.example.wsg.nightreading.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：NightReading
 * 包名：com.example.wsg.nightreading.ui
 * 文件名：LoginActivity
 * 创建者：wsg
 * 创建时间：2017/9/12  18:05
 * 描述：重新 创建登录页面 解决butterknife无响应
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {



    private Tencent mTencent;
    private String APP_ID = "1106410774";
    private IUiListener loginListener;
    private String SCOPE = "all";
    private IUiListener userInfoListener;


    //注册按钮
    private Button btn_registered;
    private EditText et_name;
    private EditText et_password;
    private Button btnLogin;
    private CheckBox keep_password;

    private TextView tv_forget;
    private CustomDialog dialog;

    //QQ登录按钮
    private ImageView iv_login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    //初始化控件
    private void initView() {
        btn_registered = (Button) findViewById(R.id.btn_registered);
        btn_registered.setOnClickListener(this);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        keep_password = (CheckBox) findViewById(R.id.keep_password);
        tv_forget = (TextView) findViewById(R.id.tv_forget);
        tv_forget.setOnClickListener(this);


        iv_login=(ImageView)findViewById(R.id.btnLogi_qq);
        iv_login.setOnClickListener(this);


        dialog = new CustomDialog(this, 100, 100, R.layout.dialog_loding, R.style.Theme_dialog, Gravity.CENTER,R.style.pop_anim_style);
        dialog.setCancelable(false);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forget:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            case R.id.btn_registered:
                startActivity(new Intent(this, RegisteredActivity.class));
                break;
            case R.id.btnLogin:
                //1.获取输入框的值
                String name = et_name.getText().toString().trim();
                String password = et_password.getText().toString().trim();
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
                                Toast.makeText(LoginActivity.this, "登录失败：请检查登录信息或稍后重试"+e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnLogi_qq:

//                Toast.makeText(LoginActivity.this, "QQ登录：" , Toast.LENGTH_SHORT).show();
                initQqLogin();
                mTencent.login(this, SCOPE, loginListener);
                break;
        }

    }





    //初始化QQ登录分享的需要的资源
    private void initQqLogin() {
        mTencent = Tencent.createInstance(APP_ID, this);


        //创建QQ登录回调接口
        loginListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                //登录成功后回调该方法
//                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();



                //登录成功后调用的方法
                JSONObject jo = (JSONObject) o;
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                //注册
                final MyUser user = new MyUser();
                user.setUsername("夜读读友");
                user.setPassword("123456");
                user.setAge(0);
                user.setSex(true);
                user.setDesc("这个人很懒，什么都没留下");
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onError(UiError uiError) {
                //登录失败后回调该方法
                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                Log.e("LoginError:", uiError.toString());
            }

            @Override
            public void onCancel() {
                //取消登录后回调该方法
                Toast.makeText(LoginActivity.this, "取消登录", Toast.LENGTH_SHORT).show();
            }
        };

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//  官方文档上面的是错误的
//        if(requestCode == Constants.REQUEST_API) {
//            if(resultCode == Constants.RESULT_LOGIN) {
//                mTencent.handleLoginData(data, loginListener);
//            }
//  resultCode 是log出来的，官方文档里给的那个属性是没有的

        if (requestCode == Constants.REQUEST_LOGIN) {
            if (resultCode == -1) {
                Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
                Tencent.handleResultData(data, loginListener);
            }
        }
    }



















    


}
