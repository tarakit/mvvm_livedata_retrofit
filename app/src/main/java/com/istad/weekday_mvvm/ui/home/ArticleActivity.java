package com.istad.weekday_mvvm.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.istad.weekday_mvvm.R;
import com.istad.weekday_mvvm.data.models.Article;
import com.istad.weekday_mvvm.data.models.api.response.ArticleResponse;
import com.istad.weekday_mvvm.ui.detail.ArticleDetailActivity;
import com.istad.weekday_mvvm.ui.home.adapters.ArticleAdapter;
import com.istad.weekday_mvvm.ui.home.adapters.ArticleAdapterClickListener;
import com.istad.weekday_mvvm.ui.home.view_models.ArticleViewModel;
import com.istad.weekday_mvvm.ui.mutable.MutableActivity;

import java.util.ArrayList;


public class ArticleActivity extends AppCompatActivity implements ArticleAdapterClickListener {

    ArticleViewModel articleViewModel;
    RecyclerView recyclerView;
    ArticleAdapter adapter;
    ProgressBar progressBar;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        initViews();

        ViewModelProvider.Factory factory =
                (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory();
        articleViewModel = new ViewModelProvider(this, factory).get(ArticleViewModel.class);
        articleViewModel.init();

        articleViewModel.getArticleResponseLiveData().observe(this, new Observer<ArticleResponse>() {
            @Override
            public void onChanged(ArticleResponse articleResponse) {
                Log.d("TAG", "onChanged DATA: "+ articleResponse.getDataList().toString());
                adapter.setDataList(articleResponse.getDataList());
                setProgressBarInvisible();
            }
        });
    }

    void setProgressBarVisible(){
        progressBar.setVisibility(View.VISIBLE);
    }

    void setProgressBarInvisible(){
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void initViews() {
        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ArticleAdapter(new ArrayList<>(), this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("TAG", "onQueryTextSubmit: "+ s);
                articleViewModel.getArticleByTitle(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d("TAG", "onQueryTextChange: "+ s);
                if(s.isEmpty()){
                    articleViewModel.getAllArticles();
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add_article){
            startActivity(new Intent(this, MutableActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemArticleClick(Article article) {
        Intent intent = new Intent(this, ArticleDetailActivity.class);
        intent.putExtra("article", article);
        startActivity(intent);
    }
}