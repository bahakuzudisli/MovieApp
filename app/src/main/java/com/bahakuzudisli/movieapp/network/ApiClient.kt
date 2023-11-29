package com.bahakuzudisli.movieapp.network

import com.bahakuzudisli.movieapp.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    fun getClient(): ApiService {
        // HTTP isteği loglarını anlamak için interceptor ekleniyor.
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        // OkHttpClient örneği oluşturuluyor ve yapılandırılıyor.
        val client: OkHttpClient =
            OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS).build()

        // Retrofit ile API'ye bağlanılıyor, JSON dönüştürme ekleniyor, istek yapılıyor
        // ve gerekli ApiService arayüzü için bir örnek oluşturuluyor.
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
            .create(ApiService::class.java)
    }
}