package com.example.circleoffifth

import android.app.Application
import com.example.circleoffifth.utils.AndroidChordSoundPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CircleOfFifthApplication : Application() {
    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this, CoroutineScope(SupervisorJob()))
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AndroidChordSoundPlayer.create(this)
    }

    companion object {
        lateinit var instance: CircleOfFifthApplication
    }
}