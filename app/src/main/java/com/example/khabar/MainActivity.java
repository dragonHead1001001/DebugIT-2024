package com.example.khabar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity implements ArticleSelect{
RecyclerView rv;
SearchView sv ;
Context context;
Button button;
Recycle r;
TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sv = findViewById(R.id.searchView);
        rv = findViewById(R.id.RV);
        context = this;
        tv = findViewById(R.id.totalResults);
    }

    @Override
    protected void onStart() {
        super.onStart();
         final Fetchdata<APIResponse> fd = new Fetchdata<APIResponse>() {
            @Override
            public void onFetchData(List<HeadLine> l, String message,int totalResults) {
                showNews(l,totalResults);
            }

            @Override
            public void onError(String message) {
                Toast.makeText(new MainActivity(),message,Toast.LENGTH_SHORT).show();
            }
        };
        Retro ret = new Retro(this);
        ret.getHeadLine(fd,"general","null");

    }

    public void showNews(List<HeadLine>l,int totalResults) {
        tv.setText(totalResults+" articles found");
        rv.setHasFixedSize(true);
        GridLayoutManager gm = new GridLayoutManager(this, 1);
        rv.setLayoutManager(gm);
        r = new Recycle(l, this,this);
        rv.setAdapter(r);
    }

    @Override
    public void onItemClick(List<HeadLine> l,int position) {
        Intent in = new Intent(context, Article_View.class);
        in.putExtra("content",l.get(position).content);
        in.putExtra("urlToImage",l.get(position).urlToImage);
        in.putExtra("HeadLine",l.get(position).title);
        in.putExtra("Source",l.get(position).author);
        in.putExtra("published",l.get(position).publishedAt);
        startActivity(in);
    }
}
