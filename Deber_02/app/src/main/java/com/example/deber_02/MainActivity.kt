package com.example.deber_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var layoutHeader: ViewGroup
        var layoutBody: ViewGroup


        layoutHeader = findViewById(R.id.header_layout)
        val inflater = LayoutInflater.from(this)
            .inflate(R.layout.header_home,layoutHeader,false)
        layoutHeader.removeAllViews();
        layoutHeader.addView(inflater)


        layoutBody = findViewById(R.id.principal_layout)
        val inflater2 = LayoutInflater.from(this)
            .inflate(R.layout.item_videos_view,layoutBody,false)
        layoutBody.removeAllViews();
        layoutBody.addView(inflater2)

        val recyclerViewEntrenador = inflater2.findViewById<RecyclerView>(R.id.rcv_videos)
        val listaVideos = arrayListOf<Videos>()
        listaVideos
            .add(Videos("https://c.tenor.com/kgfbmZkZ_qAAAAAC/twitter-twitter-users.gif",
                "userPrueba","#parati#series#tarea","musicPrueba1"
            ))
        listaVideos
            .add(Videos("https://c.tenor.com/kgfbmZkZ_qAAAAAC/twitter-twitter-users.gif",
                "userPrueba2","#parati#series#tarea","musicPrueba2"))
        listaVideos
            .add(Videos("https://c.tenor.com/kgfbmZkZ_qAAAAAC/twitter-twitter-users.gif",
                "userPrueba3","#parati#series#tarea","musicPrueba3"))

        iniciarRecyclerView(
            listaVideos,
            this,
            recyclerViewEntrenador
        )


        val menuBar = findViewById<BottomNavigationView>(
            R.id.nav_bar
        )

        menuBar.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
            MenuItem ->
            when(MenuItem.itemId){
                R.id.mnu_home -> {

                    val inflater2 = LayoutInflater.from(this)
                        .inflate(R.layout.item_videos_view,layoutBody,false)
                    layoutBody = findViewById(R.id.principal_layout)
                    layoutBody.removeAllViews();
                    layoutBody.addView(inflater2)

                    val inflater = LayoutInflater.from(this)
                        .inflate(R.layout.header_home,layoutHeader,false)
                    layoutHeader = findViewById(R.id.header_layout)
                    layoutHeader.removeAllViews();
                    layoutHeader.addView(inflater)

                    val recyclerViewEntrenador = inflater2.findViewById<RecyclerView>(
                        R.id.rcv_videos
                    )

                    iniciarRecyclerView(
                        listaVideos,
                        this,
                        recyclerViewEntrenador
                    )


                }
                R.id.mnu_buscar -> {

                    val inflater = LayoutInflater.from(this)
                        .inflate(R.layout.header_tendencia,layoutHeader,false)
                    layoutHeader = findViewById(R.id.header_layout)
                    layoutHeader.removeAllViews();
                    layoutHeader.addView(inflater)

                    val inflater2 = LayoutInflater.from(this)
                        .inflate(R.layout.tendencias_view,layoutBody,false)
                    layoutBody = findViewById(R.id.principal_layout)
                    layoutBody.removeAllViews();
                    layoutBody.addView(inflater2)

                    val recyclerViewEntrenador = inflater2.findViewById<RecyclerView>(
                        R.id.rcv_tendencias
                    )

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
                        this,
                        recyclerViewEntrenador
                    )

                }

                R.id.mnu_not -> {

                    val inflater = LayoutInflater.from(this)
                        .inflate(R.layout.header_bandeja,layoutHeader,false)
                    layoutHeader = findViewById(R.id.header_layout)
                    layoutHeader.removeAllViews();
                    layoutHeader.addView(inflater)

                    val inflater2 = LayoutInflater.from(this)
                        .inflate(R.layout.bandeja_view,layoutBody,false)
                    layoutBody = findViewById(R.id.principal_layout)
                    layoutBody.removeAllViews();
                    layoutBody.addView(inflater2)

                    val recyclerViewEntrenador = inflater2.findViewById<RecyclerView>(
                        R.id.rcv_bandeja
                    )

                    val listaBandeja = arrayListOf<ClassBandeja>()

                    listaBandeja
                        .add(ClassBandeja("uruarioEjemplo, usuario2 y 13 usuarios más","ha dicho que le gusta tu video"))
                    listaBandeja
                        .add(ClassBandeja("uruarioEjemplo, usuario2 y 13 usuarios más","ha dicho que le gusta tu video"))
                    listaBandeja
                        .add(ClassBandeja("uruarioEjemplo, usuario2 y 13 usuarios más","ha dicho que le gusta tu video"))
                    listaBandeja
                        .add(ClassBandeja("uruarioEjemplo, usuario2 y 13 usuarios más","ha dicho que le gusta tu video"))
                    listaBandeja
                        .add(ClassBandeja("uruarioEjemplo, usuario2 y 13 usuarios más","ha dicho que le gusta tu video"))
                    listaBandeja
                        .add(ClassBandeja("uruarioEjemplo, usuario2 y 13 usuarios más","ha dicho que le gusta tu video"))
                    listaBandeja
                        .add(ClassBandeja("uruarioEjemplo, usuario2 y 13 usuarios más","ha dicho que le gusta tu video"))
                    listaBandeja
                        .add(ClassBandeja("uruarioEjemplo, usuario2 y 13 usuarios más","ha dicho que le gusta tu video"))
                    listaBandeja
                        .add(ClassBandeja("uruarioEjemplo, usuario2 y 13 usuarios más","ha dicho que le gusta tu video"))
                    listaBandeja
                        .add(ClassBandeja("uruarioEjemplo, usuario2 y 13 usuarios más","ha dicho que le gusta tu video"))


                    iniciarRecyclerViewBandeja(
                        listaBandeja,
                        this,
                        recyclerViewEntrenador
                    )
                }

            }
            return@OnItemSelectedListener true
        })
    }

    fun iniciarRecyclerView(
        lista: List<Videos>,
        actividad: MainActivity,
        recyclerView: RecyclerView
    ){
        val adaptador = VideosViewLayout(
            actividad,
            lista,
            recyclerView
        )
        recyclerView.adapter= adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }

    fun iniciarRecyclerViewBuscar(
        lista: List<ClassTendencias>,
        actividad: MainActivity,
        recyclerView: RecyclerView
    ){
        val adaptador = Tendencias(
            actividad,
            lista,
            recyclerView
        )
        recyclerView.adapter= adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }

    fun iniciarRecyclerViewBandeja(
        lista: List<ClassBandeja>,
        actividad: MainActivity,
        recyclerView: RecyclerView
    ){
        val adaptador = Bandeja(
            actividad,
            lista,
            recyclerView
        )
        recyclerView.adapter= adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }


}
