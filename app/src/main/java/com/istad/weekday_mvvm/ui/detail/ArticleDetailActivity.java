package com.istad.weekday_mvvm.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.istad.weekday_mvvm.R;
import com.istad.weekday_mvvm.data.models.Article;

public class ArticleDetailActivity extends AppCompatActivity {

    ImageView thumbnail;
    TextView title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        Article article = (Article) getIntent().getSerializableExtra("article");

        thumbnail = findViewById(R.id.thumbnail);
        title = findViewById(R.id.title_detail);
        content = findViewById(R.id.content_detail);

        title.setText(article.getTitle());
        content.setText(article.getContent());
    }
}