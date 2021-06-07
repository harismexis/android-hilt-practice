package com.harismexis.breakingbad.presentation.screens.actordetail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.breakingbad.datamodel.repo.HeroLocalRepo
import com.harismexis.breakingbad.framework.extensions.getErrorMessage
import com.harismexis.breakingbad.presentation.result.ActorDetailResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorDetailViewModel @Inject constructor(
    private val heroLocal: HeroLocalRepo,
) : ViewModel() {

    private val tag = ActorDetailViewModel::class.qualifiedName

    private val mActorDetailResult = MutableLiveData<ActorDetailResult>()
    val actorDetailResult: LiveData<ActorDetailResult>
        get() = mActorDetailResult

    fun retrieveActorById(itemId: Int) {
        viewModelScope.launch {
            try {
                val item = heroLocal.getActor(itemId)
                item?.let {
                    mActorDetailResult.value = ActorDetailResult.ActorSuccess(item)
                }
            } catch (e: Exception) {
                Log.d(tag, e.getErrorMessage())
                mActorDetailResult.value = ActorDetailResult.ActorError(e.getErrorMessage())
            }
        }
    }

}
