package com.example.deber_02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.utils.setImage

class VideosViewLayout(
    private val context: MainActivity,
    private val videos: List<Videos>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<VideosViewLayout.MyViewHolder>(){
    inner class  MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        var enla = ""
        var video: ImageView
        var user: TextView
        var hash: TextView
        var music: TextView
        init {

            video = view.findViewById(R.id.vw_videoView)
            user = view.findViewById(R.id.txv_user)
            hash = view.findViewById(R.id.txv_hash)
            music = view.findViewById(R.id.txv_music)

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.videos_view, //Definimos la vista de nuestro recyvler
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val vid = videos[position]
        holder.enla = vid.enlace.toString()

        //holder.video.setImage(CarouselItem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS59YZ5ZS-7LAPTknb4CT9LzT6vJjkpZpXqHQ&usqp=CAU"))
        holder.video.setImage(CarouselItem(holder.enla))
        holder.user.text = vid.user
        holder.hash.text = vid.hash
        holder.music.text = vid.music

    }

    override fun getItemCount(): Int {
       return videos.size
    }
}