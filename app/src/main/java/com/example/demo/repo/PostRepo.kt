package com.example.demo.repo

import com.example.demo.network.api.PostsAPI
import com.example.demo.models.Post
import com.example.demo.models.PostComment
import com.example.demo.repo.interfaces.PostRepoInterface
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.awaitResult

class PostRepo(private val postsAPI: PostsAPI): PostRepoInterface {
    override suspend fun getPosts(): Result<List<Post>> =
        postsAPI.getPosts().awaitResult()

    override suspend fun getPostComments(id: String): Result<List<PostComment>> =
        postsAPI.getPostComments(id).awaitResult()
}