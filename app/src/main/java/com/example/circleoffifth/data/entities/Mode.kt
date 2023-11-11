package com.example.circleoffifth.data.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mode(
    @PrimaryKey(autoGenerate = false)
    val uid: String,
    val name: String
) {
    companion object {
        const val TRIAL = "trial"
        const val SURVIVE = "survive"
    }
}
