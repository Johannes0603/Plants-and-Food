package com.example.abschlussprojekt.remote

import com.example.abschlussprojekt.data.model.PlantList
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://perenual.com/api/"

private val moshi = Moshi.Builder()
    .add(com.squareup.moshi.KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService{
    @GET("species-list?page=1&key=sk-bSc364f5bf0a3c7902066")
    suspend fun getPlant(@Query("term") term: String) : PlantList
}
object PlantApi{
    val retrofitService: ApiService by lazy {retrofit.create(ApiService::class.java)}
}