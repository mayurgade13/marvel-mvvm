package com.example.marvel_mvvm.data.repository

import com.example.marvel_mvvm.common.Resource
import com.example.marvel_mvvm.data.remote.dto.MarvelCharacters

interface MarvelCharactersRepository {

    suspend fun getMarvelCharacters(
        timeStamp: String,
        apiKey: String,
        hash: String
    ): Resource<MarvelCharacters>
}
