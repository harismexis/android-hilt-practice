package com.harismexis.breakingbad.framework.datasource.network.model

data class RemoteCharacters(
    val info: RemoteInfo,
    val results: List<RemoteActor>,
)
