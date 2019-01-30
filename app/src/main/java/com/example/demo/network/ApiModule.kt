package com.example.demo.network

import com.example.demo.network.api.PostsAPI
import retrofit2.Retrofit

class ApiModule(private val retrofit: Retrofit) {
    fun postsApi(): PostsAPI {
        return retrofit.create(PostsAPI::class.java)
    }
}