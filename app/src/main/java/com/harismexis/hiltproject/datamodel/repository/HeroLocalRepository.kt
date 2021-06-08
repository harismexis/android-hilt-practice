package com.harismexis.hiltproject.datamodel.repository

import com.harismexis.hiltproject.datamodel.domain.Hero
import com.harismexis.hiltproject.framework.datasource.database.data.RickAndMortyLocalDao
import com.harismexis.hiltproject.framework.extensions.hero.toItem
import com.harismexis.hiltproject.framework.extensions.hero.toItems
import com.harismexis.hiltproject.framework.extensions.hero.toLocalItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeroLocalRepository @Inject constructor(
    private val dao: RickAndMortyLocalDao
) {
    suspend fun updateHeros(items: List<Hero>) {
        dao.deleteAllHeros()
        dao.insertHeros(items.toLocalItems())
    }

    suspend fun getHero(itemId: Int): Hero? {
        val localItem = dao.getHeroById(itemId)
        localItem?.let {
            return it.toItem()
        }
        return null
    }

    suspend fun getHeros(): List<Hero> {
        return dao.getAllHeros().toItems()
    }

}