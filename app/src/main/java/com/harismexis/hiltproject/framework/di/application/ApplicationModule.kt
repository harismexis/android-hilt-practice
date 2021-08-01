package com.harismexis.hiltproject.framework.di.application

import android.content.Context
import com.harismexis.hiltproject.framework.data.database.RickAndMortyDatabase
import com.harismexis.hiltproject.framework.data.database.dao.RickAndMortyLocalDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideLocalDao(@ApplicationContext app: Context): RickAndMortyLocalDao {
        return RickAndMortyDatabase.getDatabase(app).getDao()
    }

}