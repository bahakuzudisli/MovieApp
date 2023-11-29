package com.bahakuzudisli.movieapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahakuzudisli.movieapp.model.MovieDetailResponse
import com.bahakuzudisli.movieapp.network.ApiClient
import com.bahakuzudisli.movieapp.util.Constants
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    val movieRespone: MutableLiveData<MovieDetailResponse> = MutableLiveData()
    val isLoading = MutableLiveData(false)
    val errorMessage: MutableLiveData<String> = MutableLiveData()


    fun getMovieDetail(movieId: Int) {
        isLoading.value = true

        viewModelScope.launch {
            val response = ApiClient.getClient().getMovieDetail(movieId.toString(), Constants.BEARER_TOKEN)
            try {
                if (response.isSuccessful) {
                    movieRespone.postValue(response.body())
                } else {
                    if (response.message().isNullOrEmpty()) {
                        errorMessage.value = "An unknown error occurred"
                    } else {
                        errorMessage.value = response.message()
                    }
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
            } finally {
                isLoading.value = false
            }
        }
    }

}