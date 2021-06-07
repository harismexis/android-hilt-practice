package com.harismexis.breakingbad.framework.datasource.network.api

import com.harismexis.breakingbad.framework.datasource.network.model.RemoteHeros
import retrofit2.http.GET
import retrofit2.http.Query

interface BreakingBadApi {

    @GET("character")
    suspend fun getCharactersByName(
        @Query("name") name: String? = null
    ): RemoteHeros?

}