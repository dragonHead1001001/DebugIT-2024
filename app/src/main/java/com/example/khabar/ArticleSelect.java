package com.example.khabar;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleSelect implements RecyclerView.OnItemTouchListener {

     final OnItemListener mListener;
     Context context;
    GestureDetector GestureDetect;
    public interface OnItemListener
    {
        public void onItemClick(View v,int position);
        public void onLongItemClick(View v,int position);
    }
    public ArticleSelect(Context context, final RecyclerView recyclerView, OnItemListener listener)
    {
        this.context =context;
        mListener =listener;
        GestureDetect = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(@NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(@NonNull MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(@NonNull MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(@NonNull MotionEvent e) {
            View childView = recyclerView.findChildViewUnder(e.getX(),e.getY());
            if(childView!=null&& mListener!=null)
            {
                mListener.onItemClick(childView,recyclerView.getChildAdapterPosition(childView));
            }
            }

            @Override
            public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
    }
    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(),e.getY());
        if(mListener!=null&&GestureDetect!=null&&child!=null) {
            mListener.onItemClick(child, rv.getChildAdapterPosition(child));
            return true;
        }
        Toast.makeText(context,"Error displaying the article",Toast.LENGTH_SHORT ).show();
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
