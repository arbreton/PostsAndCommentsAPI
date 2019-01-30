package com.example.demo.repo.interfaces

import com.example.demo.models.Post
import com.example.demo.models.PostComment
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.awaitResult

interface PostRepoInterface {
    suspend fun getPosts(): Result<List<Post>>
    suspend fun getPostComments(id: String): Result<List<PostComment>>
}