package com.katarina.main.domain

import java.io.File

object TestUtils {
    fun loadStringResource(resName: String): String {
        val classLoader = javaClass.classLoader!!
        val file = File(classLoader.getResource(resName).file)
        val contents: String = file.readText(charset = Charsets.UTF_8)
        return contents.trimIndent()
    }
}