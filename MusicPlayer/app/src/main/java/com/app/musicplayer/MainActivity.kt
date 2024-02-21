package com.app.musicplayer

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Picasso.setSingletonInstance(
            Picasso.Builder(this)
                .loggingEnabled(true)
                .build())


        var songPath = R.raw.slipknot_duality


        var button : Button = findViewById(R.id.button)

        button.setOnClickListener {
            val mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(applicationContext, Uri.parse("android.resource://${packageName}/${songPath}"))
                prepare()
                start()
            }
        }
    }
}