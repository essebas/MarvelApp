package com.zebas2.marvelapp.domain.usecase

import com.zebas2.marvelapp.data.model.APIResponse
import com.zebas2.marvelapp.data.util.Resource
import com.zebas2.marvelapp.domain.repository.CharactersRepository

class GetCharacterDetailsUseCase(private val charactersRepository: CharactersRepository) {

    suspend fun execute(characterId: String): Resource<APIResponse> =
        charactersRepository.getCharacterDetail(characterId)

}