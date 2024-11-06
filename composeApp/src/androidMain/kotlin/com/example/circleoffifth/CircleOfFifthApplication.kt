package com.example.circleoffifth

import android.app.Application
import com.example.circleoffifth.di.appModule
import com.example.circleoffifth.utils.ChordSoundManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class CircleOfFifthApplication : Application() {
    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this, CoroutineScope(SupervisorJob()))
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        ChordSoundManager.create(this)

        startKoin {
            androidLogger()
            androidContext(this@CircleOfFifthApplication)
            modules(appModule)
        }
    }

    companion object {
        lateinit var instance: CircleOfFifthApplication
    }
}