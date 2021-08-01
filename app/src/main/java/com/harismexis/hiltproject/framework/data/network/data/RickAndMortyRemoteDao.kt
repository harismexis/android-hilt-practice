package com.harismexis.hiltproject.framework.data.network.data

import com.harismexis.hiltproject.framework.data.network.model.RemoteHeros
import com.harismexis.hiltproject.framework.data.network.api.RickAndMortyApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RickAndMortyRemoteDao @Inject constructor(private val api: RickAndMortyApi) {

    suspend fun getHeros(name: String? = null): RemoteHeros?? {
        return api.getHeros(name)
    }

}