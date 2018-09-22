package com.example.tianxiang.diyihuangxiangmu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tianxiang.diyihuangxiangmu.R;
import com.example.tianxiang.diyihuangxiangmu.data.remote.News;
import com.example.tianxiang.diyihuangxiangmu.entity.NewList;
import com.example.tianxiang.diyihuangxiangmu.loginfragment.informationtwo.zifragment.TabContract;
import com.example.tianxiang.diyihuangxiangmu.loginfragment.informationtwo.zifragment.webview.Webjie;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InformationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public Context context;
    private List<News> mData;
    private Webjie webjie;
    private static final int TYPE_TITLE = 0;
    private static final int TYPE_RIGHT_IMG = 1;
    private static final int TYPE_BIG_IMG = 2;
    private static final int TYPE_THREE_IMG = 3;
    public InformationAdapter(Context context, List<News> mData) {
        this.context = context;
        this.mData = mData;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==TYPE_TITLE){
            View view=View.inflate(context,R.layout.item1,null);
            ViewHodle2 viewHodle2=new ViewHodle2(view);
        return viewHodle2;
        }else if(viewType==TYPE_RIGHT_IMG){
            View view=View.inflate(context,R.layout.item,null);
            ViewHodle1 viewHodle1=new ViewHodle1(view);
            return viewHodle1;
        }else if(viewType==TYPE_BIG_IMG){
            View view=View.inflate(context,R.layout.item3,null);
            ViewHodle4 viewHodle4=new ViewHodle4(view);
            return viewHodle4;
        }else if(viewType==TYPE_THREE_IMG){
            View view=View.inflate(context,R.layout.item2,null);
            ViewHodle3 viewHodle3=new ViewHodle3(view);
            return viewHodle3;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        News news = mData.get(position);
        if(holder instanceof ViewHodle2){
            ((ViewHodle2) holder).title.setText(news.getTitle());
            ((ViewHodle2) holder).gentie2.setText(news.getPageviews()+"跟帖");
        }else if (holder instanceof ViewHodle1){
            Glide.with(context).load(news.getImageListThumb().get(0)).into(((ViewHodle1) holder).imageView);
            ((ViewHodle1) holder).title.setText(news.getTitle());
            ((ViewHodle1) holder).gentie1.setText(news.getPageviews()+"跟帖");
        }else if (holder instanceof ViewHodle4){
            Glide.with(context).load(news.getImageListThumb().get(0)).into(((ViewHodle4) holder).imageView);
            ((ViewHodle4) holder).title.setText(news.getTitle());
            ((ViewHodle4) holder).gentie4.setText(news.getPageviews()+"跟帖");
        }else if (holder instanceof ViewHodle3){
            Glide.with(context).load(news.getImageListThumb().get(0)).into(((ViewHodle3) holder).imageView1);
            Glide.with(context).load(news.getImageListThumb().get(0)).into(((ViewHodle3) holder).imageView2);
            Glide.with(context).load(news.getImageListThumb().get(0)).into(((ViewHodle3) holder).imageView3);
         ((ViewHodle3) holder).title.setText(news.getTitle());
         ((ViewHodle3) holder).gentie3.setText(news.getPageviews()+"跟帖");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webjie.show(position);
            }
        });

    }

    public void setInformationAdapter(Webjie webjie) {
        this.webjie = webjie;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class ViewHodle1 extends RecyclerView.ViewHolder{
        private final ImageView imageView;
        private final ImageView zhiding;
        private final ImageView cha;
        private final TextView title;
        private final TextView biaoti;
        private final TextView gentie1;
        private final TextView shijian;

        public ViewHodle1(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itm_image);
            title = itemView.findViewById(R.id.itm_title);
            zhiding = itemView.findViewById(R.id.itm_zhiding);
            biaoti = itemView.findViewById(R.id.itm_xin);
            gentie1 = itemView.findViewById(R.id.itm_gentie);
            shijian = itemView.findViewById(R.id.itm_shijian);
            cha = itemView.findViewById(R.id.itm_cha);
        }
    }
    public class ViewHodle2 extends RecyclerView.ViewHolder{

        private final TextView title;
        private final TextView biaoti;
        private final TextView gentie2;
        private final TextView shijian;
        private final ImageView zhidint;
        private final ImageView cha;

        public ViewHodle2(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itm1_title);
            biaoti = itemView.findViewById(R.id.itm1_xin);
            gentie2 = itemView.findViewById(R.id.itm1_gentie);
            shijian = itemView.findViewById(R.id.itm1_shijian);
            zhidint = itemView.findViewById(R.id.itm1_zhiding);
            cha = itemView.findViewById(R.id.itm1_cha);
        }
    }
    public class ViewHodle3 extends RecyclerView.ViewHolder{

        private final TextView title;
        private final ImageView imageView1;
        private final ImageView imageView2;
        private final ImageView imageView3;
        private final TextView biaoti;
        private final TextView gentie3;
        private final TextView shijian;
        private final ImageView cha;

        public ViewHodle3(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itm2_title);
            imageView1 = itemView.findViewById(R.id.itm2_image);
            imageView2 = itemView.findViewById(R.id.itm2_image1);
            imageView3 = itemView.findViewById(R.id.itm2_image2);
            biaoti = itemView.findViewById(R.id.itm2_xin);
            gentie3 = itemView.findViewById(R.id.itm2_gentie);
            shijian = itemView.findViewById(R.id.itm2_shijian);
            cha = itemView.findViewById(R.id.itm2_cha);


        }
    }
    public class ViewHodle4 extends RecyclerView.ViewHolder{

        private final TextView title;
        private final ImageView imageView;
        private final ImageView zhiding;
        private final TextView biaoti;
        private final TextView gentie4;
        private final TextView shijian;
        private final ImageView cha;

        public ViewHodle4(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itm3_title);
            imageView = itemView.findViewById(R.id.itm3_image);
            zhiding = itemView.findViewById(R.id.itm3_zhiding);
            biaoti = itemView.findViewById(R.id.itm3_xin);
            gentie4 = itemView.findViewById(R.id.itm3_gentie1);
            shijian = itemView.findViewById(R.id.itm3_shijian);
            cha = itemView.findViewById(R.id.itm3_cha);
        }
    }

    @Override
    public int getItemViewType(int position) {

        switch (mData.get(position).getLayoutType()){
            case 0:
                return TYPE_TITLE;
            case 1:
                return TYPE_RIGHT_IMG;
            case 2:
                return TYPE_BIG_IMG;
            case 3:
                return TYPE_THREE_IMG;
        }

        return TYPE_TITLE;
    }
}
