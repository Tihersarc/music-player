package com.app.musicplayer

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class RecyclerAdapter(private var mSongs: MutableList<Song> = mutableListOf()) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var onItemClickListener: AdapterView.OnItemClickListener? = null
    private var isMediaPlayerPrepared = false

    fun setOnItemClickListener(listener: AdapterView.OnItemClickListener) {
        this.onItemClickListener = listener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val songImage: ImageView = itemView.findViewById(R.id.image)
        val songName: TextView = itemView.findViewById(R.id.name)
        val playButton : Button = itemView.findViewById(R.id.playButton)
        val pauseButton : Button = itemView.findViewById(R.id.pauseButton)
        val stopButton : Button = itemView.findViewById(R.id.stopButton)
    }

    private lateinit var context : Context;
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val songView = inflater.inflate(R.layout.song_container, parent, false)

        return ViewHolder(songView)
    }

    override fun getItemCount(): Int {
        return mSongs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var mediaPlayer: MediaPlayer? = null

        val song: Song = mSongs[position]
        val imageUrl = song.image

        Picasso.get()
            .load(imageUrl)
            .into(holder.songImage, object : Callback {
                override fun onSuccess() {
                    Log.d("Picasso", "Image loaded successfully for item: ${song.name}")
                }
                override fun onError(e: Exception?) {
                    e?.printStackTrace()
                    Log.e("Picasso", "Error loading image for item: ${song.name}", e)
                }
            })

        holder.songName.text = song.name

        if (song.mediaPlayer == null) {
            song.mediaPlayer = MediaPlayer.create(context, song.path)
        }

        holder.playButton.setOnClickListener {
            val mediaPlayer = song.mediaPlayer
            mediaPlayer?.apply {
                if (!isPlaying) {
                    start()
                }
            }
        }

        holder.pauseButton.setOnClickListener {
            val mediaPlayer = song.mediaPlayer
            mediaPlayer?.apply {
                if (isPlaying) {
                    pause()
                }
            }
        }

        holder.stopButton.setOnClickListener {
            val mediaPlayer = song.mediaPlayer
            mediaPlayer?.apply {
                stop()
                prepareAsync()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addSongs(newSongs: List<Song>) {
        mSongs.addAll(newSongs)
        notifyDataSetChanged()
    }
}
