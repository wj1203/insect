package com.example.yangyang.insect.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.baidu.ai.aip.utils.Base64Util;
import com.baidu.ai.aip.utils.HttpUtil;
import com.example.yangyang.insect.R;
import com.example.yangyang.insect.activity.MainActivity;
import com.example.yangyang.insect.activity.ShowActivity;
import com.example.yangyang.insect.entity.JsonAccess;
import com.example.yangyang.insect.util.ToastUtil;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecognitionFragment extends Fragment implements View.OnClickListener {

    private ImageButton imgbtn_recognition;
    private ImageView imageView;
    public static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    public static final int REQUEST_PERMISSSION_CAMERA = 3;
    private Uri imageUri;
    public static File tempFile;
    private ContentValues contentValues;
    private Intent intent;
    MyHandler myHandler = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_msg, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgbtn_recognition :
                openCamera();
                break;
        }
    }
    // 打开相机
    private void openCamera() {
        //獲取系統版本
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        // 创建隐式调用相机的intent
         intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            // 取得当前时间命名文件
            SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            String filename = timeStampFormat.format(new Date());
            //  创建存放照片的文件
            tempFile = new File(Environment.getExternalStorageDirectory(), filename + ".jpg");
            if (currentapiVersion < 24) {
                // 从文件中创建uri
                imageUri = Uri.fromFile(tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            } else {
                //兼容android7.0 使用共享文件的形式
                contentValues = new ContentValues(1);
                contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());
                //检查是否有存储权限，以免崩溃
                if ( ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE )
                        != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.CAMERA)
                        !=PackageManager.PERMISSION_GRANTED ) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},
                            REQUEST_PERMISSSION_CAMERA);
                }else {     // 有权限
                    takePicture(intent,contentValues);
                }

            }
        }
    }
    // 拍照
    private void takePicture(Intent intent,ContentValues contentValues) {
        imageUri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
    }
    // 处理活动回调
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PHOTO_REQUEST_CAREMA :  // 处理相机的回调
                if (resultCode == RESULT_OK){
                    // 处理照片
                    try {
                        // 照片的bitmap
                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageUri));
                        // 识别
                        identify(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    // 自定义handler接收子线程传回来的result
                     myHandler = new MyHandler();

//                    try {
//                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageUri));
//                        imageView.setImageBitmap(bitmap);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }

                }
                break;
        }

    }
    // 识别
    String access_token ;
    String param;
    private void identify(Bitmap bitmap) {
        // 压缩到0.1 拿到base64编码,合成param
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, bStream);
        byte[] bytes = bStream.toByteArray();
        String base64 = Base64Util.encode(bytes);
        try {
            String image_param = URLEncoder.encode(base64,"UTF-8");
             param = "image=" + image_param + "&top_num=" + 6;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 获得access token 24.1b561ba2365e2ab484e69e953f09a48c.2592000.1555152863.282335-15652740
        String request_access_token = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=aHWDOH5MERuoXkwUIOtbwU7j&client_secret=2QbMhquS9aRY2Gd4hpjlU8SXpIGfC3zm&";
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .get()
                .url(request_access_token)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ToastUtil.toast(getContext(),"access__token请求失败"+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response)  {
                // 解析拿到acces_stoken
                String jsonString = null;
                try {
                    jsonString = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("tag",jsonString);
                Gson gson = new Gson();
                JsonAccess jsonAccess  = gson.fromJson(jsonString ,JsonAccess.class);
                 access_token = jsonAccess.access_token;
            }
        });

        // 开启子线程上传base64
        new Thread(new Runnable() {
            String url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/animal";
                @Override
                public void run() {
                    try {
                        String result = HttpUtil.post(url,"24.1b561ba2365e2ab484e69e953f09a48c.2592000.1555152863.282335-15652740",param);
                            // bundle用于传递线程数据
                        Bundle bundle = new Bundle();
                        bundle.putString("result",result);
                        // 通过message发送到主线程
                        Message message = new Message();
                        message.setData(bundle);
                        message.what = 1;
                        myHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
    }

     class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 取出bundle得到结果
            Bundle bundle = msg.getData();
            String result = bundle.getString("result");
            Intent intent = new Intent(getContext(),ShowActivity.class);
            intent.putExtra("result",result);
            getContext().startActivity(intent);
        }

    }




    private boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
    private void initView(View view) {
        imgbtn_recognition = view.findViewById(R.id.imgbtn_recognition);
        imgbtn_recognition.setOnClickListener(this);

        imageView = view.findViewById(R.id.img);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_PERMISSSION_CAMERA :
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    takePicture(intent,contentValues);
                }
                break;
        }
    }

}
