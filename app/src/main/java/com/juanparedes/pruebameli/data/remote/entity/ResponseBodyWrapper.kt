package com.juanparedes.pruebameli.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.juanparedes.pruebameli.data.remote.entity.ResultProductApiDto

class ResponseBodyWrapper {
    @SerializedName("results")
    val results: List<ResultProductApiDto> = listOf()
}