package com.example.yangyang.insect.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yangyang.insect.R;
import com.example.yangyang.insect.activity.InsectKindActivity;
import com.example.yangyang.insect.entity.Insect;

import java.util.ArrayList;

/**
 * Created by yangyang on 2019/3/5.
 * 项目名：insect
 * 包名 ： com.example.yangyang.insect.adapter
 * 作用：
 */
public class GridViewAdapter extends BaseAdapter {

    private ArrayList<Insect> data;
    private Context context;
    public GridViewAdapter(Context context, ArrayList data){
        this.data = data;
        this.context = context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_insect,parent,false);
            viewHolder.imageView = convertView.findViewById(R.id.image_view);
            viewHolder.textView = convertView.findViewById(R.id.text_view);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(data.get(position).getName());
        Glide.with(context).load(data.get(position).getHead_uri()).into(viewHolder.imageView);
        // item 点击事件
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,InsectKindActivity.class);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });


        return convertView;
    }

    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
