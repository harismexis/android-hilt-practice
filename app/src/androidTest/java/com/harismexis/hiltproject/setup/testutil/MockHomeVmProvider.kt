package com.harismexis.hiltproject.setup.testutil

import androidx.lifecycle.MutableLiveData
import com.harismexis.hiltproject.presentation.result.HerosResult
import com.harismexis.hiltproject.presentation.screens.home.viewmodel.HomeViewModel
import io.mockk.mockk

object MockHomeVmProvider {

    val mockHomeViewModel: HomeViewModel = mockk(relaxed = true)
    var fakeHerosResult = MutableLiveData<HerosResult>()

}