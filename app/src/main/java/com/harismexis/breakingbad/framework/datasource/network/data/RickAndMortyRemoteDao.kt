package com.harismexis.breakingbad.framework.datasource.network.data

import com.harismexis.breakingbad.framework.datasource.network.api.RickAndMortyApi
import com.harismexis.breakingbad.framework.datasource.network.model.RemoteHeros
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RickAndMortyRemoteDao @Inject constructor(private val api: RickAndMortyApi) {

    suspend fun getActors(name: String? = null): RemoteHeros?? {
        return api.getCharactersByName(name)
    }

}