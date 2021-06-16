package com.harismexis.hiltproject.framework.datasource.network.repository

import com.harismexis.hiltproject.datamodel.domain.Hero
import com.harismexis.hiltproject.datamodel.repository.HeroRemote
import com.harismexis.hiltproject.framework.datasource.network.data.RickAndMortyRemoteDao
import com.harismexis.hiltproject.framework.extensions.hero.toItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class HeroRemoteRepository @Inject constructor(
    private val dao: RickAndMortyRemoteDao
): HeroRemote {
    override suspend fun getHeros(name: String?): List<Hero> = dao.getHeros(name).toItems()

}