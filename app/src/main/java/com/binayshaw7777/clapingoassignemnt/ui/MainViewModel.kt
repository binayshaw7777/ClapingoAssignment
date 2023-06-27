package com.binayshaw7777.clapingoassignemnt.ui

import android.app.Application
import android.content.res.AssetManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binayshaw7777.clapingoassignemnt.model.ApiRequest
import com.binayshaw7777.clapingoassignemnt.network.RequestStatus

class MainViewModel(application: Application) : ViewModel() {

    private val mainRepository = MainRepository()

    private val _jsonParseResult = MutableLiveData<RequestStatus>()
    val jsonParseResult: LiveData<RequestStatus> = _jsonParseResult

    private val _successLiveData = MutableLiveData<ApiRequest>()
    val successLiveData: LiveData<ApiRequest> = _successLiveData

    private val _failureLiveData = MutableLiveData<String>()
    val failureLiveData: LiveData<String> = _failureLiveData

    fun performJsonParsing(assetManager: AssetManager) {
        when (val result = mainRepository.performJsonParsing(assetManager)) {
            is RequestStatus.Success -> {
                val data = result.data
                _successLiveData.value = data
            }
            is RequestStatus.Failure -> {
                val error = result.error
                _failureLiveData.value = error
            }
        }
    }
}