package com.greencoder.serviceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greencoder.serviceapp.api.IService;
import com.greencoder.serviceapp.model.Comment;
import com.greencoder.serviceapp.model.Post;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.POST;

public class MainActivity extends AppCompatActivity {

    public static final String END_POINT="http://jsonplaceholder.typicode.com";
    public static final String LOG_TAG=MainActivity.class.getSimpleName();

    IService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        consumeApi();
    }

    public void consumeApi()
    {
        Gson gson=new GsonBuilder()
                .create();

        Retrofit ret=new Retrofit.Builder()
                .baseUrl(END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        service=ret.create(IService.class);

        Call<Post> c1=service.gePost(1);

        c1.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Response<Post> response, Retrofit retrofit) {

                Log.e(LOG_TAG,"Single post"+response.body().toString());
            }

            @Override
            public void onFailure(Throwable t) {

                Log.e(LOG_TAG,t.toString());
            }
        });

        Call<List<Comment>> c2=service.getCommentsOf(1);

        c2.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Response<List<Comment>> response, Retrofit retrofit) {

                List<Comment> allCm=response.body();

                Log.e(LOG_TAG,"Comments");
                for(Comment cm:allCm)
                {
                    Log.e(LOG_TAG,cm.toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {

                Log.e(LOG_TAG,t.toString());
            }
        });

        Post p=new Post(1l,101l,"Avishek","Rest Web service");

        Call<Post> c3=service.createPost(p);

        c3.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Response<Post> response, Retrofit retrofit) {

                Log.e(LOG_TAG,"Created post "+response.body().toString());
            }

            @Override
            public void onFailure(Throwable t) {

                Log.e(LOG_TAG,t.toString());
            }
        });
    }
}
