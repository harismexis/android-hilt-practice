package com.harismexis.hiltproject.framework.data.network.repository

import com.harismexis.hiltproject.core.domain.Hero
import com.harismexis.hiltproject.core.repository.HeroRemote
import com.harismexis.hiltproject.framework.data.network.data.RickAndMortyRemoteDao
import com.harismexis.hiltproject.framework.util.extensions.hero.toItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class HeroRemoteRepository @Inject constructor(
    private val dao: RickAndMortyRemoteDao
): HeroRemote {
    override suspend fun getHeros(name: String?): List<Hero> = dao.getHeros(name).toItems()

}