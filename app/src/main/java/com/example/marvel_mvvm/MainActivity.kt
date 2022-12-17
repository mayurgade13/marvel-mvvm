package com.example.marvel_mvvm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.marvel_mvvm.Extensions.toMD5
import com.example.marvel_mvvm.data.ApiService
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val date = Date()
        val timeStamp = date.time / 1000L
        val hashString = timeStamp.toString().plus(BuildConfig.MARVEL_PRIVATE_KEY).plus(BuildConfig.MARVEL_PUBLIC_KEY).toMD5()

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val retrofit =  Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                Log.d("####", "result - "+apiService.getMarvelCharacters(timeStamp.toString(), BuildConfig.MARVEL_PUBLIC_KEY, hashString))
            }
        }
    }
}