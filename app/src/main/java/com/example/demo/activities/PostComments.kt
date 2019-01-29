package com.example.demo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.adapters.PostCommentsAdapter
import com.example.demo.models.Post
import com.example.demo.viewmodels.PostsViewModel
import kotlinx.android.synthetic.main.activity_information_display.*
import kotlinx.android.synthetic.main.post_element.*

class PostComments : AppCompatActivity() {

    lateinit var adapter: PostCommentsAdapter
    private val viewModel by lazy { ViewModelProviders.of(this).get(PostsViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_display)
        val extras = intent.extras
        val postObject = extras?.getParcelable<Post>("postItem") ?: Post()
        setupUI(postObject)
        viewModel.getPostComments(postObject.id.toString())
    }

    private fun setupUI(postObject: Post) {
        titleContainer.visibility = View.GONE
        postContainer.visibility = View.VISIBLE
        titleValue.text = Html.fromHtml(viewModel.getTitle(postObject))
        contentValue.text = postObject.body
        postId.text = "0000"+postObject.id
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.error.observe(this, Observer {
            Toast.makeText(this@PostComments, "Error", Toast.LENGTH_SHORT).show()
        })
        viewModel.postComments.observe(this, Observer {
            adapter = PostCommentsAdapter(it)
            setupRecyclerView()
        })
    }


    private fun setupRecyclerView() {
        val llm = androidx.recyclerview.widget.LinearLayoutManager(this)
        llm.orientation = RecyclerView.VERTICAL
        postCommentsList.layoutManager = llm
        postCommentsList.adapter = adapter
    }


}
