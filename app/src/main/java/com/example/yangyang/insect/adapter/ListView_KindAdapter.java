package com.example.yangyang.insect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yangyang.insect.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by yangyang on 2019/3/18.
 * 项目名：insect
 * 包名 ： com.example.yangyang.insect.adapter
 * 作用：
 */
public class ListView_KindAdapter extends BaseAdapter {
    Context context ;
    ArrayList<Map> data ;

    public ListView_KindAdapter(Context context, ArrayList<Map> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_passage,parent,false);
            viewHolder.imageView = convertView.findViewById(R.id.image_view);
            viewHolder.textView = convertView.findViewById(R.id.text_view);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(data.get(position).get("name")+"");
        Glide.with(context).load(data.get(position).get("uri")).into(viewHolder.imageView);

        // 点击事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
