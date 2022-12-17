package com.example.marvel_mvvm.domain.usecase

import com.example.marvel_mvvm.data.DataProvider
import com.example.marvel_mvvm.common.Resource
import com.example.marvel_mvvm.data.remote.dto.MarvelCharacters
import com.example.marvel_mvvm.data.repository.MarvelCharactersRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MarvelCharactersUseCaseImpl(
    private val marvelCharactersRepository: MarvelCharactersRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): MarvelCharactersUseCase, DataProvider() {

    override suspend fun invoke(
        timeStamp: String,
        apiKey: String,
        hash: String
    ): Flow<Resource<MarvelCharacters>> {
        return flow {
            emit(Resource.Loading())
            emit(marvelCharactersRepository.getMarvelCharacters(timeStamp, apiKey, hash))
        }.flowOn(dispatcher)
    }
}
