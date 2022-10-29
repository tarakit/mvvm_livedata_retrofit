package com.istad.weekday_mvvm.data.models;

import com.google.gson.annotations.SerializedName;
import com.istad.weekday_mvvm.data.models.api.response.ThumbnailData;

import java.io.Serializable;

public class Article implements Serializable {

    @SerializedName("title")
    private String title;
    @SerializedName("slug")
    private String slug;
    @SerializedName("status")
    private boolean status;
    @SerializedName("content")
    private String content;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("publishedAt")
    private String publishedAt;
    @SerializedName("thumbnail")
    private ThumbnailData thumbnailData;

    public Article(String title, String slug, boolean status, String content, String createdAt, String updatedAt, String publishedAt, ThumbnailData thumbnailData) {
        this.title = title;
        this.slug = slug;
        this.status = status;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.publishedAt = publishedAt;
        this.thumbnailData = thumbnailData;
    }

    public ThumbnailData getThumbnailData() {
        return thumbnailData;
    }

    public void setThumbnailData(ThumbnailData thumbnailData) {
        this.thumbnailData = thumbnailData;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                ", status=" + status +
                ", content='" + content + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", thumbnailData=" + thumbnailData +
                '}';
    }
}
