package com.example.circleoffifth

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.circleoffifth.AppDatabase.Companion.VERSION
import com.example.circleoffifth.data.dao.GameDao
import com.example.circleoffifth.data.entities.Mode
import com.example.circleoffifth.data.entities.Mode.Companion.CHALLENGE
import com.example.circleoffifth.data.entities.Mode.Companion.SURVIVE
import com.example.circleoffifth.data.entities.Records
import com.example.circleoffifth.data.entities.ScoreState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.UUID

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

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "circleOfFifth")
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            onCreate(scope)
                        }
                    })
                    .build()
                this.instance = instance
                instance
            }
        }

        fun onCreate(scope: CoroutineScope) {
            instance?.let {
                scope.launch {
                    val trialMode = Mode(UUID.randomUUID().toString(), CHALLENGE)
                    val surviveMode = Mode(UUID.randomUUID().toString(), SURVIVE)
                    it.getGameDao().saveMode(trialMode)
                    it.getGameDao().saveMode(surviveMode)
                }
            }
        }
    }
}