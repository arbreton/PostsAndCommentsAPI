package com.example.demo.viewmodels.interfaces

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import com.example.demo.adapters.PostCommentsAdapter
import com.example.demo.adapters.PostsAdapter
import com.example.demo.models.Post

interface PostsVmInterface {
    fun observePosts(lifecycleOwner: LifecycleOwner, activity: Activity, adapter: PostsAdapter)
    fun observePostComments(lifecycleOwner: LifecycleOwner, activity: Activity, adapter: PostCommentsAdapter)
    fun getPosts()
    fun getPostComments(id: String)
    fun getTitle(postObject: Post): String
}