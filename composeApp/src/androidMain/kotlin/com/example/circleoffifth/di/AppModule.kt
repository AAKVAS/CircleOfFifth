package com.example.circleoffifth.di

import com.example.circleoffifth.AppDatabase
import com.example.circleoffifth.CircleOfFifthApplication
import com.example.circleoffifth.data.ChordRepository
import com.example.circleoffifth.data.ChordRepositoryImpl
import com.example.circleoffifth.data.dao.GameDao
import com.example.circleoffifth.ui.viewModel.ChallengeViewModel
import com.example.circleoffifth.ui.viewModel.MainViewModel
import com.example.circleoffifth.ui.viewModel.SurviveViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {
    single {
        CircleOfFifthApplication.instance.database
    }

    single<GameDao> { get<AppDatabase>().getGameDao() }

    single<ChordRepository> { ChordRepositoryImpl(get()) }

    viewModelOf(::MainViewModel)
    viewModel { ChallengeViewModel(get()) }
    viewModel { SurviveViewModel(get()) }
}