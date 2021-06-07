package com.harismexis.hiltproject.presentation.result

import com.harismexis.hiltproject.datamodel.domain.Hero

sealed class HeroDetailResult {
    data class Success(val item: Hero): HeroDetailResult()
    data class Error(val error: String): HeroDetailResult()
}