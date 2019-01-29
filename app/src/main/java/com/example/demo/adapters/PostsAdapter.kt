package com.example.demo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.models.Post
import android.text.Html
import android.R.id
import androidx.constraintlayout.widget.ConstraintLayout


class PostsAdapter(var postList: List<Post> = listOf(), val listener: PostActionInterface): RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {


    inner class PostViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView), PostViewHolderInterface {
        val title = rootView.findViewById<TextView>(R.id.titleValue)
        val body = rootView.findViewById<TextView>(R.id.contentValue)
        val postId = rootView.findViewById<TextView>(R.id.postId)
        val postContainer = rootView.findViewById<ConstraintLayout>(R.id.postContainer)
        val titleContainer = rootView.findViewById<ConstraintLayout>(R.id.titleContainer)
        val titleBold = rootView.findViewById<TextView>(R.id.titleValueBold)

        override fun bind(item: Post) {
            if (item.isTitle) {
                postContainer.visibility = View.GONE
                titleContainer.visibility = View.VISIBLE
                titleBold.text = item.title
            } else {
                titleContainer.visibility = View.GONE
                postContainer.visibility = View.VISIBLE
                var name = ""
                when (item.userId) {
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
                val sourceString = "\""+item.title+"\"" + " by " + "<b>$name</b>"
                title.text = Html.fromHtml(sourceString)
                body.text = item.body
                postId.text = "0000"+item.id
                postContainer.setOnClickListener {
                    listener.onItemClick(item)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_element, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList?.size ?: 0
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = postList[position]
        holder.bind(item)
    }


    interface PostViewHolderInterface {
        fun bind(item: Post)
    }

    interface PostActionInterface {
        fun onItemClick(item: Post)
    }

}