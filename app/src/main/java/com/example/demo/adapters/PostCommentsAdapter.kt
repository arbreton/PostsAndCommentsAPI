package com.example.demo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.demo.models.PostComment


class PostCommentsAdapter(var postCommentList: List<PostComment> = listOf()): RecyclerView.Adapter<PostCommentsAdapter.PostViewHolder>() {


    inner class PostViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView), PostCommentViewHolderInterface {
        val name = rootView.findViewById<TextView>(R.id.nameValue)
        val body = rootView.findViewById<TextView>(R.id.contentValue)
        val email = rootView.findViewById<TextView>(R.id.emailValue)
        val postContainer = rootView.findViewById<ConstraintLayout>(R.id.postContainer)
        val titleContainer = rootView.findViewById<ConstraintLayout>(R.id.titleContainer)
        val titleBold = rootView.findViewById<TextView>(R.id.titleValueBold)

        override fun bind(item: PostComment) {
            if (item.isTitle) {
                postContainer.visibility = View.GONE
                titleContainer.visibility = View.VISIBLE
                titleBold.text = item.email
            } else {
                titleContainer.visibility = View.GONE
                postContainer.visibility = View.VISIBLE
                name.text = item.name
                body.text = item.body
                email.text = item.email
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_comment_element, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postCommentList?.size ?: 0
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = postCommentList[position]
        holder.bind(item)
    }

    interface PostCommentViewHolderInterface {
        fun bind(item: PostComment)
    }

}