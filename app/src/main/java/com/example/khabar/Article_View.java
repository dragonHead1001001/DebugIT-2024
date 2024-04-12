package com.example.khabar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
        setContentView(R.layout.activity_articles);
        in = getIntent();
        head = findViewById(R.id.headlines);
        content = findViewById(R.id.article);
        img = findViewById(R.id.articleImage);
        author =findViewById(R.id.articleAuthor);
        time= findViewById(R.id.publishedTime);
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