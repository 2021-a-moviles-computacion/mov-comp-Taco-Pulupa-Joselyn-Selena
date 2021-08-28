package com.example.deber_02

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.utils.setImage

class Bandeja(
    private val context: MainActivity,
    private val listaBandeja: List<ClassBandeja>,
    private val recyclerView: RecyclerView,
): RecyclerView.Adapter<Bandeja.MyViewHolder>() {
    inner class  MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val list = mutableListOf<CarouselItem>()
        var titulo: TextView
        var descript: TextView
        var image1: ImageView
        var image2: ImageView

        init {


            titulo = view.findViewById(R.id.tituloTextView)
            descript = view.findViewById(R.id.descritiotextView)
            image1 = view.findViewById(R.id.IconimageView1)
            image2 = view.findViewById(R.id.IconimageView2)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_bandeja_view, //Definimos la vista de nuestro recyvler
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val vid = listaBandeja[position]
        holder.titulo.text = vid.titulo
        holder.descript.text = vid.description
        holder.image1.setImageResource(R.drawable.ic_baseline_person_24)
        holder.image2.setImage(CarouselItem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS59YZ5ZS-7LAPTknb4CT9LzT6vJjkpZpXqHQ&usqp=CAU"))

    }

    override fun getItemCount(): Int {
        return listaBandeja.size
    }

}