package com.example.khabar;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Retro {
    Context context;
    String APIkey = "27dd4f46d8cd4ba881fb80e711595475";
            String Base_URL = "https://newsapi.org/v2/";
            Retrofit rf = new Retrofit.Builder().baseUrl(Base_URL).addConverterFactory(GsonConverterFactory.create()).build();

    public void getHeadLine(Fetchdata<APIResponse> l,String category, String query)
    {
        NewsAPI newsAPI = rf.create(NewsAPI.class);
        Call<APIResponse> headlines= newsAPI.a("us",APIkey,category,query);
        try
        {
            headlines.enqueue(new Callback<APIResponse>() {
                @Override
                public void onResponse(@NonNull Call<APIResponse> call, @NonNull Response<APIResponse> response) {
                    if(!response.isSuccessful())
                    {
                        Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();

                    }
                    if(response.body()==null) {
                        Toast.makeText(context, "NULL", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    l.onFetchData(response.body().getArticle(), response.message() );
                }

                @Override
                public void onFailure(@NonNull Call<APIResponse> call, @NonNull Throwable throwable) {
                    l.onError("Request Failed");
                }
            });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public Retro(Context c) {
        context = c;
    }
    interface NewsAPI
    {
        @GET("top-headlines")
        Call<APIResponse> a(
                @Query("country")String country,
                @Query("apikey")String APIkey,
                @Query("category")String category,
                @Query("q")String q

        );

    }


}