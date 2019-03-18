package com.example.yangyang.insect.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangyang.insect.R;
import com.example.yangyang.insect.fragment.FindFragment;
import com.example.yangyang.insect.fragment.HomeFragment;
import com.example.yangyang.insect.fragment.PictureFragment;
import com.example.yangyang.insect.fragment.RecognitionFragment;
import com.example.yangyang.insect.fragment.VideoFragment;
import com.example.yangyang.insect.util.ToastUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    private RelativeLayout rl_fragment;
    private RelativeLayout rl_home;
    private RelativeLayout rl_find;
    private RelativeLayout rl_camera;
    private RelativeLayout rl_video;
    private RelativeLayout rl_picture;


    private TextView tv_home;
    private TextView tv_find;
    private TextView tv_camera;
    private TextView tv_video;
    private TextView tv_picture;

    HomeFragment homeFragment ;
    PictureFragment pictureFragment;
    RecognitionFragment recognitionFragment;
    FragmentManager fragmentManager;
    FindFragment findFragment;
    VideoFragment videoFragment;
    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 申请权限
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        homeFragment = new HomeFragment();
        findFragment = new FindFragment();
        recognitionFragment = new RecognitionFragment();
        videoFragment = new VideoFragment();
        pictureFragment = new PictureFragment();
        initView();

        // 使用toolbar代替actionbar
        setSupportActionBar(toolbar);
        //  显示homeasup
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.home);
        }

        // 设置默认显示的fragment
//        homeFragment = new HomeFragment();
//        fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.rl_fragment,homeFragment);
//        transaction.commit();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerlayout);

        rl_fragment = findViewById(R.id.rl_fragment);
        rl_home = findViewById(R.id.rl_home);
        rl_find = findViewById(R.id.rl_find);
        rl_camera = findViewById(R.id.rl_recognition);
        rl_video = findViewById(R.id.rl_video);
        rl_picture = findViewById(R.id.rl_picture);

        tv_home = findViewById(R.id.tv_home);
        tv_find = findViewById(R.id.tv_find);
        tv_camera = findViewById(R.id.tv_camera);
        tv_video = findViewById(R.id.tv_video);
        tv_picture = findViewById(R.id.tv_picture);

        rl_home.setOnClickListener(this);
        rl_find.setOnClickListener(this);
        rl_camera.setOnClickListener(this);
        rl_video.setOnClickListener(this);
        rl_picture.setOnClickListener(this);

        // 第一次加载fragment
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.rl_fragment,homeFragment);
        transaction.add(R.id.rl_fragment,findFragment);
        transaction.add(R.id.rl_fragment,recognitionFragment);
        transaction.add(R.id.rl_fragment,videoFragment);
        transaction.add(R.id.rl_fragment,pictureFragment);
        transaction.hide(findFragment);
        transaction.hide(recognitionFragment);
        transaction.hide(videoFragment);
        transaction.hide(pictureFragment);
        transaction.commit();
        currentFragment = homeFragment;
        tv_home.setTextColor(Color.parseColor("#00574B"));

    }

    @Override       // homeAsUp的点击事件
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.START);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (v.getId()){
            case R.id.rl_home :
                if(homeFragment == null){
                    homeFragment = new HomeFragment();
                }
                transaction.hide(currentFragment).show(homeFragment);
                transaction.commit();
                currentFragment = homeFragment;
                // 突出选项
                tv_home.setTextColor(Color.parseColor("#00574B"));
                tv_find.setTextColor(Color.BLACK);
                tv_picture.setTextColor(Color.BLACK);
                tv_video.setTextColor(Color.BLACK);
                break;

            case R.id.rl_find :
                if(findFragment ==  null){
                    findFragment = new FindFragment();
                }
                transaction.hide(currentFragment).show(findFragment);
                transaction.commit();
                currentFragment = findFragment;
                // 突出选项
                tv_find.setTextColor(Color.parseColor("#00574B"));
                tv_home.setTextColor(Color.BLACK);
                tv_video.setTextColor(Color.BLACK);
                tv_picture.setTextColor(Color.BLACK);
                break;

            case R.id.rl_recognition:
                if(recognitionFragment ==  null){
                    recognitionFragment = new RecognitionFragment();
                }
                transaction.hide(currentFragment).show(recognitionFragment);
                transaction.commit();
                currentFragment = recognitionFragment;
                // 突出选项

                break;
            case R.id.rl_video:
                // 切换fragment
                if(videoFragment == null){
                    videoFragment = new VideoFragment();
                }
                transaction.hide(currentFragment).show(videoFragment);
                transaction.commit();
                currentFragment = videoFragment;
                // 突出选项
                tv_video.setTextColor(Color.parseColor("#00574B"));
                tv_home.setTextColor(Color.BLACK);
                tv_find.setTextColor(Color.BLACK);
                tv_picture.setTextColor(Color.BLACK);

                break;

            case R.id.rl_picture :
                if (pictureFragment == null){
                    pictureFragment = new PictureFragment();
                }
                transaction.hide(currentFragment).show(pictureFragment);
                transaction.commit();
                currentFragment = pictureFragment;
                // 突出选项
                tv_picture.setTextColor(Color.parseColor("#00574B"));
                tv_home.setTextColor(Color.BLACK);
                tv_find.setTextColor(Color.BLACK);
                tv_video.setTextColor(Color.BLACK);
                break;
        }
    }

    // 权限回调


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){

            case 1:

                break;
        }
    }
}
