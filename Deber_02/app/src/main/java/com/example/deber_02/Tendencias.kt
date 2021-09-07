package com.example.deber_02

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.utils.setImage


class Tendencias(
    private val context: MainActivity,
    private val listaTendencias: List<ClassTendencias>,
    private val recyclerView: RecyclerView
):RecyclerView.Adapter<Tendencias.MyViewHolder>() {
    inner class  MyViewHolder(view: View,viewType: Int ): RecyclerView.ViewHolder(view){

        val list = mutableListOf<CarouselItem>()
        lateinit var carouselPrincipal:ImageCarousel
        lateinit var titulo: TextView
        lateinit var hash: TextView
        lateinit var numero: TextView
        lateinit var image1: ImageView
        lateinit var image2: ImageView
        lateinit var image3: ImageView
        lateinit var image4: ImageView
        lateinit var image5: ImageView
        lateinit var image6: ImageView
        var view_Type: Int = 0
        var contador: Int = 0

        init {

            Log.i("init","Entro viewType${viewType}")
               // carouselPrincipal = view.findViewById(R.id.carousel)
            if(viewType == 1){
                carouselPrincipal = view.findViewById(R.id.carousel)
                view_Type = 1
                Log.i("init","viewType${viewType}")
                contador = contador+1
            }

            if(viewType == 0){
                titulo = view.findViewById(R.id.txv_titulo)
                hash = view.findViewById(R.id.hashtextView)
                numero = view.findViewById(R.id.NumerotextView)
                image1 = view.findViewById(R.id.img1)
                image2 = view.findViewById(R.id.img2)
                image3 = view.findViewById(R.id.img3)
                image4 = view.findViewById(R.id.img4)
                image5 = view.findViewById(R.id.img5)
                image6 = view.findViewById(R.id.img6)
                view_Type = 0
                contador = contador+1
                Log.i("init","viewType${viewType}")
            }
            Log.i("init","Paso viewType${viewType}")

            Log.i("init","Salio viewType${viewType}")


        }

    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return 1
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

       var conut:Int = 0
        var layout = R.layout.item_scroll_tendencias
        if (viewType == 0){
            layout = R.layout.recicler_view_tendencias
            Log.i("onCreateViewHolder","conut${conut}")
            Log.i("onCreateViewHolder","viewType${viewType}")
        }
        if (viewType == 1){
            layout = R.layout.item_scroll_tendencias
            conut = 1
            Log.i("onCreateViewHolder","conut${conut}")
            Log.i("onCreateViewHolder","viewType${viewType}")
        }
        var itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                layout, //Definimos la vista de nuestro recyvler
                parent,
                false
            )

        return MyViewHolder(itemView,conut)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Log.i("onBindViewHolder","position ${position}")
        if(holder.view_Type == 1){
            Log.i("onBindViewHolder","Entro holder ${holder.view_Type}")
            holder.list.add(CarouselItem(imageUrl = "https://www.gifmaniacos.es/wp-content/uploads/2021/03/gifmaniacos.es-2-1.gif"))
            holder.list.add(CarouselItem(imageUrl = "https://masquedigital.com/wp-content/uploads/2020/04/Ganar-seguidores-en-Tik-Tok-M%C3%A1sQueDigital.png"))
            holder.list.add(CarouselItem(imageUrl = "https://c.tenor.com/e_htBDn7Nh4AAAAd/tick-tock-tiktok.gif"))
            holder.list.add(CarouselItem(imageUrl = "https://media1.tenor.com/images/450095c24ed810b7ece619b7202b7d6c/tenor.gif?itemid=15882511"))
            holder.carouselPrincipal.addData(holder.list)

            Log.i("onBindViewHolder","Es LA Pos${holder.view_Type}")

        }
        if(holder.view_Type == 0){
            val vid = listaTendencias[position -1]
            Log.i("onBindViewHolder","position ${listaTendencias[position-1]}")
            holder.titulo.text = vid.titulo
            holder.hash.text = vid.hash
            holder.numero.text = vid.numero
            holder.image1.setImage(CarouselItem("https://www.educaciontrespuntocero.com/wp-content/uploads/2019/06/homer.gif"))
            holder.image2.setImage(CarouselItem("https://i1.wp.com/codigoespagueti.com/wp-content/uploads/2014/06/GIF.gif?fit=640%2C360&quality=80&ssl=1"))
            holder.image3.setImage(CarouselItem("https://tercetocomunicacion.es/wp-content/uploads/2017/05/minions.gif"))
            holder.image4.setImage(CarouselItem("https://lanetaneta.com/wp-content/uploads/2019/10/El-GIF-est%C3%A1-muerto.-Larga-vida-al-GIF.gif"))
            holder.image5.setImage(CarouselItem("https://www.gifmaniacos.es/wp-content/uploads/2021/03/gifmaniacos.es-2-1.gif"))
            holder.image6.setImage(CarouselItem("https://c.tenor.com/e_htBDn7Nh4AAAAd/tick-tock-tiktok.gif"))
            Log.i("onBindViewHolder","Es LA Pos${holder.view_Type}")
        }

    }

    override fun getItemCount(): Int {
       return (listaTendencias.size +1)
    }

}