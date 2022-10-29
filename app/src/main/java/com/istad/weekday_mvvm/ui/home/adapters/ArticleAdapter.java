package com.istad.weekday_mvvm.ui.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.istad.weekday_mvvm.R;
import com.istad.weekday_mvvm.data.models.Article;
import com.istad.weekday_mvvm.data.models.api.response.Data;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    List<Data> dataList;
    public ArticleAdapterClickListener articleAdapterClickListener;
    Context context;

    public ArticleAdapter(List<Data> dataList, ArticleAdapterClickListener listener) {
        this.dataList = dataList;
        this.articleAdapterClickListener = listener;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_article, parent, false);
        context = parent.getContext();
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = dataList.get(position).getArticle();
        holder.title.setText(article.getTitle());
        holder.content.setText(article.getContent());

        if(article.getThumbnailData().getDataAttribute() != null)
        Glide.with(context)
                .load("https://cms.istad.co"+article.getThumbnailData().getDataAttribute().getThumbnail().getUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title, content;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);

            itemView.setOnClickListener(view -> {
                articleAdapterClickListener.onItemArticleClick(dataList.get(getAdapterPosition()).getArticle());
            });
        }
    }
}

