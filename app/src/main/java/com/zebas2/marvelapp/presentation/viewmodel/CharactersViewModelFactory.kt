package com.zebas2.marvelapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zebas2.marvelapp.domain.usecase.GetCharactersUseCase
import com.zebas2.marvelapp.domain.usecase.GetSearchedCharactersUseCase

class CharactersViewModelFactory(
    private val app: Application,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getSearchedCharactersUseCase: GetSearchedCharactersUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharactersViewModel(app, getCharactersUseCase, getSearchedCharactersUseCase) as T
    }

}