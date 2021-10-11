package com.zebas2.marvelapp.domain.usecase

import com.zebas2.marvelapp.data.model.APIResponse
import com.zebas2.marvelapp.data.util.Resource
import com.zebas2.marvelapp.domain.repository.CharactersRepository

class GetCharactersUseCase(private val charactersRepository: CharactersRepository) {

    suspend fun execute(
        isUserNeededMoreCharacters: Boolean,
        isOfflineConnection: Boolean
    ): Resource<APIResponse> =
        charactersRepository.getCharacters(isUserNeededMoreCharacters, isOfflineConnection)

    fun getAllCharacters(limit: Int, offset: Int) =
        charactersRepository.getCharactersPage(limit, offset)

}