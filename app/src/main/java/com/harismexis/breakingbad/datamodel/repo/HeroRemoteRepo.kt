package com.harismexis.breakingbad.datamodel.repo

import com.harismexis.breakingbad.datamodel.domain.Hero
import com.harismexis.breakingbad.framework.datasource.network.data.RickAndMortyRemoteDao
import com.harismexis.breakingbad.framework.extensions.actor.toItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class HeroRemoteRepo @Inject constructor(
    private val dao: RickAndMortyRemoteDao
) {
    suspend fun getActors(name: String? = null): List<Hero> = dao.getActors(name).toItems()

}