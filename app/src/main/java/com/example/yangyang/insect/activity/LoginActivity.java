package com.example.yangyang.insect.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yangyang.insect.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_username;
    private EditText et_password;
    private TextView tv_forget_password;
    private Button btn_login;
    private TextView tv_sign_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        tv_forget_password = findViewById(R.id.tv_forget_password);
        btn_login = findViewById(R.id.btn_login);
        tv_sign_in = findViewById(R.id.tv_sign_in);

        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login :
                Intent  intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_forget_password :

                break;
            case R.id.tv_sign_in :

                break;
        }
    }

}
