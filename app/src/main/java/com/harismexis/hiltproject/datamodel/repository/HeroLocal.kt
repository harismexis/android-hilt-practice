package com.harismexis.hiltproject.datamodel.repository

import com.harismexis.hiltproject.datamodel.domain.Hero

interface HeroLocal {
    suspend fun updateHeros(items: List<Hero>)

    suspend fun getHero(itemId: Int): Hero?

    suspend fun getHeros(): List<Hero>

}