package com.example.demo.viewmodels

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.demo.activities.MainActivity
import com.example.demo.activities.PostComments
import com.example.demo.adapters.PostCommentsAdapter
import com.example.demo.adapters.PostsAdapter
import com.example.demo.network.api.PostsAPI
import com.example.demo.extensions.ifError
import com.example.demo.extensions.ifSucceeded
import com.example.demo.models.Post
import com.example.demo.models.PostComment
import com.example.demo.repo.PostRepo
import com.example.demo.viewmodels.interfaces.PostsVmInterface
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import retrofit2.Retrofit
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch


class PostsViewModel(val postsApi: PostsAPI): ViewModel(), CoroutineScope, PostsVmInterface {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    val error = MutableLiveData<String>()
    val posts = MutableLiveData<List<Post>>()
    val postComments = MutableLiveData<List<PostComment>>()


    override fun observePosts(lifecycleOwner: LifecycleOwner, activity: Activity, adapter: PostsAdapter) {
        error.observe(lifecycleOwner, Observer {
            Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
        })
        posts.observe(lifecycleOwner, Observer {
            adapter.setPostList(it)
            (activity as MainActivity).setupRecyclerView()
        })
    }

    override fun observePostComments(lifecycleOwner: LifecycleOwner, activity: Activity, adapter: PostCommentsAdapter) {
        error.observe(lifecycleOwner, Observer {
            Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
        })
        postComments.observe(lifecycleOwner, Observer {
            adapter.setPostComments(it)
            (activity as PostComments).setupRecyclerView()
        })
    }

    override fun getPosts() {
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

    override fun getPostComments(id: String) {
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


    override fun getTitle(postObject: Post): String {
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