package com.example.demo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.adapters.PostCommentsAdapter
import com.example.demo.models.Post
import com.example.demo.viewmodels.PostsViewModel
import kotlinx.android.synthetic.main.activity_information_display.*
import kotlinx.android.synthetic.main.post_element.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostComments : AppCompatActivity() {

    private val adapter: PostCommentsAdapter by inject()
    private val postsViewModel: PostsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_display)
        val extras = intent.extras
        val postObject = extras?.getParcelable("postItem") ?: Post()
        setupUI(postObject)
        postsViewModel.getPostComments(postObject.id.toString())
    }


    private fun setupUI(postObject: Post) {
        titleContainer.visibility = View.GONE
        postContainer.visibility = View.VISIBLE
        titleValue.text = Html.fromHtml(postsViewModel.getTitle(postObject))
        contentValue.text = postObject.body
        postId.text = "0000"+postObject.id
        postsViewModel.observePostComments(this, this@PostComments, adapter)
    }


    fun setupRecyclerView() {
        val llm = androidx.recyclerview.widget.LinearLayoutManager(this)
        llm.orientation = RecyclerView.VERTICAL
        postCommentsList.layoutManager = llm
        postCommentsList.adapter = adapter
    }


}
