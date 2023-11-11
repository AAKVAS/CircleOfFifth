package com.example.circleoffifth

import android.app.Application
import com.example.circleoffifth.utils.ChordSoundManager
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class CircleOfFifthApplication : Application() {
    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this, CoroutineScope(SupervisorJob()))
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        ChordSoundManager.create(this)
    }

    companion object {
        lateinit var instance: CircleOfFifthApplication
    }
}