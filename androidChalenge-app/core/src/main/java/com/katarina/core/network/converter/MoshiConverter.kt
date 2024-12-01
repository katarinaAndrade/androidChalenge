package com.katarina.core.network.converter

import com.squareup.moshi.Moshi

interface MoshiConverter {

    fun moshi(): Moshi

    fun <T> fromJson(json: String?, type: Class<T>): T

    fun toJson(obj: Any?): String
}
