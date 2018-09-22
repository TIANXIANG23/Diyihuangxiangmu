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
import com.example.tianxiang.diyihuangxiangmu.entity.Data;

import java.util.List;

public class CorrelationAdapter extends RecyclerView.Adapter<CorrelationAdapter.ViewHodler> {
    private Context context;
    private List<Data> list;

    public CorrelationAdapter(Context context, List<Data> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.correlation,null);
        ViewHodler viewHodler=new ViewHodler(view);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, int position) {
        Glide.with(context).load(list.get(position).getImageListThumb().get(0)).into(holder.correlation_image);
        holder.correlation_title.setText(list.get(position).getTitle());
        holder.correlation_biao.setText("公务机");
        holder.correlation_shi.setText(list.get(position).getPublishTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {

        private final TextView correlation_title;
        private final ImageView correlation_image;
        private final TextView correlation_biao;
        private final TextView correlation_shi;

        public ViewHodler(View itemView) {
            super(itemView);
            correlation_title = itemView.findViewById(R.id.correlation_title);
            correlation_image = itemView.findViewById(R.id.correlation_image);
            correlation_biao = itemView.findViewById(R.id.correlation_biao);
            correlation_shi = itemView.findViewById(R.id.correlation_shi);
        }
    }
}
