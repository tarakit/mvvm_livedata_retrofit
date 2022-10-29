package com.istad.weekday_mvvm.data.models.api.response;

import com.google.gson.annotations.SerializedName;
import com.istad.weekday_mvvm.data.models.Thumbnail;

public class DataAttribute {

    @SerializedName("id")
    private int id;
    @SerializedName("attributes")
    private Thumbnail thumbnail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "DataAttribute{" +
                "id=" + id +
                ", thumbnail=" + thumbnail +
                '}';
    }
}
