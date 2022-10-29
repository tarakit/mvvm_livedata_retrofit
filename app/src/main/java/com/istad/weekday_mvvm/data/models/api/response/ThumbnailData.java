package com.istad.weekday_mvvm.data.models.api.response;

import com.google.gson.annotations.SerializedName;

public class ThumbnailData {

    @SerializedName("data")
    private DataAttribute dataAttribute;

    public DataAttribute getDataAttribute() {
        return dataAttribute;
    }

    public void setDataAttribute(DataAttribute dataAttribute) {
        this.dataAttribute = dataAttribute;
    }

    @Override
    public String toString() {
        return "ThumbnailData{" +"dataAttribute=" + dataAttribute +'}';
    }
}