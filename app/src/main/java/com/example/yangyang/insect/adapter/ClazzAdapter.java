package com.example.yangyang.insect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yangyang.insect.R;
import com.example.yangyang.insect.entity.Clazz;

import java.util.ArrayList;

/**
 * Created by yangyang on 2018/12/7.
 * 项目名：insect
 * 包名 ： com.example.yangyang.insect.adapter
 * 作用：  昆虫目的适配器
 */
public class ClazzAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Clazz> mList;
    private LayoutInflater inflater ;
    public ClazzAdapter(Context context, ArrayList<Clazz> list){
        mContext = context;
        mList = list;
        inflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            // 1先实例化两个空对象
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.insect_class_item,parent,false);
            // 2控件存放到viewHolder中
           viewHolder.iv_class = convertView.findViewById(R.id.iv_class);
           viewHolder.tv_class = convertView.findViewById(R.id.tv_class);
            // 3 保存viewHolder
            convertView.setTag(viewHolder);
        }else {
            // 4 取出viewHolder
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // 5 取出当前实例对象
        Clazz clazz = mList.get(position);
        // 6 赋值+return
        viewHolder.iv_class.setImageResource(clazz.getImg());
        viewHolder.tv_class.setText(clazz.getName());
        return convertView;
    }
    class ViewHolder{
        ImageView iv_class;
        TextView tv_class;
    }

}
