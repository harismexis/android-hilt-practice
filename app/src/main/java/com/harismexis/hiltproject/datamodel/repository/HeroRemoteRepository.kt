package com.harismexis.hiltproject.datamodel.repository

import com.harismexis.hiltproject.datamodel.domain.Hero
import com.harismexis.hiltproject.framework.datasource.network.data.RickAndMortyRemoteDao
import com.harismexis.hiltproject.framework.extensions.hero.toItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class HeroRemoteRepository @Inject constructor(
    private val dao: RickAndMortyRemoteDao
) {
    suspend fun getHeros(name: String? = null): List<Hero> = dao.getHeros(name).toItems()

}