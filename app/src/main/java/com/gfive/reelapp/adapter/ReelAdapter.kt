package com.gfive.reelapp.adapter

import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.gfive.reelapp.R
import com.gfive.reelapp.model.videoItem
import kotlinx.android.synthetic.main.reel_layout.view.*


class ReelAdapter(private val reelItems: ArrayList<videoItem>) : RecyclerView.Adapter<ReelViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReelViewHolder {
        return ReelViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.reel_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ReelViewHolder, position: Int) {
        holder.videoView.setVideoPath(reelItems[position].videoUrl)
        holder.videoView.setOnPreparedListener {
            it.start()
            holder.videoView.layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT) // credit: ChatGPT
        }
    }

    override fun getItemCount(): Int {
        return reelItems.size;
    }
}

class ReelViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {
    val videoView : VideoView = view.videoView
}