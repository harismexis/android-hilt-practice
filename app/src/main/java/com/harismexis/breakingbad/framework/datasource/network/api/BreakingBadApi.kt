package com.harismexis.breakingbad.framework.datasource.network.api

import com.harismexis.breakingbad.framework.datasource.network.model.RemoteActor
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface BreakingBadApi {

    @GET("characters")
    suspend fun getCharactersByName(
        @Query("name") name: String? = null
    ): List<RemoteActor?>?

}