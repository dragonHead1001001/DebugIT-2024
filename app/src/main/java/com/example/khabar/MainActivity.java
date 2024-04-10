package com.example.khabar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {
RecyclerView rv;
SearchView sv ;
Context context;
Recycle r;
    private final Fetchdata<APIResponse> fd = new Fetchdata<APIResponse>() {
        @Override
        public void onFetchData(List<HeadLine> l, String message) {
            showNews(l);
        }

        @Override
        public void onError(String message) {
            Toast.makeText(new MainActivity(),message,Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sv = findViewById(R.id.searchView);
        Retro ret = new Retro(this);
        ret.getHeadLine(fd,"business","null");
        context = this;
    }



    public void showNews(List<HeadLine>l)
    {
        rv = (RecyclerView) findViewById(R.id.RV);
        rv.setHasFixedSize(true);
        GridLayoutManager gm= new GridLayoutManager(this,1);
        rv.setLayoutManager(gm);
        r= new Recycle(l,this);
        rv.setAdapter(new Recycle(l,this));
        rv.addOnItemTouchListener(new ArticleSelect(this, rv, new ArticleSelect.OnItemListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent in = new Intent(context, Article_View.class);
                in.putExtra("content",l.get(position).content);
                in.putExtra("urlToImage",l.get(position).urlToImage);
                in.putExtra("HeadLine",l.get(position).title);
                startActivity(in);
            }

            @Override
            public void onLongItemClick(View v, int position) {

            }
        }));
    }
}
