package com.example.circleoffifth.di

import com.example.circleoffifth.data.AppDatabase
import com.example.circleoffifth.data.ChordRepository
import com.example.circleoffifth.data.ChordRepositoryImpl
import com.example.circleoffifth.data.dao.GameDao
import com.example.circleoffifth.ui.viewModel.ChallengeViewModel
import com.example.circleoffifth.ui.viewModel.SurviveViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val commonModule = module {
    single<GameDao> { get<AppDatabase>().getGameDao() }
    single<ChordRepository> { ChordRepositoryImpl(get()) }

    viewModel { ChallengeViewModel(get()) }
    viewModel { SurviveViewModel(get()) }
}