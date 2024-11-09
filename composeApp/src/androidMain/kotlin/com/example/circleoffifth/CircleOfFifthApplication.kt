package com.example.circleoffifth

import android.app.Application
import com.example.circleoffifth.di.Koin
import com.example.circleoffifth.utils.AndroidChordSoundPlayer
import org.koin.android.ext.koin.androidContext

class CircleOfFifthApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidChordSoundPlayer.create(this)

        Koin.setupKoin {
            androidContext(applicationContext)
        }
    }
}