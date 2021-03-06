package com.harismexis.hiltproject.presentation.screens.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.hiltproject.core.repository.HeroLocal
import com.harismexis.hiltproject.core.repository.HeroRemote
import com.harismexis.hiltproject.framework.util.event.Event
import com.harismexis.hiltproject.framework.util.extensions.getErrorMessage
import com.harismexis.hiltproject.core.result.HerosResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val heroRemote: HeroRemote,
    private val heroLocal: HeroLocal
) : ViewModel() {

    private val TAG = HomeViewModel::class.qualifiedName

    private val mHerosResult = MutableLiveData<HerosResult>()
    val herosResult: LiveData<HerosResult>
        get() = mHerosResult

    private val mShowErrorMessage = MutableLiveData<Event<String>>()
    val showErrorMessage : LiveData<Event<String>>
        get() = mShowErrorMessage

    private var searchQuery: String? = null

    fun fetchHeros() {
        fetchRemoteHeros(searchQuery)
    }

    fun updateSearchQuery(query: String?) {
        searchQuery = query
        fetchRemoteHeros(query)
    }

    private fun fetchRemoteHeros(name: String? = null) {
        viewModelScope.launch {
            try {
                val items = heroRemote.getHeros(name)
                mHerosResult.value = HerosResult.Success(items)
                heroLocal.updateHeros(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mHerosResult.value = HerosResult.Error(e)
                mShowErrorMessage.value = Event(e.getErrorMessage())
            }
        }
    }

}