package com.app.musicplayer

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerAdapter = RecyclerAdapter()
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = recyclerAdapter

        Picasso.setSingletonInstance(
            Picasso.Builder(this)
                .loggingEnabled(true)
                .build())

        val song1 = Song("Duality", R.raw.slipknot_duality,"https://i.scdn.co/image/ab67616d0000b2736b3463e7160d333ada4b175a")
        val song2 = Song("Through the Fire and Flames", R.raw.dragonforce_through_the_fire_and_flames,"https://i.discogs.com/OB95zv7DEHBJytdBfLOLPOF8RCpUjkNHgSnvSL8K4sE/rs:fit/g:sm/q:90/h:600/w:588/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTI1Mjk1/NTktMTUwMzEwMDI1/Ni05NjI5LmpwZWc.jpeg")
        val song3 = Song("Nightcall", R.raw.kavinsky_nightcall,"https://upload.wikimedia.org/wikipedia/en/5/5b/Kavinsky_Nightcall_2010.png")

        var songList = ArrayList<Song>()
        songList.add(song1)
        songList.add(song2)
        songList.add(song3)

        recyclerAdapter.addSongs(songList)
    }
}