package com.zebas2.marvelapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.zebas2.marvelapp.data.model.APIResponse
import com.zebas2.marvelapp.data.model.Character
import com.zebas2.marvelapp.data.util.Resource
import com.zebas2.marvelapp.domain.usecase.GetCharactersUseCase
import com.zebas2.marvelapp.domain.usecase.GetSearchedCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "CharactersViewModel"

class CharactersViewModel(
    private val app: Application,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getSearchedCharactersUseCase: GetSearchedCharactersUseCase
) : AndroidViewModel(app) {

    val characters: MutableLiveData<Resource<APIResponse>> = MutableLiveData()
    val searchedCharacters: MutableLiveData<Resource<APIResponse>> = MutableLiveData()


    fun getCharacters(isUserNeededMoreCharacters: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        try {
            characters.postValue(Resource.Loading())
            if (isNetWorkAvailable(app)) {
                val apiResult = getCharactersUseCase.execute(isUserNeededMoreCharacters, false)
                characters.postValue(apiResult)
            } else {
                val apiResult = getCharactersUseCase.execute(isUserNeededMoreCharacters, true)
                characters.postValue(apiResult)
            }
        } catch (e: Exception) {
            Log.e(TAG, "getCharacters: ${e.message.toString()}")
            characters.postValue(Resource.Error("Intente nuevamente mas tarde"))
        }
    }

    fun getSearchCharacters(userSearch: String, isTemporalSearch: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                searchedCharacters.postValue(Resource.Loading())
                if (isNetWorkAvailable(app)) {
                    val apiResult =
                        getSearchedCharactersUseCase.execute(
                            userSearch.removeWhiteSpaces(),
                            false,
                            isTemporalSearch
                        )
                    searchedCharacters.postValue(apiResult)
                } else {
                    val apiResult =
                        getSearchedCharactersUseCase.execute(
                            userSearch.removeWhiteSpaces(),
                            true,
                            isTemporalSearch
                        )
                    searchedCharacters.postValue(apiResult)
                }
            } catch (e: Exception) {
                Log.e(TAG, "getCharacters: ${e.message.toString()}")
                searchedCharacters.postValue(Resource.Error("Intente nuevamente mas tarde"))
            }
        }

    private fun isNetWorkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

    private fun String.removeWhiteSpaces() = replace("\\s+".toRegex(), " ")

}