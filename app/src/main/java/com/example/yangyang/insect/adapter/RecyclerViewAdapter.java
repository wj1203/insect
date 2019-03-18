package com.example.yangyang.insect.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yangyang.insect.R;
import com.example.yangyang.insect.activity.PassageActivity;
import com.example.yangyang.insect.entity.Passage;

import java.util.ArrayList;

/**
 * Created by yangyang on 2019/3/4.
 * 项目名：insect
 * 包名 ： com.example.yangyang.insect.adapter
 * 作用：
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static ArrayList<Passage> dataList;
    private Context context;


    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.text_view);
        }
    }

    public RecyclerViewAdapter(Context context,ArrayList data){
        dataList = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,  int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_passage,viewGroup,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        // item的点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,PassageActivity.class);
                int j = viewHolder.getLayoutPosition();
                intent.putExtra("passage_uri",dataList.get(j).getPassage_uri());
                context.startActivity(intent);
            }
        });
        viewHolder.textView.setTag(dataList.get(i).getPassage_uri());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Passage passage = dataList.get(i);
        Glide.with(context).load(passage.getHead_uri()).into(viewHolder.imageView);
        viewHolder.textView.setText(passage.getTitle());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
