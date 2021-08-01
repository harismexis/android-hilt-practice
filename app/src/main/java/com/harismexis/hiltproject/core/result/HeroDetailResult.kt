package com.harismexis.hiltproject.core.result

import com.harismexis.hiltproject.core.domain.Hero

sealed class HeroDetailResult {
    data class Success(val item: Hero): HeroDetailResult()
    data class Error(val error: String): HeroDetailResult()
}