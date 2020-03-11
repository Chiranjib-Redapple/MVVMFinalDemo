package com.example.mvvmfinaldemo.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0

@Entity(tableName = "User")
data class User(
    var id: Int? = null,
    var name: String? = null,
    var email: String? = null,
    var password: String? = null,
    var email_verified: String? = null,
    var created_at: String? = null,
    var updated_at: String? = null
) {
    @PrimaryKey(autoGenerate = false)
    var uId: Int = CURRENT_USER_ID
}
