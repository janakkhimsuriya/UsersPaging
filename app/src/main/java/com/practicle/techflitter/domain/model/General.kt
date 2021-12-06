package com.practicle.techflitter.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

data class Meta(
    var pagination: Pagination
)

@Entity(tableName = "Pagination")
data class Pagination(
    @PrimaryKey
    var total: Int,
    var pages: Int,
    var page: Int,
    var limit: Int,
    var links: Links,
)

data class Links(
    var previous: String,
    var current: String,
    var next: String
)

class LinkTypeConvertor {
    @TypeConverter
    fun fromStringToLinks(data: String): Links {
        return Gson().fromJson(data, Links::class.java)
    }

    @TypeConverter
    fun fromLinksToString(links: Links): String {
        return Gson().toJson(links)
    }
}