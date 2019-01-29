package com.example.demo.repo

import com.example.demo.api.PostsAPI
import com.example.demo.models.Post
import com.example.demo.models.PostComment
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.awaitResult

class PostRepo(private val postsAPI: PostsAPI) {
    suspend fun getPosts(): Result<List<Post>> =
        postsAPI.getPosts().awaitResult()

    suspend fun getPostComments(id: String): Result<List<PostComment>> =
        postsAPI.getPostComments(id).awaitResult()
}