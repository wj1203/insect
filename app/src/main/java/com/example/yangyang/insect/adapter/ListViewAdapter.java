package com.example.yangyang.insect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yangyang.insect.R;
import com.example.yangyang.insect.entity.MyComment;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yangyang on 2019/3/7.
 * 项目名：insect
 * 包名 ： com.example.yangyang.insect.adapter
 * 作用：
 */
public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MyComment> dataList ;

    public ListViewAdapter(Context context, ArrayList<MyComment> data) {
        this.context = context;
        this.dataList = data;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_view,parent,false);
             viewHolder = new ListViewViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.tv_comment);
            viewHolder.cv_comment_head = convertView.findViewById(R.id.cv_comment_head);
            viewHolder.tv_comment_name = convertView.findViewById(R.id.tv_comment_name);
            convertView.setTag(viewHolder);
        }else {
           viewHolder = (ListViewViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(dataList.get(position).getComment());
        Glide.with(context).load(dataList.get(position).getComment_head_uri()).into(viewHolder.cv_comment_head);
        viewHolder.tv_comment_name.setText(dataList.get(position).getComment_name());

        return convertView;
    }
    class ListViewViewHolder{
        TextView textView;
        CircleImageView cv_comment_head;
        TextView tv_comment_name;
    }
}
