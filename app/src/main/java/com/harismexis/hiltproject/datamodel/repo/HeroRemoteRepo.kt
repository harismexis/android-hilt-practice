package com.harismexis.hiltproject.datamodel.repo

import com.harismexis.hiltproject.datamodel.domain.Hero
import com.harismexis.hiltproject.framework.datasource.network.data.RickAndMortyRemoteDao
import com.harismexis.hiltproject.framework.extensions.hero.toItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class HeroRemoteRepo @Inject constructor(
    private val dao: RickAndMortyRemoteDao
) {
    suspend fun getActors(name: String? = null): List<Hero> = dao.getActors(name).toItems()

}