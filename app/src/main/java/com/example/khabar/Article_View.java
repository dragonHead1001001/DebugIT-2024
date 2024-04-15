package com.example.khabar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Locale;

public class Article_View extends AppCompatActivity {
TextView head;
Intent in;
TextView content;
ImageView img;
TextView author,time;
ImageButton speaker,share;
TextToSpeech tts;
String url;
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
        speaker = findViewById(R.id.speaker);
        share = findViewById(R.id.Share);
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR)
                {
                    tts.setLanguage(Locale.US);
                }
            }
        });
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
            url = ""+_EXTRAS_.get("URL");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        tts.stop();
    }

    public void Speak(View v)
    {
        tts.speak(""+content.getText(),TextToSpeech.QUEUE_FLUSH,null);
    }
    public void Share(View v)
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,url+"\nis a news article I read on Khabar app.\nThis is news fetcher app.");
        intent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(intent, null);
        startActivity(shareIntent);
    }
}