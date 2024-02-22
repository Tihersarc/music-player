package com.app.musicplayer

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class RecyclerAdapter(private var mSongs: MutableList<Song> = mutableListOf()) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var onItemClickListener: AdapterView.OnItemClickListener? = null

    fun setOnItemClickListener(listener: AdapterView.OnItemClickListener) {
        this.onItemClickListener = listener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val songImage: ImageView = itemView.findViewById(R.id.image)
        val songName: TextView = itemView.findViewById(R.id.name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val songView = inflater.inflate(R.layout.song_container, parent, false)

        return ViewHolder(songView)
    }

    override fun getItemCount(): Int {
        return mSongs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addSongs(newSongs: List<Song>) {
        mSongs.addAll(newSongs)
        notifyDataSetChanged()
    }
}