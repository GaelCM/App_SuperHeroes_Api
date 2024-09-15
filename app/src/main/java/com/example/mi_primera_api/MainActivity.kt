package com.example.mi_primera_api

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mi_primera_api.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var bindig:ActivityMainBinding
    //retrofit aqui
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bindig=ActivityMainBinding.inflate(layoutInflater)
        retrofit=getRetrofit()
        setContentView(bindig.root)

        initCompoents()


    }

    fun initCompoents(){

        bindig.buscarBtn.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                getSuperHeroes(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    fun getSuperHeroes(name:String){
        CoroutineScope(Dispatchers.IO).launch { //Creamos ua corrutia para enviar el proceso de pedir la info a un segundo thread y ahorrar recursos
            var res=retrofit.create(SuperHeroesService::class.java).getSuperHeroesByName(name)
            if (res.isSuccessful){
                var lista=res.body()?.results
                if (lista != null) {
                    lista.forEach {
                        Log.i("dd","${it.nombre}")
                    }
                }
            }
        }

    }

    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}