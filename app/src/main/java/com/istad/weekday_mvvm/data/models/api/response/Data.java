package com.istad.weekday_mvvm.data.models.api.response;

import com.google.gson.annotations.SerializedName;
import com.istad.weekday_mvvm.data.models.Article;

public class Data {

    @SerializedName("id")
    private int id;
    @SerializedName("attributes")
    private Article article;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", article=" + article +
                '}';
    }
}
