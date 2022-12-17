package com.example.marvel_mvvm.domain.usecase

import com.example.marvel_mvvm.common.Resource
import com.example.marvel_mvvm.data.remote.dto.MarvelCharacters
import kotlinx.coroutines.flow.Flow

interface MarvelCharactersUseCase {

    suspend operator fun invoke(
        timeStamp: String,
        apiKey: String,
        hash: String
    ): Flow<Resource<MarvelCharacters>>
}
