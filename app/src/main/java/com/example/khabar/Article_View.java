package com.example.khabar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class Article_View extends AppCompatActivity {
TextView head;
Intent in;
TextView content;
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_article_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        in = getIntent();
        head = findViewById(R.id.headline);
        content = findViewById(R.id.content);
        img = findViewById(R.id.articleImg);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle _EXTRAS_ = in.getExtras();
        if(_EXTRAS_!=null) {
            content.setText(_EXTRAS_.get("content").toString());
            head.setText(_EXTRAS_.get("HeadLine").toString());
            Picasso.get().load(_EXTRAS_.get("urlToImage").toString()).fit().into(img);
        }
    }
}