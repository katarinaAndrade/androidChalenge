package com.katarina.core.network.converter

import com.squareup.moshi.Moshi

class MoshiConverterImpl(
    private val moshi: Moshi
) : MoshiConverter {

    override fun moshi(): Moshi = moshi

    override fun <T> fromJson(json: String?, type: Class<T>): T {
        val adapter = moshi.adapter(type)
        return json?.let {
            if (it.isNotEmpty()) {
                adapter.fromJson(it)
            } else {
                type.newInstance()
            }
        } ?: throw Exception()
    }

    override fun toJson(obj: Any?): String {
        return genericType(obj)
    }

    private inline fun <reified T> genericType(type: T): String {
        val adapter = moshi.adapter(T::class.java)
        return adapter.toJson(type)
    }
}
