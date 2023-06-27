package com.binayshaw7777.clapingoassignemnt.ui

import android.content.res.AssetManager
import com.binayshaw7777.clapingoassignemnt.network.RequestStatus
import com.binayshaw7777.clapingoassignemnt.utils.parseJsonFile


class MainRepository {

    fun performJsonParsing(assetManager: AssetManager) : RequestStatus{
        return parseJsonFile(assetManager)
    }

}