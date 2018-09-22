package com.example.tianxiang.diyihuangxiangmu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tianxiang.diyihuangxiangmu.R;
import com.example.tianxiang.diyihuangxiangmu.entity.Comment;
import com.example.tianxiang.diyihuangxiangmu.entity.CommentList;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHodler> {
    private Context context;
    private List<Comment> list;

    public HotAdapter(Context context, List<Comment> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.hotitem,null);
        ViewHodler viewHodler=new ViewHodler(view);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, int position) {
      holder.correlation_name.setText("勿忘心安");
      holder.correlation_time.setText(list.get(position).getCommentTime());
      holder.correlation_ping.setText(list.get(position).getContent());
      //holder.correlation_dian.setText(list.get(position).getCommentId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {

        private final CircleImageView correlation_circleImageView;
        private final TextView correlation_name;
        private final TextView correlation_time;
        private final TextView correlation_ping;
        private final ImageView correlation_head;
        private final TextView correlation_dian;

        public ViewHodler(View itemView) {
            super(itemView);
            correlation_circleImageView = itemView.findViewById(R.id.correlation_circleImageView);
            correlation_name = itemView.findViewById(R.id.correlation_name);
            correlation_time = itemView.findViewById(R.id.correlation_time);
            correlation_ping = itemView.findViewById(R.id.correlation_ping);
            correlation_head = itemView.findViewById(R.id.correlation_head);
            correlation_dian = itemView.findViewById(R.id.textView7);
        }
    }
}
