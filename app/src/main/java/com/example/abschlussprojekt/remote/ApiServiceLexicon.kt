package com.example.abschlussprojekt.remote


import com.example.abschlussprojekt.data.model.PlantList
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://trefle.io/api/v1/"

private val moshi = Moshi.Builder()
    .add(com.squareup.moshi.KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService{
    @GET("plants?token=")
    suspend fun getPlants(
        @Query("page") page: Int,
        @Query("token") token: String,
        @Query("term") term: String
    ): PlantList

    @GET("plants/search?token=")
    suspend fun getPlantsSearch(@Query("token") token: String,@Query("q") term: String) : PlantList
}

object PlantApi{
    val retrofitService: ApiService by lazy {retrofit.create(ApiService::class.java)}
}