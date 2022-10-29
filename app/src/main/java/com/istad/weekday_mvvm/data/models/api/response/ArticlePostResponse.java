package com.istad.weekday_mvvm.data.models.api.response;

import com.google.gson.annotations.SerializedName;

public class ArticlePostResponse {

    @SerializedName("data")
    private Data dataAttribute;

    public Data getDataAttribute() {
        return dataAttribute;
    }

    public void setDataAttribute(Data dataAttribute) {
        this.dataAttribute = dataAttribute;
    }

    @Override
    public String toString() {
        return "ArticlePostResponse{" +
                "dataAttribute=" + dataAttribute +
                '}';
    }
}

