package com.istad.weekday_mvvm.data.models.api.request;

import com.google.gson.annotations.SerializedName;

public class ArticleRequest {

    @SerializedName("data")
    private ArticleData articleData;


    public ArticleData getArticleData() {
        return articleData;
    }

    public void setArticleData(ArticleData articleData) {
        this.articleData = articleData;
    }

    @Override
    public String toString() {
        return "ArticleRequest{" +
                "articleData=" + articleData +
                '}';
    }
}
