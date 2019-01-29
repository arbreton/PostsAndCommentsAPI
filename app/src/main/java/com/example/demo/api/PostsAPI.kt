package com.example.demo.api

import com.example.demo.models.Post
import com.example.demo.models.PostComment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsAPI {
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("posts/{id}/comments")
    fun getPostComments(@Path("id") id: String): Call<List<PostComment>>
}