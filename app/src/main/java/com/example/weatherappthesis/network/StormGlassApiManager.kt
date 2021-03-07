package com.example.weatherappthesis.network

import com.example.weatherappthesis.ApiInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StormGlassApiManager {
    companion object {
        private const val URL = "https://api.stormglass.io/v2/"
    }

    fun getService(): StormGlassApi {
        val client = OkHttpClient().newBuilder()
            .addInterceptor(ApiInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(StormGlassApi::class.java)
    }
}