package com.harismexis.hiltproject.framework.di.application

import com.harismexis.hiltproject.core.repository.HeroLocal
import com.harismexis.hiltproject.core.repository.HeroRemote
import com.harismexis.hiltproject.framework.data.database.repository.HeroLocalRepository
import com.harismexis.hiltproject.framework.data.network.repository.HeroRemoteRepository
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