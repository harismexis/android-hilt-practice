package com.harismexis.hiltproject.presentation.result

import com.harismexis.hiltproject.datamodel.domain.Hero

sealed class HerosResult {
    data class Success(val items: List<Hero>): HerosResult()
    data class Error(val error: Exception): HerosResult()
}