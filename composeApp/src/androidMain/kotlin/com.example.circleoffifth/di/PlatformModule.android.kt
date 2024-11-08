package com.example.circleoffifth.di

import com.example.circleoffifth.AppDatabase
import com.example.circleoffifth.CircleOfFifthApplication
import com.example.circleoffifth.data.dao.GameDao
import org.koin.core.module.Module
import org.koin.dsl.module


actual fun platformModule(): Module = module {
    single {
        CircleOfFifthApplication.instance.database
    }

    single<GameDao> { get<AppDatabase>().getGameDao() }
}
