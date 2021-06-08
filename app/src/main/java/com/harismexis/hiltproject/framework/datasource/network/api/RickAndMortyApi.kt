package com.harismexis.hiltproject.framework.datasource.network.api

import com.harismexis.hiltproject.framework.datasource.network.model.RemoteHeros
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getHeros(
        @Query("name") name: String? = null
    ): RemoteHeros?

}