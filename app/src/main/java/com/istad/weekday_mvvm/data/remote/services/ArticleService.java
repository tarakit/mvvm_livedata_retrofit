package com.istad.weekday_mvvm.data.remote.services;

import com.istad.weekday_mvvm.data.models.Thumbnail;
import com.istad.weekday_mvvm.data.models.api.request.ArticleRequest;
import com.istad.weekday_mvvm.data.models.api.response.ArticlePostResponse;
import com.istad.weekday_mvvm.data.models.api.response.ArticleResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ArticleService {

    @GET("articles?populate=*")
    Call<ArticleResponse> getAllArticles();

    @GET("articles")
    Call<ArticleResponse> getArticlesByTitle(@Query("filters[title][$containsi]") String title);

    @Multipart
    @POST("upload")
    Call<List<Thumbnail>> uploadImage(@Part MultipartBody.Part image);

    @POST("articles")
    Call<ArticlePostResponse> postArticle(@Body ArticleRequest articleRequest);
}
