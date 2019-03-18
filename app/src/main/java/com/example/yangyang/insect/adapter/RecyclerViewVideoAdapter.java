package com.example.yangyang.insect.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yangyang.insect.R;
import com.example.yangyang.insect.custom.MyListView;
import com.example.yangyang.insect.entity.MyComment;
import com.example.yangyang.insect.entity.Video;
import com.example.yangyang.insect.util.ToastUtil;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Collections;

import de.hdodenhof.circleimageview.CircleImageView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by yangyang on 2019/3/7.
 * 项目名：insect
 * 包名 ： com.example.yangyang.insect.adapter
 * 作用：
 */
public class RecyclerViewVideoAdapter extends RecyclerView.Adapter<RecyclerViewVideoAdapter.VideoViewHolder> {
    private static ArrayList<Video> dataList;
    private Context context;


    static class VideoViewHolder extends RecyclerView.ViewHolder{
        JCVideoPlayerStandard jc_video;
        CircleImageView cv_head;
        TextView tv_name;
        ImageView iv_zan;
        ImageView iv_comment;
        MyListView lv_comment;
        boolean isZan = false;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            jc_video = itemView.findViewById(R.id.jc_video);
            cv_head = itemView.findViewById(R.id.cv_head);
            tv_name = itemView.findViewById(R.id.tv_name);
            iv_zan = itemView.findViewById(R.id.iv_zan);
            iv_comment = itemView.findViewById(R.id.iv_comment);
            lv_comment = itemView.findViewById(R.id.lv_comment);
        }
    }

    public RecyclerViewVideoAdapter(Context context,ArrayList data){
        dataList = data;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_video,viewGroup,false);
        final RecyclerViewVideoAdapter.VideoViewHolder viewHolder = new RecyclerViewVideoAdapter.VideoViewHolder(view);
        // zan图标的点击事件
        viewHolder.iv_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.iv_zan && viewHolder.isZan == false){
                    ToastUtil.toast(context,"点赞成功(～￣▽￣)～");
                    // 已赞
                    viewHolder.isZan = true;
                    // 加黑
                    viewHolder.iv_zan.setImageResource(R.drawable.darkzan);
                    // 动画
                    ScaleAnimation animation = new ScaleAnimation(1.0f,1.5f,1.0f,1.5f);
                    animation.setDuration(250);
                    viewHolder.iv_zan.startAnimation(animation);
                }else {
                    ToastUtil.toast(context,"已经点过赞，不能反悔哦！\n≡(▔﹏▔)≡");
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder viewHolder, int i) {
        Video video = dataList.get(i);
        viewHolder.jc_video.setUp(video.getVideo_uri(),JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,video.getIntroduce());
        Glide.with(context).load(video.getLoading_uri()).into(viewHolder.jc_video.thumbImageView);
        viewHolder.jc_video.startVideo();
        // circleView
        Glide.with(context).load(video.getHead_uri()).into(viewHolder.cv_head);
        // change name
        viewHolder.tv_name.setText(video.getName());
        // zan

        // MyComment
        ArrayList<MyComment> myComments = (ArrayList<MyComment>) LitePal.findAll(MyComment.class);
        if (i >0){
            Collections.shuffle(myComments);
        }
        ListViewAdapter listViewAdapter = new ListViewAdapter(context,myComments);
        viewHolder.lv_comment.setAdapter(listViewAdapter);
        viewHolder.jc_video.startVideo();
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
