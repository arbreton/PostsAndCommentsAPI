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


class MainActivity : AppCompatActivity(), PostsAdapter.PostActionInterface{

    lateinit var adapter: PostsAdapter
    private val viewModel by lazy { ViewModelProviders.of(this).get(PostsViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeViewModel()
        viewModel.getPosts()
    }


    private fun observeViewModel() {
        viewModel.error.observe(this, Observer {
            Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
        })
        viewModel.posts.observe(this, Observer {
            adapter = PostsAdapter(it, this)
            setupRecyclerView()
        })
    }

    override fun onItemClick(item: Post) {
        val intent = Intent(this@MainActivity, PostComments::class.java)
        intent.putExtra("postItem", item)
        startActivity(intent)
    }


    private fun setupRecyclerView() {
        val llm = androidx.recyclerview.widget.LinearLayoutManager(this)
        llm.orientation = RecyclerView.VERTICAL
        postsList.layoutManager = llm
        postsList.adapter = adapter
    }

}
