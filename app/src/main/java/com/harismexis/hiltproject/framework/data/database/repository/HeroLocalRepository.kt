package com.harismexis.hiltproject.framework.data.database.repository

import com.harismexis.hiltproject.core.domain.Hero
import com.harismexis.hiltproject.core.repository.HeroLocal
import com.harismexis.hiltproject.framework.data.database.dao.RickAndMortyLocalDao
import com.harismexis.hiltproject.framework.util.extensions.hero.toItem
import com.harismexis.hiltproject.framework.util.extensions.hero.toItems
import com.harismexis.hiltproject.framework.util.extensions.hero.toLocalItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeroLocalRepository @Inject constructor(
    private val dao: RickAndMortyLocalDao
): HeroLocal {
    override suspend fun updateHeros(items: List<Hero>) {
        dao.deleteAllHeros()
        dao.insertHeros(items.toLocalItems())
    }

    override suspend fun getHero(itemId: Int): Hero? {
        val localItem = dao.getHeroById(itemId)
        localItem?.let {
            return it.toItem()
        }
        return null
    }

    override suspend fun getHeros(): List<Hero> {
        return dao.getAllHeros().toItems()
    }

}