package com.liebxsantos.pexelswallpapersapp.framework.network.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient<T>(url: String, okHttpClient: OkHttpClient, converterFactory: GsonConverterFactory) {
    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .build()

    fun createApi(apiInterface: Class<T>) = retrofit.create(apiInterface)
}