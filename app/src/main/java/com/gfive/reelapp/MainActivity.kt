package com.gfive.reelapp

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.gfive.reelapp.adapter.ReelAdapter
import com.gfive.reelapp.model.videoItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        val reelItems = ArrayList<videoItem>()

        reelItems.add(videoItem("android.resource://$packageName/raw/a"))
        reelItems.add(videoItem("android.resource://$packageName/raw/b"))
        reelItems.add(videoItem("android.resource://$packageName/raw/c"))
        reelItems.add(videoItem("android.resource://$packageName/raw/d"))
        reelItems.add(videoItem("android.resource://$packageName/raw/e"))
        reelItems.add(videoItem("android.resource://$packageName/raw/f"))
        reelItems.add(videoItem("android.resource://$packageName/raw/g"))
        viewPager.adapter = ReelAdapter(reelItems)
    }
}