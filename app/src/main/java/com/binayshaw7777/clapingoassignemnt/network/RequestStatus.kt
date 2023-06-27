package com.binayshaw7777.clapingoassignemnt.network

import com.binayshaw7777.clapingoassignemnt.model.ApiRequest

sealed class RequestStatus {
    data class Success(val data: ApiRequest) : RequestStatus()
    data class Failure(val error: String) : RequestStatus()
}