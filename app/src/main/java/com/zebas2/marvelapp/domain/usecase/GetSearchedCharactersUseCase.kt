package com.zebas2.marvelapp.domain.usecase

import com.zebas2.marvelapp.data.model.Character
import com.zebas2.marvelapp.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow


class GetSearchedCharactersUseCase(private val charactersRepository: CharactersRepository) {

    fun execute():Flow<List<Character>> = charactersRepository.getSaveCharacter()

}