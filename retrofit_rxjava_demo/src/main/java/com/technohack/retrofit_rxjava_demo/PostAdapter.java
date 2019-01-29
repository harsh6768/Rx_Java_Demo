package com.technohack.retrofit_rxjava_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technohack.retrofit_rxjava_demo.model.PostModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    Context context ;
    List<PostModel> postModelList;

    public PostAdapter(Context context, List<PostModel> postModelList) {
        this.context = context;
        this.postModelList = postModelList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.post_list_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.postAuthor.setText(String.valueOf(postModelList.get(position).getUserId()));
        holder.postTitle.setText(postModelList.get(position).getTitle());
        holder.postContent.setText(postModelList.get(position).getBody().substring(0,30));

    }
    @Override
    public int getItemCount() {
        return postModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView postTitle,postAuthor,postContent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            postAuthor=itemView.findViewById(R.id.post_authodId);
            postTitle=itemView.findViewById(R.id.post_titleId);
            postContent=itemView.findViewById(R.id.post_contentId);

        }
    }
}
