package com.istad.weekday_mvvm.data.models.api.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleData {

    @SerializedName("title")
    private String title;
    @SerializedName("slug")
    private String slug;
    @SerializedName("status")
    private boolean status;
    @SerializedName("content")
    private String content;
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("category")
    private String category;
    @SerializedName("tags")
    private List<String> tags;
    @SerializedName("users_permissions_user")
    private String usersPermissionUser;

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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getUsersPermissionUser() {
        return usersPermissionUser;
    }

    public void setUsersPermissionUser(String usersPermissionUser) {
        this.usersPermissionUser = usersPermissionUser;
    }

    @Override
    public String toString() {
        return "ArticleData{" +
                "title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                ", status=" + status +
                ", content='" + content + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", category='" + category + '\'' +
                ", tags=" + tags +
                ", usersPermissionUser='" + usersPermissionUser + '\'' +
                '}';
    }
}