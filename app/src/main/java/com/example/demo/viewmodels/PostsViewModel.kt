package com.example.demo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.demo.api.PostsAPI
import com.example.demo.extensions.ifError
import com.example.demo.extensions.ifSucceeded
import com.example.demo.models.Post
import com.example.demo.models.PostComment
import com.example.demo.repo.PostRepo
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch


class PostsViewModel: ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default

    val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private var gson: Gson = GsonBuilder()
        .setLenient()
        .create()
    private var retrofit: Retrofit
    private var postsApi: PostsAPI

    val error = MutableLiveData<String>()
    val posts = MutableLiveData<List<Post>>()
    val postComments = MutableLiveData<List<PostComment>>()

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        postsApi = retrofit.create(PostsAPI::class.java)
    }

    fun getPosts() {
        launch {
            val result = PostRepo(postsApi).getPosts()
            result.ifSucceeded { data, response ->
                val listPosts = mutableListOf<Post>()
                val title = Post()
                title.isTitle = true
                title.title = "Posts"
                listPosts.add(title)
                data.forEach {
                    listPosts.add(it)
                }
                posts.postValue(listPosts)
            }
            result.ifError { code, response ->
                error.postValue(response.message())
            }
        }
    }

    fun getPostComments(id: String) {
        launch {
            val result = PostRepo(postsApi).getPostComments(id)
            result.ifSucceeded { data, response ->
                val listPosts = mutableListOf<PostComment>()
                val title = PostComment()
                title.isTitle = true
                title.email = "Comments"
                listPosts.add(title)
                data.forEach {
                    listPosts.add(it)
                }
                postComments.postValue(listPosts)
            }
            result.ifError { code, response ->
                error.postValue(response.message())
            }
        }
    }


    fun getTitle(postObject: Post): String {
        var name = ""
        when (postObject.userId) {
            1 -> name = "Brian (01)"
            2 -> name = "Joseph (02)"
            3 -> name = "Andrew (03)"
            4 -> name = "Johana (04)"
            5 -> name = "Jacob (05)"
            6 -> name = "Steve (06)"
            7 -> name = "Andrea (07)"
            8 -> name = "George (08)"
            9 -> name = "Steph (09)"
            10 -> name = "Rachel (10)"
        }
        return "\""+postObject.title+"\"" + " by " + "<b>$name</b>"
    }


}