package com.example.circleoffifth.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mode(
    @PrimaryKey(autoGenerate = false)
    val uid: String,
    val name: String
) {
    companion object {
        const val CHALLENGE = "сhallenge"
        const val SURVIVE = "survive"
    }
}
