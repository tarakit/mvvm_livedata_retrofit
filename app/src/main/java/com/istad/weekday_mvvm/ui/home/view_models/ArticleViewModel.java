package com.istad.weekday_mvvm.ui.home.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.istad.weekday_mvvm.data.models.Thumbnail;
import com.istad.weekday_mvvm.data.models.api.request.ArticleData;
import com.istad.weekday_mvvm.data.models.api.request.ArticleRequest;
import com.istad.weekday_mvvm.data.models.api.response.ArticlePostResponse;
import com.istad.weekday_mvvm.data.models.api.response.ArticleResponse;
import com.istad.weekday_mvvm.data.models.api.response.Data;
import com.istad.weekday_mvvm.data.remote.repository.ArticleRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ArticleViewModel extends ViewModel {

    ArticleRepository articleRepository;
    LiveData<ArticleResponse> articleResponseLiveData;

    public void init(){
        articleRepository = new ArticleRepository();
        articleResponseLiveData = articleRepository.getArticleResponseMutableLiveData();
    }

    public void initRepo(){
        articleRepository = new ArticleRepository("only init service");
    }

    public MutableLiveData<ArticlePostResponse> postArticle(String imageId, String title, String content){
        ArticleRequest articleRequest = new ArticleRequest();
        ArticleData data = new ArticleData();
        data.setTitle(title);
        data.setSlug(title+ "123");
        data.setContent(content);
        data.setStatus(true);
        data.setThumbnail(imageId);
        data.setCategory("2");
        List<String> tags = new ArrayList<>();
        tags.add("1");
        data.setTags(tags);
        data.setUsersPermissionUser("1");
        articleRequest.setArticleData(data);

        return articleRepository.postArticle(articleRequest);
    }

    public MutableLiveData<List<Thumbnail>> uploadImage(File file){
        return articleRepository.uploadImage(file);
    }

    public void getArticleByTitle(String title){
        articleRepository.getArticlesByTitle(title);
    }

    public void getAllArticles(){
        articleRepository.getAllArticles();
    }

    public LiveData<ArticleResponse> getArticleResponseLiveData() {
        return articleResponseLiveData;
    }
}
