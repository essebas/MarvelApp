package com.zebas2.marvelapp.domain.usecase

import com.zebas2.marvelapp.data.model.APIResponse
import com.zebas2.marvelapp.data.model.Character
import com.zebas2.marvelapp.data.util.Resource
import com.zebas2.marvelapp.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow


class GetSearchedCharactersUseCase(private val charactersRepository: CharactersRepository) {

    suspend fun execute(
        userSearch: String,
        isOfflineConnection: Boolean,
        isTemporalSearch: Boolean
    ): Resource<APIResponse> =
        charactersRepository.getSearchedByNameCharacters(
            userSearch,
            isOfflineConnection,
            isTemporalSearch
        )

}