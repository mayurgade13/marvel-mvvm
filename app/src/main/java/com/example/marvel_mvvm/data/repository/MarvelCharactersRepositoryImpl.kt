package com.example.marvel_mvvm.data.repository

import com.example.marvel_mvvm.data.ApiService
import com.example.marvel_mvvm.data.DataProvider
import com.example.marvel_mvvm.common.Resource
import com.example.marvel_mvvm.data.remote.dto.MarvelCharacters
class MarvelCharactersRepositoryImpl(
    private val apiService: ApiService,
): MarvelCharactersRepository, DataProvider() {

    override suspend fun getMarvelCharacters(
        timeStamp: String,
        apiKey: String,
        hash: String
    ): Resource<MarvelCharacters> {
        return getApiResponse {
            apiService.getMarvelCharacters(timeStamp, apiKey, hash)
        }
    }
}
