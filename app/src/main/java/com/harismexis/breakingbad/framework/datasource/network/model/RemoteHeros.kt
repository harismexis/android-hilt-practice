package com.harismexis.breakingbad.framework.datasource.network.model

data class RemoteHeros(
    val info: RemoteInfo,
    val results: List<RemoteHero>,
)
