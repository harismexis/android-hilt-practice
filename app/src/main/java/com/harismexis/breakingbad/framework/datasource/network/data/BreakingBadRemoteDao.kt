package com.harismexis.breakingbad.framework.datasource.network.data

import com.harismexis.breakingbad.framework.datasource.network.api.BreakingBadApi
import com.harismexis.breakingbad.framework.datasource.network.model.RemoteCharacters
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreakingBadRemoteDao @Inject constructor(private val api: BreakingBadApi) {

    suspend fun getActors(name: String? = null): RemoteCharacters?? {
        return api.getCharactersByName(name)
    }

}