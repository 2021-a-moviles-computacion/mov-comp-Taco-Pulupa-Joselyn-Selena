package com.example.deber_02

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVTendencias (
    private val context: MainActivity,
    private val videos: List<Videos>,
    private val recyclerView: RecyclerView,
): RecyclerView.Adapter<RVTendencias.MyViewHolder>() {
    inner class  MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var enla: String

        init {
            val recyclerViewEntrenador = view.findViewById<RecyclerView>(
                R.id.rcv_tendencias
            )

            enla =""
            val listaTendencias = arrayListOf<ClassTendencias>()

            listaTendencias
                .add(ClassTendencias("Musica","America","302M"))
            listaTendencias
                .add(ClassTendencias("Nuevo","America","302M"))
            listaTendencias
                .add(ClassTendencias("Arte","America","302M"))
            listaTendencias
                .add(ClassTendencias("Desafios","America","302M"))
            listaTendencias
                .add(ClassTendencias("Musica","America","302M"))
            listaTendencias
                .add(ClassTendencias("Nuevo","America","302M"))
            listaTendencias
                .add(ClassTendencias("Arte","America","302M"))
            listaTendencias
                .add(ClassTendencias("Desafios","America","302M"))

            iniciarRecyclerViewBuscar(
                listaTendencias,
                context,
                recyclerViewEntrenador
            )

        }

    }

    fun iniciarRecyclerViewBuscar(
        listaTendencias: List<ClassTendencias>,
        actividad: MainActivity,
        recyclerView: RecyclerView
    ){
        val adaptador = Tendencias(
            actividad,
            listaTendencias,
            recyclerView
        )
        recyclerView.adapter= adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.tendencias_view, //Definimos la vista de nuestro recyvler
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RVTendencias.MyViewHolder, position: Int) {
        val vid = videos[position]
        holder.enla = vid.enlace.toString()
    }

    override fun getItemCount(): Int {
        return videos.size
    }


}