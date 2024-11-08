package com.example.circleoffifth

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.circleoffifth.AppDatabase.Companion.VERSION
import com.example.circleoffifth.data.dao.GameDao
import com.example.circleoffifth.data.entities.Mode
import com.example.circleoffifth.data.entities.Records
import com.example.circleoffifth.data.entities.ScoreState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

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

        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabaseBuilder(context: Context): Builder<AppDatabase> {
            val appContext = context.applicationContext
            val dbFile = appContext.getDatabasePath("circleOfFifth.db")
            return Room.databaseBuilder<AppDatabase>(
                context = appContext,
                name = dbFile.absolutePath
            ).setDriver(BundledSQLiteDriver())
        }

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return instance ?: synchronized(this) {
                val instance = getDatabaseBuilder(context)
                    .setQueryCoroutineContext(Dispatchers.IO)
                    .build()
                    this.instance = instance
                    instance
            }
        }
    }
}