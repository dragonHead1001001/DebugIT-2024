package com.example.khabar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
TextView author,time;
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
        author =findViewById(R.id.Source);
        time= findViewById(R.id.published);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle _EXTRAS_ = in.getExtras();
        if(_EXTRAS_!=null) {
            content.setText(""+_EXTRAS_.get("content"));
            head.setText(""+_EXTRAS_.get("HeadLine"));
            if(_EXTRAS_.get("urlToImage")!=null)
            {
                Log.d("Photo",""+_EXTRAS_.get("urlToImage"));
                Picasso.get().load(_EXTRAS_.get("urlToImage").toString()).into(img);
            }
            else
                img.setImageResource(R.drawable.dumb);
            author.setText(""+_EXTRAS_.get("Source"));
            time.setText(""+_EXTRAS_.get("published"));
        }
    }
}