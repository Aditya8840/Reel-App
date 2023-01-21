package com.gfive.reelapp.fragments

import android.app.ActionBar.LayoutParams
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.VideoView
import com.gfive.reelapp.R


class VideoSegment : Fragment() {
    private lateinit var videoSegment1: VideoView
    private lateinit var videoSegment2: VideoView
    private lateinit var videoSegment3: VideoView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_video_segment, container, false)
        videoSegment1 = view.findViewById(R.id.videoView1)
        videoSegment2 = view.findViewById(R.id.videoView2)
        videoSegment3 = view.findViewById(R.id.videoView3)

        videoSegment1.setOnClickListener { navigateToNewFragment("Video Segment 1") }
        videoSegment2.setOnClickListener { navigateToNewFragment("Video Segment 2") }
        videoSegment3.setOnClickListener { navigateToNewFragment("Video Segment 3") }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videoSegment1.setVideoPath("android.resource://${requireActivity().packageName}/raw/a")
        videoSegment2.setVideoPath("android.resource://${requireActivity().packageName}/raw/b")
        videoSegment3.setVideoPath("android.resource://${requireActivity().packageName}/raw/c")

        videoSegment1.setOnPreparedListener { mp -> mp.setVolume(0f, 0f) }
        videoSegment2.setOnPreparedListener { mp -> mp.setVolume(0f, 0f) }
        videoSegment3.setOnPreparedListener { mp -> mp.setVolume(0f, 0f) }

        videoSegment1.start()
        videoSegment2.start()
        videoSegment3.start()
    }

    private fun navigateToNewFragment(text: String) {
        val newFragment = TextFragment.newInstance(text)
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}