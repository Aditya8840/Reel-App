package com.gfive.reelapp.adapter

import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.gfive.reelapp.MainActivity
import com.gfive.reelapp.R
import com.gfive.reelapp.model.videoItem
import kotlinx.android.synthetic.main.reel_layout.view.*
import java.util.concurrent.Flow


class ReelAdapter(private val reelItems: ArrayList<videoItem>,val listener: MainActivity) : RecyclerView.Adapter<ReelViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReelViewHolder {
        return ReelViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.reel_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ReelViewHolder, position: Int) {
        holder.videoView.setVideoPath(reelItems[position].videoUrl)
        holder.videoView.setOnPreparedListener {
            it.start()
            it.isLooping = true
            holder.videoView.layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT) // credit: ChatGPT
        }

        //like button
        holder.like.setOnClickListener{
            if(reelItems[position].videoLikes == 0){
                holder.like.setBackgroundResource(R.drawable.filled_heart)
                reelItems[position].videoLikes++
            }else{
                holder.like.setBackgroundResource(R.drawable.like)
                reelItems[position].videoLikes--
            }
        }
        if (reelItems[position].videoSaved){
            holder.saved.setBackgroundResource(R.drawable.bookmark_icon)
        }else{
            holder.saved.setBackgroundResource(R.drawable.bookmark)
        }
        holder.saved.setOnClickListener {
            if (reelItems[position].videoSaved){
                holder.saved.setBackgroundResource(R.drawable.bookmark)
                reelItems[position].videoSaved = false
                listener.deleteFlow(position)
            }else{
                holder.saved.setBackgroundResource(R.drawable.bookmark_icon)
                reelItems[position].videoSaved = true
                listener.saveFlow(position)
            }
        }
        holder.more.setOnClickListener {
            listener.bottomDialogMore(position)
        }
    }

    override fun getItemCount(): Int {
        return reelItems.size;
    }
}

class ReelViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val videoView : VideoView = view.videoView
    val more = view.more
    val like = view.like
    val saved = view.save_flow
}