package com.example.circleoffifth.data


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.circleoffifth.data.AppDatabase.Companion.VERSION
import com.example.circleoffifth.data.dao.GameDao
import com.example.circleoffifth.data.entities.Mode
import com.example.circleoffifth.data.entities.Records
import com.example.circleoffifth.data.entities.ScoreState

@Database(
    entities = [
        Mode::class,
        Records::class,
        ScoreState::class
    ],
    version = VERSION
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getGameDao(): GameDao

    companion object {
        const val VERSION = 1
        const val DB_NAME = "circleOfFifth.db"
    }
}