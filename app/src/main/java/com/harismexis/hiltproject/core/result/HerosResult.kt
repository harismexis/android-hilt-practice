package com.harismexis.hiltproject.core.result

import com.harismexis.hiltproject.core.domain.Hero

sealed class HerosResult {
    data class Success(val items: List<Hero>): HerosResult()
    data class Error(val error: Exception): HerosResult()
}