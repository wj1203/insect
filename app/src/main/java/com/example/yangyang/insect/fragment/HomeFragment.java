package com.example.yangyang.insect.fragment;


import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.yangyang.insect.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements LocationSource {
    private MapView mapView;
    //初始化地图控制器对象
    private AMap aMap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ActivityCompat.requestPermissions(getActivity(),new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
        },1);

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        initView(view);
        initMarker();
        return view;
    }

    private void initMarker() {
        addMarker(31.908802,118.897339,"111",R.layout.marker_one);
        addMarker(31.9065,118.898954,"333",R.layout.marker_two);
        addMarker(31.906712,118.900531,"444",R.layout.marker_three);
        addMarker(31.907518,118.89956,"222",R.layout.marker_four);
        addMarker(31.902982,118.899796,"555",R.layout.marker_five);
    }
    //  内部封装一个添加marker的函数
    private void addMarker(double lat,double lng,String title,int viewPath){
        MarkerOptions options = new MarkerOptions();
        options.position(new LatLng(lat,lng));
        options.title(title);
        ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(viewPath,mapView,false);
        options.icon(BitmapDescriptorFactory.fromView(imageView));
        aMap.addMarker(options);
    }


    private void initView(View view) {
        mapView = view.findViewById(R.id.mapView);
        if (aMap == null){
            aMap = mapView.getMap();
        }
         // 蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.interval(1);
        myLocationStyle.strokeWidth(1);
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
        // 指南针
        aMap.getUiSettings().setCompassEnabled(true);
        // 绑定蓝点
        aMap.setMyLocationStyle(myLocationStyle);
        // 当前位置
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
         // 显示蓝点
        aMap.setMyLocationEnabled(true);

    }


    //   绑定map的生命周期

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void activate(OnLocationChangedListener listener) {



    }

    @Override
    public void deactivate() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
