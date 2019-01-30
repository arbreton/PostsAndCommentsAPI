package com.example.demo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.adapters.PostsAdapter
import com.example.demo.models.Post
import kotlinx.android.synthetic.main.activity_main.*
import com.example.demo.viewmodels.PostsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class MainActivity : AppCompatActivity(), PostsAdapter.PostActionInterface {

    private val adapter: PostsAdapter by inject { parametersOf(this@MainActivity) }
    private val postViewModel: PostsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postViewModel.observePosts(this, this@MainActivity, adapter)
        postViewModel.getPosts()
    }


    override fun onItemClick(item: Post) {
        val intent = Intent(this@MainActivity, PostComments::class.java)
        intent.putExtra("postItem", item)
        startActivity(intent)
    }


    fun setupRecyclerView() {
        val llm = androidx.recyclerview.widget.LinearLayoutManager(this)
        llm.orientation = RecyclerView.VERTICAL
        postsList.layoutManager = llm
        postsList.adapter = adapter
    }

}
