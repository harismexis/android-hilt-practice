package com.harismexis.breakingbad.presentation.result

import com.harismexis.breakingbad.datamodel.domain.Hero

sealed class HerosResult {
    data class Success(val items: List<Hero>): HerosResult()
    data class Error(val error: Exception): HerosResult()
}