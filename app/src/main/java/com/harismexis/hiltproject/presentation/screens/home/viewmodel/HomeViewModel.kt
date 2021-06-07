package com.harismexis.hiltproject.presentation.screens.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.hiltproject.datamodel.repo.HeroLocalRepo
import com.harismexis.hiltproject.datamodel.repo.HeroRemoteRepo
import com.harismexis.hiltproject.framework.event.Event
import com.harismexis.hiltproject.framework.extensions.getErrorMessage
import com.harismexis.hiltproject.framework.util.functional.Action1
import com.harismexis.hiltproject.framework.util.network.ConnectivityMonitorSimple
import com.harismexis.hiltproject.presentation.result.HerosResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val heroRemote: HeroRemoteRepo,
    private val heroLocal: HeroLocalRepo,
    private val connectivity: ConnectivityMonitorSimple,
) : ViewModel() {

    private val TAG = HomeViewModel::class.qualifiedName

    private val mActorsResult = MutableLiveData<HerosResult>()
    val herosResult: LiveData<HerosResult>
        get() = mActorsResult

    private val mShowErrorMessage = MutableLiveData<Event<String>>()
    val showErrorMessage : LiveData<Event<String>>
        get() = mShowErrorMessage

    private var searchQuery: String? = null

    fun fetchInitialHeros() {
        if (connectivity.isOnline()) {
            fetchRemoteHeros(searchQuery)
        } else {

        }
    }

    fun updateSearchQuery(query: String?) {
        searchQuery = query
        fetchRemoteHeros(query)
    }

    fun refresh(callback: Action1<Boolean>) {
        val canRefresh = connectivity.isOnline()
        callback.call(canRefresh)
        if (canRefresh) fetchRemoteHeros(searchQuery)
    }

    private fun fetchRemoteHeros(name: String? = null) {
        viewModelScope.launch {
            try {
                val items = heroRemote.getActors(name)
                mActorsResult.value = HerosResult.Success(items)
                // heroLocal.updateHeros(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mActorsResult.value = HerosResult.Error(e)
                mShowErrorMessage.value = Event(e.getErrorMessage())
            }
        }
    }

}