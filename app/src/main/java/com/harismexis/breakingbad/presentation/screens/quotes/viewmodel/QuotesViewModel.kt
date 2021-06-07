package com.harismexis.breakingbad.presentation.screens.quotes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.breakingbad.datamodel.repo.QuoteLocalRepo
import com.harismexis.breakingbad.datamodel.repo.QuoteRemoteRepo
import com.harismexis.breakingbad.framework.event.Event
import com.harismexis.breakingbad.framework.extensions.getErrorMessage
import com.harismexis.breakingbad.framework.util.functional.Action1
import com.harismexis.breakingbad.framework.util.network.ConnectivityMonitorSimple
import com.harismexis.breakingbad.presentation.result.QuotesResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val quoteRemote: QuoteRemoteRepo,
    private val quoteLocal: QuoteLocalRepo,
    private val connectivity: ConnectivityMonitorSimple,
) : ViewModel() {

    private val TAG = QuotesViewModel::class.qualifiedName

    private val mQuotes = MutableLiveData<QuotesResult>()
    val quotes: LiveData<QuotesResult>
        get() = mQuotes

    private val mShowErrorMessage = MutableLiveData<Event<String>>()
    val showErrorMessage : LiveData<Event<String>>
        get() = mShowErrorMessage

    private var seriesName: String? = null

    fun fetchQuotes(seriesName: String?) {
        this.seriesName = seriesName
        if (connectivity.isOnline()) {
            fetchRemoteQuotes(seriesName)
        } else {
            fetchLocalQuotes(seriesName)
        }
    }

    fun refresh(callback: Action1<Boolean>) {
        val canRefresh = connectivity.isOnline()
        callback.call(canRefresh)
        if (canRefresh) {
            fetchRemoteQuotes(seriesName)
        }
    }

    private fun fetchRemoteQuotes(seriesName: String?) {
        viewModelScope.launch {
            try {
                val items = quoteRemote.getQuotes(seriesName)
                mQuotes.value = QuotesResult.QuotesSuccess(items)
                quoteLocal.insertQuotes(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mQuotes.value = QuotesResult.QuotesError(e)
                mShowErrorMessage.value = Event(e.getErrorMessage())
            }
        }
    }

    private fun fetchLocalQuotes(seriesName: String?) {
        viewModelScope.launch {
            try {
                val items = quoteLocal.getQuotesBySeries(seriesName)
                mQuotes.value = QuotesResult.QuotesSuccess(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mQuotes.value = QuotesResult.QuotesError(e)
                mShowErrorMessage.value = Event(e.getErrorMessage())
            }
        }
    }

}