package com.practicle.techflitter.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Users")
data class User(
    @PrimaryKey var id: Long,
    var name: String,
    var email: String,
    var gender: String,
    var status: String,
    var isSync: Boolean = true
) : Parcelable {
    fun isMale(): Boolean {
        return gender.lowercase() == "male"
    }

    fun isFemale(): Boolean {
        return gender.lowercase() == "female"
    }

    fun isActive(): Boolean {
        return status.lowercase() == "active"
    }

    fun isInactive(): Boolean {
        return status.lowercase() == "inactive"
    }
}
