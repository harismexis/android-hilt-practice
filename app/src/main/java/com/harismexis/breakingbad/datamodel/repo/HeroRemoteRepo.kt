package com.harismexis.breakingbad.datamodel.repo

import com.harismexis.breakingbad.datamodel.domain.Hero
import com.harismexis.breakingbad.framework.datasource.network.data.BreakingBadRemoteDao
import com.harismexis.breakingbad.framework.extensions.actor.toItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class HeroRemoteRepo @Inject constructor(
    private val dao: BreakingBadRemoteDao
) {
    suspend fun getActors(name: String? = null): List<Hero> = dao.getActors(name).toItems()

}