package com.istad.weekday_mvvm.data.remote.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.istad.weekday_mvvm.data.ServiceGenerator;
import com.istad.weekday_mvvm.data.models.Thumbnail;
import com.istad.weekday_mvvm.data.models.api.request.ArticleRequest;
import com.istad.weekday_mvvm.data.models.api.response.ArticlePostResponse;
import com.istad.weekday_mvvm.data.models.api.response.ArticleResponse;
import com.istad.weekday_mvvm.data.remote.services.ArticleService;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

public class ArticleRepository {

    ArticleService articleService;
    MutableLiveData<ArticleResponse> articleResponseMutableLiveData;

    public ArticleRepository(){
        articleResponseMutableLiveData = new MutableLiveData<>();
        articleService = ServiceGenerator.createService(ArticleService.class);
        getAllArticles();
    }

    public ArticleRepository(String service){
        articleService = ServiceGenerator.createService(ArticleService.class);
    }

    public MutableLiveData<ArticlePostResponse> postArticle(ArticleRequest articleRequest){
        MutableLiveData<ArticlePostResponse> liveData = new MutableLiveData<>();

        articleService.postArticle(articleRequest).enqueue(new Callback<ArticlePostResponse>() {
            @Override
            public void onResponse(Call<ArticlePostResponse> call, Response<ArticlePostResponse> response) {
                Log.d("TAG", "onResponse: "+ response);
                liveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArticlePostResponse> call, Throwable t) {
                Log.d("TAG", "onFailure: "+ t.getMessage());
                liveData.postValue(null);
            }
        });

        return liveData;
    }

    public MutableLiveData<List<Thumbnail>> uploadImage(File file){
        MutableLiveData<List<Thumbnail>> thumbnailLiveData = new MutableLiveData<>();
        RequestBody request = RequestBody.create(MediaType.parse("image/*"), file);

        Log.d("TAG", "uploadImage: "+ file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("files", file.getAbsolutePath(), request);
        articleService.uploadImage(body).enqueue(new Callback<List<Thumbnail>>() {
            @Override
            public void onResponse(Call<List<Thumbnail>> call, Response<List<Thumbnail>> response) {
                Log.d("TAG", "onResponse: "+ response);
                thumbnailLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Thumbnail>> call, Throwable t) {
                Log.d("TAG", "onFailure: "+ t.getMessage());
                thumbnailLiveData.postValue(null);
            }
        });

        return thumbnailLiveData;
    }

    public void getArticlesByTitle(String title){
        articleService.getArticlesByTitle(title).enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                articleResponseMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                articleResponseMutableLiveData.postValue(null);
            }
        });
    }

    public void getAllArticles(){
        articleService.getAllArticles().enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                Log.d("TAG", "onResponse: "+ response.body().getDataList().toString());
                articleResponseMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                Log.d("TAG", "onFailure: "+ t.getMessage());
                articleResponseMutableLiveData.postValue(null);
            }
        });
    }

    public LiveData<ArticleResponse> getArticleResponseMutableLiveData() {
        return articleResponseMutableLiveData;
    }
}
