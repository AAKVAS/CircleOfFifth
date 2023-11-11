package com.example.circleoffifth.di

import com.example.circleoffifth.AppDatabase
import com.example.circleoffifth.CircleOfFifthApplication
import com.example.circleoffifth.data.ChordRepository
import com.example.circleoffifth.data.ChordRepositoryImpl
import com.example.circleoffifth.data.dao.GameDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [AppDataModule::class]
)
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindChordRepository(repository: ChordRepositoryImpl): ChordRepository
}

@Module
@InstallIn(SingletonComponent::class)
internal object AppDataModule {
    @Provides
    fun provideAppDatabase(): AppDatabase {
        return CircleOfFifthApplication.instance.database
    }

    @Provides
    fun provideGameDao(appDatabase: AppDatabase): GameDao {
        return appDatabase.getGameDao()
    }
}