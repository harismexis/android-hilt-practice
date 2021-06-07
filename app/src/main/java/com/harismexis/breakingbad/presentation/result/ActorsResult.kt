package com.harismexis.breakingbad.presentation.result

import com.harismexis.breakingbad.datamodel.domain.Hero


sealed class ActorsResult {
    data class ActorsSuccess(val items: List<Hero>): ActorsResult()
    data class ActorsError(val error: Exception): ActorsResult()
}