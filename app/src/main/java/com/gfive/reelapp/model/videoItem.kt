package com.gfive.reelapp.model

data class videoItem (
    var videoUrl: String = "",
    var videoId: String = "",
    var videoTitle: String = "",
    var videoDesc : String = "",
    var videoCreator : String = "",
    var videoLikes: Int = 0,
    var videoSaved: Boolean = false
)