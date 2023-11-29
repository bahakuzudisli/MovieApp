package com.bahakuzudisli.movieapp.model


import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    @SerializedName("name")
    val name: String?
)