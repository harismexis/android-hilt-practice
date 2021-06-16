package com.harismexis.hiltproject.framework.di.application

import com.harismexis.hiltproject.datamodel.repository.HeroLocal
import com.harismexis.hiltproject.datamodel.repository.HeroRemote
import com.harismexis.hiltproject.framework.datasource.database.repository.HeroLocalRepository
import com.harismexis.hiltproject.framework.datasource.network.repository.HeroRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindHeroLocal(
        localRepo: HeroLocalRepository
    ): HeroLocal

    @Binds
    abstract fun bindHeroRemote(
        remoteRepo: HeroRemoteRepository
    ): HeroRemote

}