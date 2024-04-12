package com.example.khabar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class ViewHold extends RecyclerView.ViewHolder
{
    TextView head;
    TextView art;
    ImageView img;
    CardView cv;
    TextView author;
    public ViewHold(View v)
    {
        super(v);
        head = v.findViewById(R.id.head);
        art = v.findViewById(R.id.art);
        cv = v.findViewById(R.id.cv);
        img = v.findViewById(R.id.img);
        author= v.findViewById(R.id.author);
    }

}
