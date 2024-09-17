package com.example.mi_primera_api.rvSuperHeroes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mi_primera_api.Data.SuperHeroDataResponse
import com.example.mi_primera_api.databinding.ActivityMainBinding
import com.example.mi_primera_api.databinding.SuperheroItemBinding

class SuperHeroViewHolder(view: View):RecyclerView.ViewHolder(view){

    private var binding=SuperheroItemBinding.bind(view)

    fun Render(superHeroItem: SuperHeroDataResponse.SuperHeroItem){

        binding.superHeroName.text=superHeroItem.nombre.toString()

    }

}