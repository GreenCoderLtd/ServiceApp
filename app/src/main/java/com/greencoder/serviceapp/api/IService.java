package com.greencoder.serviceapp.api;

import com.greencoder.serviceapp.model.Comment;
import com.greencoder.serviceapp.model.Post;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by newcomputer on 10/18/15.
 */
public interface IService {

    @GET("/posts")
    Call<List<Post>> getAllPost();

    @GET("/posts/{postid}")
    Call<Post> gePost(@Path("postid") int postId);

    @GET("/comments")
    Call<List<Comment>> getCommentsOf(@Query("postId") int postId);

    @POST("/posts")
    Call<Post> createPost(@Body Post post);

}
