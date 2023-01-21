package com.gfive.reelapp

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.gfive.reelapp.adapter.ReelAdapter
import com.gfive.reelapp.adapter.SavedFlowAdapter
import com.gfive.reelapp.fragments.VideoSegment
import com.gfive.reelapp.model.videoItem
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.reel_layout.*
import kotlinx.android.synthetic.main.reel_layout.view.*
import kotlinx.android.synthetic.main.savedboot_sheetdialog.*

class MainActivity : AppCompatActivity() {
    lateinit var savedFlow : ArrayList<videoItem>
    lateinit var reelItems : ArrayList<videoItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        bottom_nav.selectedItemId = R.id.reel
        bottom_nav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.reel -> {
                    fragment_container.visibility = View.GONE
                    viewPager.visibility = View.VISIBLE
                    flow.visibility = View.VISIBLE
                    saved_reel.visibility = View.VISIBLE
                    (viewPager.getChildAt(viewPager.currentItem)).videoView.start()
                    true
                }
                R.id.explore -> {
                    (viewPager.getChildAt(viewPager.currentItem)).videoView.pause()
                    fragment_container.visibility = View.VISIBLE
                    viewPager.visibility = View.GONE
                    flow.visibility = View.GONE
                    saved_reel.visibility = View.GONE
                    val videoFragment = VideoSegment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, videoFragment)
                        .commit()
                    true
                }
                else -> false
            }
        }
        reelItems = ArrayList<videoItem>()
        savedFlow = ArrayList<videoItem>()
        reelItems.add(videoItem("android.resource://$packageName/raw/a","a"))
        reelItems.add(videoItem("android.resource://$packageName/raw/b","b"))
        reelItems.add(videoItem("android.resource://$packageName/raw/c","c"))
        reelItems.add(videoItem("android.resource://$packageName/raw/d","d"))
        reelItems.add(videoItem("android.resource://$packageName/raw/e","e"))
        reelItems.add(videoItem("android.resource://$packageName/raw/f","f"))
        reelItems.add(videoItem("android.resource://$packageName/raw/g","g"))
        viewPager.adapter = ReelAdapter(reelItems,this)
        saved_reel.setOnClickListener {
            savedFlows()
        }
    }

    //credit: @ChatGPT
    fun bottomDialogMore(position: Int){
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.bottomsheet_dialog)
        dialog.show()
    }

    fun savedFlows(){
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.savedboot_sheetdialog)
        val layoutManager = GridLayoutManager(this, 2)
        dialog.recycler_view.layoutManager = layoutManager
        dialog.recycler_view.adapter = SavedFlowAdapter(savedFlow,this)
        dialog.show()
    }

    fun saveFlow(position: Int){
        savedFlow.add(reelItems[position])
    }

    fun deleteFlow(position: Int){
        savedFlow.removeAt(position)
    }
}