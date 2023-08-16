package com.juanparedes.pruebameli.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.juanparedes.pruebameli.data.local.entity.ResultProductEntity
import com.juanparedes.pruebameli.domain.model.ResultProduct

data class ResultProductApiDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("price")
    val price: Double
)

fun ResultProductApiDto.mapToDomain() = ResultProduct(
    id, title, thumbnail, price
)

fun List<ResultProductApiDto>.mapToDomain() = this.map { it.mapToDomain() }

fun ResultProductApiDto.mapToLocalEntity() = ResultProductEntity().also {
    it.id = id
    it.title = title
    it.thumbnail = thumbnail
    it.price = price
}

fun List<ResultProductApiDto>.mapToLocalEntity() = this.map { it.mapToLocalEntity() }