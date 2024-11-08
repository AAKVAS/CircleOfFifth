package com.example.circleoffifth.di

import com.example.circleoffifth.data.ChordRepository
import com.example.circleoffifth.data.ChordRepositoryImpl
import com.example.circleoffifth.ui.viewModel.ChallengeViewModel
import com.example.circleoffifth.ui.viewModel.MainViewModel
import com.example.circleoffifth.ui.viewModel.SurviveViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val commonModule = module {
    single<ChordRepository> { ChordRepositoryImpl(get()) }


    viewModel { MainViewModel(get()) }
    viewModel { ChallengeViewModel(get()) }
    viewModel { SurviveViewModel(get()) }
}