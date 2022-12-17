package com.example.marvel_mvvm.data

import com.example.marvel_mvvm.data.remote.dto.MarvelCharacters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val GET_MARVEL_CHARACTERS = "/v1/public/characters"

        // Keys
        const val MARVEL_API_TIMESTAMP = "ts"
        const val MARVEL_API_KEY = "apikey"
        const val MARVEL_API_HASH = "hash"
    }

    @GET(GET_MARVEL_CHARACTERS)
    suspend fun getMarvelCharacters(
        @Query(MARVEL_API_TIMESTAMP) timestamp: String,
        @Query(MARVEL_API_KEY) apiKey: String,
        @Query(MARVEL_API_HASH) hash: String
    ): Response<MarvelCharacters>
}
