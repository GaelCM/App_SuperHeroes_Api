package com.example.mi_primera_api.Data

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(var results:List<SuperHeroItem>){

    data class SuperHeroItem(
        var id:String,
        @SerializedName("name") var nombre:String,
        @SerializedName("image") var foto:SuperHeroFotoItem
    ){

    }

    data class SuperHeroFotoItem(
        var url:String
    ){

    }

}