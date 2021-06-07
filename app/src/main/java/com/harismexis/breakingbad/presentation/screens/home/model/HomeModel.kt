package com.harismexis.breakingbad.presentation.screens.home.model

import com.harismexis.breakingbad.datamodel.domain.Hero
import com.harismexis.breakingbad.datamodel.repo.HeroLocalRepo
import com.harismexis.breakingbad.datamodel.repo.HeroRemoteRepo
import javax.inject.Inject

data class HomeModel @Inject constructor (
    private val heroRemote: HeroRemoteRepo,
    private val heroLocal: HeroLocalRepo
) {
    suspend fun getRemoteActors(name: String? = null): List<Hero> = heroRemote.getActors(name)

    suspend fun updateActors(items: List<Hero>) {
        heroLocal.updateActors(items)
    }

    suspend fun getLocalActor(itemId: Int): Hero? {
        return heroLocal.getActor(itemId)
    }

    suspend fun getLocalActors(): List<Hero> {
        return heroLocal.getActors()
    }

}