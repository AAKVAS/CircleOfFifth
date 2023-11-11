package com.example.circleoffifth.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_state")
data class ScoreState(
    @PrimaryKey(autoGenerate = false)
    val uid: String,
    @ColumnInfo(name = "mode_uid")
    val modeUid: String,
    val score: Int,
    val move: Int
)
