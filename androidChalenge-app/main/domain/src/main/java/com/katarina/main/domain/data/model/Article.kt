package com.katarina.main.domain.data.model

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat

data class Article(
    val source: Source? = null,
    val author: String? = "",
    val title: String? = "",
    val description: String? = "",
    val url: String? = "",
    val urlToImage: String? = "",
    val publishedAt: String? = "",
    val content: String? = "",
) {
    @SuppressLint("SimpleDateFormat")
    fun formatDate(): String {
        var result = ""

        publishedAt?.let { publishedAt ->
            var fixedDate = publishedAt.replace("Z", "")
            fixedDate = fixedDate.replace("T", " ")

            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            try {
                val date = dateFormat.parse(fixedDate)
                val dateToString = SimpleDateFormat("dd MMMM yyyy - HH:mm")
                result = dateToString.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }

        return result
    }
}