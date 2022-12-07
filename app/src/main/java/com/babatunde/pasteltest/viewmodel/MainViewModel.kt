package com.babatunde.pasteltest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babatunde.pasteltest.repository.NewsRepository
import com.babatunde.pasteltest.utils.ConnectivityObserver
import com.babatunde.pasteltest.utils.NetworkConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NewsRepository,
    networkObserver:NetworkConnectivityObserver
):ViewModel() {
    private val network = networkObserver.observe()
    val newsList = repository.getNews()

    init {
        viewModelScope.launch {
            network.collect{
                if (it == ConnectivityObserver.Status.Available) {
                    repository.loadNewsFromNetwork()
                }
            }
        }

    }

}