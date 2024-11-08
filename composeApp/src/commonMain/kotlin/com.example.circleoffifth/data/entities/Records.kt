package com.example.circleoffifth.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Records(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "mode_uid")
    val modeUid: String,
    val record: Int
)
