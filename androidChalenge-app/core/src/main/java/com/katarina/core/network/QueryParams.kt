package com.katarina.core.network

import kotlin.reflect.KProperty

class QueryParams {

    private val _hash = HashMap<String, String>()

    val hash: Map<String, String> get() = _hash

    fun add(key: String, value: String?) {
        value?.let { _hash[key] = it }
    }

    fun add(key: String, value: Int?) {
        value?.let { _hash[key] = it.toString() }
    }

    fun param(paramName: String? = null): Param = Param(paramName)

    inner class Param(private val paramName: String? = null) {

        operator fun getValue(thisRef: Any?, property: KProperty<*>) = _hash[paramName ?: property.name]

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String?) {
            value?.let {
                _hash[paramName ?: property.name] = it
            }
        }
    }
}
