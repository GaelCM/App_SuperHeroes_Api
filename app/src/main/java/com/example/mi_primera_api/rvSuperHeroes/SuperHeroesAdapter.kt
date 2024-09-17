package com.example.mi_primera_api.rvSuperHeroes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mi_primera_api.Data.SuperHeroDataResponse
import com.example.mi_primera_api.R

class SuperHeroesAdapter(var DataLista:List<SuperHeroDataResponse.SuperHeroItem>):RecyclerView.Adapter<SuperHeroViewHolder>(){

    fun updateData(DataLista:List<SuperHeroDataResponse.SuperHeroItem>){
        this.DataLista=DataLista
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.superhero_item,parent,false)
        return SuperHeroViewHolder(view)
    }

    override fun getItemCount(): Int {
        return DataLista.size
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.Render(DataLista[position])
    }

}