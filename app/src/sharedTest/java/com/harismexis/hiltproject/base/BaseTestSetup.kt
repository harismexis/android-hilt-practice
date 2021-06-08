package com.harismexis.hiltproject.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

abstract class BaseTestSetup {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

}