package com.example.android_moviles_2020_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class HHttpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hhttp)
        metodoGet()
        metodoPost()
    }

    fun metodoGet(){
        "https://jsonplaceholder.typicode.com/posts/1"
            .httpGet()
            .responseString { req, resm ,result ->
                when(result){
                    is Result.Failure ->{
                        val error = result.getException()
                        Log.i("http-klaxon","error ${error}")
                    }
                    is Result.Success ->{
                        val getSrting = result.get()
                        Log.i("http-klaxon","getString ${getSrting}")

//                        "https://jsonplaceholder.typicode.com/posts/1" -> 1
//                        "https://jsonplaceholder.typicode.com/posts/1" -> muchos
                        //castear la respuesta de la aplicacion movil., consumo de datos del servidor hacia el movil
                        val post = Klaxon()
                            .parse<IPostHttp>(getSrting)
                        //.parseArray<IPostHttp>(getSrting)
                        Log.i("http-klaxon"," ${post?.body}")
                    }

                }

            }
    }

    fun metodoPost(){
        val parametros: List<Pair<String,*>> = listOf(
            "title" to "Titulo moviles",
            "body" to "Descripcion moviles",
            "userId" to 1
        )

        "https://jsonplaceholder.typicode.com/posts"
            .httpPost(parametros)
            .responseString(){rep, res, result ->
                when(result) {
                    is Result.Failure ->{
                        val error = result.getException()
                        Log.i("http-klaxon","error ${error}")
                    }
                    is Result.Success -> {
                        val getSrting = result.get()
                        Log.i("http-klaxon", "getString ${getSrting}")
                    }
                }
            }


    }
}