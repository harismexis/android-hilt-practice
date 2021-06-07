package com.harismexis.breakingbad.framework.datasource.network.data

import com.harismexis.breakingbad.framework.datasource.network.api.BreakingBadApi
import com.harismexis.breakingbad.framework.datasource.network.model.RemoteActor
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreakingBadRemoteDao @Inject constructor(private val api: BreakingBadApi) {

    suspend fun getActors(name: String? = null): List<RemoteActor?>? {
        return api.getCharactersByName(name)
    }

}