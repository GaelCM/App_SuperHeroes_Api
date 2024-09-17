package com.example.mi_primera_api

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mi_primera_api.Data.SuperHeroDataResponse
import com.example.mi_primera_api.databinding.ActivityMainBinding
import com.example.mi_primera_api.rvSuperHeroes.SuperHeroesAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var bindig:ActivityMainBinding
    //retrofit aqui
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: SuperHeroesAdapter
    private var Data:List<SuperHeroDataResponse.SuperHeroItem> = emptyList()

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

        adapter=SuperHeroesAdapter(Data)
        bindig.rvSuperHeroes.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        bindig.rvSuperHeroes.adapter=adapter

    }

    fun getSuperHeroes(name:String){
        bindig.loading.isVisible=true
        CoroutineScope(Dispatchers.IO).launch { //Creamos ua corrutia para enviar el proceso de pedir la info a un segundo thread y ahorrar recursos
            var res=retrofit.create(SuperHeroesService::class.java).getSuperHeroesByName(name)
            if (res.isSuccessful){
                Data= res.body()?.results.orEmpty()
                if (Data != null) {
                    runOnUiThread {
                        adapter.updateData(Data)
                        adapter.notifyDataSetChanged()
                        bindig.loading.isVisible=false
                    }

                    Data.forEach {
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