package com.harismexis.breakingbad.presentation.result

import com.harismexis.breakingbad.datamodel.domain.Hero


sealed class ActorDetailResult {
    data class ActorSuccess(val item: Hero): ActorDetailResult()
    data class ActorError(val error: String): ActorDetailResult()
}