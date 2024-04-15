package com.example.khabar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ArticleSelect{
RecyclerView rv;
TextView error;
SearchView sv ;
Button reload;
ProgressDialog pd;
Context context;
Button buttons[] = new Button[7];
Recycle r;
TextView tv;
Parcelable state;
GridLayoutManager gm = new GridLayoutManager(this, 1);
    private final Fetchdata<APIResponse> fd = new Fetchdata<APIResponse>() {
        @Override
        public void onFetchData(List<HeadLine> l, String message,int totalResults) {
            showNews(l,totalResults);
        }

        @Override
        public void onError(String message) {
            pd.dismiss();
            setContentView(R.layout.error_page);

        }

        @Override
        public void noData(String message) {
            pd.dismiss();
            setContentView(R.layout.error_page);
            error = findViewById(R.id.error);
            error.setText(message);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_list);
        sv = findViewById(R.id.searchView);
        rv = findViewById(R.id.RV);
        reload = findViewById(R.id.reload);
        context = this;
        pd = new ProgressDialog(context);
        pd.setTitle("Adding News");
        pd.show();
        tv = findViewById(R.id.totalResults);
        buttons[0] = findViewById(R.id.General);
        buttons[1] = findViewById(R.id.Bussiness);
        buttons[2] =findViewById(R.id.Sports);
        buttons[3] = findViewById(R.id.Entertainment);
        buttons[4] = findViewById(R.id.Science);
        buttons[5] = findViewById(R.id.Technology);
        buttons[6] = findViewById(R.id.Health);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                pd.setTitle("Adding News");
                pd.show();
                Retro retro = new Retro(context);
                retro.getHeadLine(fd,"general",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(state==null) {
            Retro ret = new Retro(this);
            ret.getHeadLine(fd, "general", "null");
        }

    }

    public void showNews(List<HeadLine>l,int totalResults) {
        tv.setText(totalResults+" articles found");
        rv.setHasFixedSize(true);
        rv.setLayoutManager(gm);
        r = new Recycle(l, this,this);
        rv.setAdapter(r);
        if(state!=null)
            gm.onRestoreInstanceState(state);
        pd.dismiss();
    }

    @Override
    public void onItemClick(List<HeadLine> l,int position) {
        Intent in = new Intent(context, Article_View.class);
        in.putExtra("content",l.get(position).content);
        in.putExtra("urlToImage",l.get(position).urlToImage);
        in.putExtra("HeadLine",l.get(position).title);
        in.putExtra("Source",l.get(position).author);
        in.putExtra("published",l.get(position).publishedAt);
        in.putExtra("URL",l.get(position).url);
        startActivity(in);
    }

    @Override
    protected void onStop() {
        super.onStop();
        state = gm.onSaveInstanceState();
    }

    public void onClick(View v)
    {
        int key = v.getId();
        for(int i=0;i<7;i++)
        {
            if(key==buttons[i].getId())
            {
                pd.setTitle("Adding News");
                pd.show();
                Retro retro = new Retro(context);
                retro.getHeadLine(fd,buttons[i].getText().toString().toLowerCase(),"null");
            }
        }

    }
    public void onC(View v)
    {
            pd.setTitle("Adding News");
            pd.show();
            setContentView(R.layout.activity_main);
            Retro retro = new Retro(context);
            retro.getHeadLine(fd,"general","null");


    }
}
