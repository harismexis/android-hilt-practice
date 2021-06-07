package com.harismexis.hiltproject.framework.datasource.network.data

import com.harismexis.hiltproject.framework.datasource.network.model.RemoteHeros
import com.harismexis.hiltproject.framework.datasource.network.api.RickAndMortyApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RickAndMortyRemoteDao @Inject constructor(private val api: RickAndMortyApi) {

    suspend fun getActors(name: String? = null): RemoteHeros?? {
        return api.getCharactersByName(name)
    }

}