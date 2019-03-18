package com.example.yangyang.insect.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import com.example.yangyang.insect.R;
import com.example.yangyang.insect.adapter.ClazzAdapter;
import com.example.yangyang.insect.adapter.GridViewAdapter;
import com.example.yangyang.insect.db.MyDataBase;
import com.example.yangyang.insect.entity.Clazz;
import com.example.yangyang.insect.entity.Insect;

import org.litepal.LitePal;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PictureFragment extends Fragment {
    private GridView grid_view;
    private static ArrayList<Insect> mList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        initData();
        initView(view);
        return view;
    }

    private void initView(View view) {
        grid_view = view.findViewById(R.id.grid_view);
        GridViewAdapter adapter = new GridViewAdapter(getContext(),mList);
        // 设置为两列
        grid_view.setNumColumns(2);
        grid_view.setAdapter(adapter);
    }

    private static void initData() {
        LitePal.deleteAll(Insect.class);
        MyDataBase.formInsectToDB();
        mList = (ArrayList<Insect>) LitePal.findAll(Insect.class);


    }



}
