package com.gfive.reelapp.adapter

import android.media.MediaMetadataRetriever
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gfive.reelapp.MainActivity
import com.gfive.reelapp.R
import com.gfive.reelapp.model.videoItem
import kotlinx.android.synthetic.main.saved_flow_layout.view.*

class SavedFlowAdapter(val savedFlow: ArrayList<videoItem>, val listener : MainActivity) :
    RecyclerView.Adapter<FlowViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowViewHolder {
        return FlowViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.saved_flow_layout, parent, false))
    }

    override fun onBindViewHolder(holder: FlowViewHolder, position: Int) {
        val retriever = MediaMetadataRetriever()
        val resourceName = savedFlow[position].videoId
        val resourceId = listener.resources.getIdentifier(resourceName, "raw", listener.packageName)
        val file = listener.resources.openRawResourceFd(resourceId)
        retriever.setDataSource(file.fileDescriptor, file.startOffset, file.length)
        file.close()
        val bitmap = retriever.frameAtTime
        holder.thumbnail.setImageBitmap(bitmap)
        retriever.release()
    }

    override fun getItemCount(): Int {
        return savedFlow.size
    }
}

class FlowViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val thumbnail = view.thumbnailSavedFlow
}