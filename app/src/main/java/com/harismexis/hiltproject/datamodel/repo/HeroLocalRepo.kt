package com.harismexis.hiltproject.datamodel.repo

import com.harismexis.hiltproject.framework.datasource.database.data.RickAndMortyLocalDao
import com.harismexis.hiltproject.framework.extensions.actor.toItem
import com.harismexis.hiltproject.framework.extensions.actor.toItems
import com.harismexis.hiltproject.framework.extensions.actor.toLocalItems
import com.harismexis.hiltproject.datamodel.domain.Hero
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeroLocalRepo @Inject constructor(
    private val dao: RickAndMortyLocalDao
) {
    suspend fun updateActors(items: List<Hero>) {
        dao.deleteAllHeros()
        dao.insertHeros(items.toLocalItems())
    }

    suspend fun getActor(itemId: Int): Hero? {
        val localItem = dao.getHeroById(itemId)
        localItem?.let {
            return it.toItem()
        }
        return null
    }

    suspend fun getActors(): List<Hero> {
        return dao.getAllHeros().toItems()
    }

}