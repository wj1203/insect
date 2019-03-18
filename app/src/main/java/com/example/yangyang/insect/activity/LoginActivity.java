package com.example.yangyang.insect.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.load.engine.Resource;
import com.example.yangyang.insect.R;
import com.example.yangyang.insect.util.SMSUtil;
import com.example.yangyang.insect.util.ToastUtil;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_username;
    private EditText et_password;
    private Button btn_login;
    private TextView tv_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        tv_send = findViewById(R.id.tv_send);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
        tv_send.setOnClickListener(this);
//      et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login :
                if (et_password.getText().toString().equals(SMSUtil.getSMScode())){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }else {
                    ToastUtil.toast(getApplicationContext(),"请输入正确的验证码");
                }
                break;

            case R.id.tv_send:
                String number = et_username.getText().toString();
                if (number.isEmpty()){
                    ToastUtil.toast(getApplicationContext(),"号码为空");
                }else {       // 号码不为空
                    SMSUtil.sendSMS(number);
                    // 设置不可点击
                    tv_send.setClickable(false);
                    // 改变背景
                    tv_send.setBackground(getDrawable(R.drawable.send_gray_bg));
                    // 倒计时
                    CountDownTimer timer = new CountDownTimer(30*1000,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            tv_send.setText(millisUntilFinished/1000+"s");
                        }

                        @Override
                        public void onFinish() {
                            tv_send.setBackground(getDrawable(R.drawable.send_background));
                            tv_send.setTextSize(10);
                            tv_send.setText("重新发送");
                            tv_send.setClickable(true);
                        }
                    }.start();
                }
                break;

        }
    }


}
