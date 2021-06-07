package com.harismexis.hiltproject.presentation.screens.herodetail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.hiltproject.datamodel.repo.HeroLocalRepo
import com.harismexis.hiltproject.framework.extensions.getErrorMessage
import com.harismexis.hiltproject.presentation.result.HeroDetailResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModel @Inject constructor(
    private val heroLocal: HeroLocalRepo,
) : ViewModel() {

    private val tag = HeroDetailViewModel::class.qualifiedName

    private val mActorDetailResult = MutableLiveData<HeroDetailResult>()
    val heroDetailResult: LiveData<HeroDetailResult>
        get() = mActorDetailResult

    fun getHeroById(itemId: Int) {
        viewModelScope.launch {
            try {
                val item = heroLocal.getHero(itemId)
                item?.let {
                    mActorDetailResult.value = HeroDetailResult.Success(item)
                }
            } catch (e: Exception) {
                Log.d(tag, e.getErrorMessage())
                mActorDetailResult.value = HeroDetailResult.Error(e.getErrorMessage())
            }
        }
    }

}
