package com.example.khabar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Recycle extends RecyclerView.Adapter<ViewHold> {
List<HeadLine> hl;
Context context;
ArticleSelect listener;
public Recycle(List<HeadLine> l,Context c,ArticleSelect listener)
{
    hl= l;
    context = c;
    this.listener = listener;
}

    @NonNull
    @Override
    public ViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHold(LayoutInflater.from(context).inflate(R.layout.headlines,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHold holder, int position) {
        holder.head.setText(hl.get(position).title);
        holder.art.setText(hl.get(position).content);

        if(hl.get(position).getUrlToImage()!=null)
        {
            Picasso.get().load(hl.get(position).urlToImage).fit().into(holder.img);
        }
        else
        {
            holder.img.setImageResource(R.drawable.dumb);
        }
        holder.cv.setOnClickListener(v -> listener.onItemClick(hl,position));
    }
    @Override
    public int getItemCount() {
    if(hl!=null)
        return hl.size();
    else
        return 0;
    }
}
