package com.example.circleoffifth.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase.Builder
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.circleoffifth.data.AppDatabase
import com.example.circleoffifth.data.AppDatabase.Companion.DB_NAME
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(appModule())
    }

object Koin {
    var di: KoinApplication? = null

    fun setupKoin(appDeclaration: KoinAppDeclaration = {}) {
        if (di == null) {
            di = initKoin(appDeclaration)
        }
    }
}

fun getDatabaseBuilder(context: Context): Builder<AppDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath(DB_NAME)
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
    .setDriver(BundledSQLiteDriver())
    .setQueryCoroutineContext(Dispatchers.IO)
}

actual fun platformModule(): Module = module {
    single {
        getDatabaseBuilder(get()).build()
    }
}
