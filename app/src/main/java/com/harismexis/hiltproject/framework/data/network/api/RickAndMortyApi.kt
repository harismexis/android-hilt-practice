package com.harismexis.hiltproject.framework.data.network.api

import com.harismexis.hiltproject.framework.data.network.model.RemoteHeros
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getHeros(
        @Query("name") name: String? = null
    ): RemoteHeros?

}