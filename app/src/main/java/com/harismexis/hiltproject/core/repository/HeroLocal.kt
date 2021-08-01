package com.harismexis.hiltproject.core.repository

import com.harismexis.hiltproject.core.domain.Hero

interface HeroLocal {

    suspend fun updateHeros(items: List<Hero>)

    suspend fun getHero(itemId: Int): Hero?

    suspend fun getHeros(): List<Hero>

}