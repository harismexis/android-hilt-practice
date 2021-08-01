package com.harismexis.hiltproject.core.repository

import com.harismexis.hiltproject.core.domain.Hero

interface HeroRemote {

    suspend fun getHeros(name: String? = null): List<Hero>
}