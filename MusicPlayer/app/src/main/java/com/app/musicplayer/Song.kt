package com.app.musicplayer

import android.media.MediaPlayer

data class Song (
    var name : String,
    var path : Int,
    var image : String,
    var mediaPlayer: MediaPlayer? = null
)