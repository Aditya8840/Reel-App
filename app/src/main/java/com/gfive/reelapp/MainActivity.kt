package com.gfive.reelapp

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.gfive.reelapp.adapter.ReelAdapter
import com.gfive.reelapp.adapter.SavedFlowAdapter
import com.gfive.reelapp.model.videoItem
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.reel_layout.*
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