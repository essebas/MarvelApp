package com.zebas2.marvelapp.domain.usecase

import com.zebas2.marvelapp.data.model.APIResponse
import com.zebas2.marvelapp.data.util.Resource
import com.zebas2.marvelapp.domain.repository.CharactersRepository

class GetCharactersUseCase(private val charactersRepository: CharactersRepository) {

    suspend fun execute(): Resource<APIResponse> = charactersRepository.getCharacters()

}