package com.harismexis.breakingbad.presentation.result

import com.harismexis.breakingbad.datamodel.domain.Hero

sealed class HeroDetailResult {
    data class Success(val item: Hero): HeroDetailResult()
    data class Error(val error: String): HeroDetailResult()
}