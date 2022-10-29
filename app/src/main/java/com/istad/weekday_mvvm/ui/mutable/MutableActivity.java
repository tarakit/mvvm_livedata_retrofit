package com.istad.weekday_mvvm.ui.mutable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.istad.weekday_mvvm.R;
import com.istad.weekday_mvvm.data.models.Thumbnail;
import com.istad.weekday_mvvm.data.models.api.response.ArticlePostResponse;
import com.istad.weekday_mvvm.ui.home.view_models.ArticleViewModel;
import com.istad.weekday_mvvm.utils.FilePath;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MutableActivity extends AppCompatActivity {

    ImageView imageThumbnail;
    EditText title, content;
    ProgressBar progressBar;
    Button btnSave;
    ArticleViewModel articleViewModel;

    int thumbnailID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutable);

        initViews();
        initEvents();

        ViewModelProvider.Factory factory =
                (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory();
        articleViewModel = new ViewModelProvider(this, factory).get(ArticleViewModel.class);
        articleViewModel.initRepo();

        requestPermission();
    }

    private void initEvents() {
        imageThumbnail.setOnClickListener(view -> {
            showFileChooser();
        });

        btnSave.setOnClickListener(view -> {
            articleViewModel.postArticle(String.valueOf(thumbnailID), title.getText().toString(), content.getText().toString()).observe(this, new Observer<ArticlePostResponse>() {
                @Override
                public void onChanged(ArticlePostResponse articlePostResponse) {
                    Toast.makeText(MutableActivity.this, "Success POsted", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onChanged: "+ articlePostResponse.toString());
                }
            });
        });
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Choose Image"),1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1000 && resultCode == RESULT_OK && data != null){
            progressBarVisible();
            Uri imageUri = data.getData();
            Log.d("TAG", "onActivityResult: "+ imageUri);
            try {
                String selectedPath = FilePath.getPath(this, imageUri);
                File file = new File(selectedPath);
                uploadFileDataToAPI(file);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void uploadFileDataToAPI(File file) {
        articleViewModel.uploadImage(file).observe(this, new Observer<List<Thumbnail>>() {
            @Override
            public void onChanged(List<Thumbnail> thumbnails) {
                String imageUrl = "https://cms.istad.co"+ thumbnails.get(0).getUrl();
                thumbnailID = thumbnails.get(0).getId();
                Glide.with(MutableActivity.this).load(imageUrl).into(imageThumbnail);
                progressBarInvisible();
            }
        });
    }

    private void progressBarVisible(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void progressBarInvisible(){
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void initViews() {
        imageThumbnail = findViewById(R.id.image_thumbnail);
        title = findViewById(R.id.edit_title);
        content = findViewById(R.id.edt_content);
        progressBar = findViewById(R.id.progress_bar);
        btnSave = findViewById(R.id.btn_save);
    }

    private void requestPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){}
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE
        }, 100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "You just denied Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }
}