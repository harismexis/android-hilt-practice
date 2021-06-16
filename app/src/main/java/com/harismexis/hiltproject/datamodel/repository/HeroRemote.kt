package com.harismexis.hiltproject.datamodel.repository

import com.harismexis.hiltproject.datamodel.domain.Hero

interface HeroRemote {
    suspend fun getHeros(name: String? = null): List<Hero>

}