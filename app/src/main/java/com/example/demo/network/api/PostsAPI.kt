package com.example.demo.network.api

import com.example.demo.models.Post
import com.example.demo.models.PostComment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsAPI {
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("comments")
    fun getPostComments(@Query("postId") id: String): Call<List<PostComment>>
}