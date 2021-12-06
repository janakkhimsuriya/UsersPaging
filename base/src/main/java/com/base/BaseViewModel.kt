package com.base

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import java.lang.ref.WeakReference

abstract class BaseViewModel<N : BaseNavigator> : ViewModel(), LifecycleObserver {
    private val title = ObservableField<String>()
    private val message = ObservableField<String>()

    private val isLoading = ObservableBoolean()
    private val isRefreshing = ObservableBoolean()
    private lateinit var navigator: WeakReference<N>

    private val parentJob = Job()
    val coroutineScope = CoroutineScope(parentJob + Dispatchers.Default)

    fun isLoading(): ObservableBoolean {
        return isLoading
    }

    fun setLoading(isLoading: Boolean) {
        this.isLoading.set(isLoading)
    }

    fun setTitle(title: String?) {
        this.title.set(title)
    }

    fun getTitle(): ObservableField<String> {
        return title
    }

    fun setMessage(title: String?) {
        this.message.set(title)
    }

    fun getMessage(): ObservableField<String> {
        return message
    }

    fun isRefreshing(): ObservableBoolean {
        return isRefreshing
    }

    fun setRefreshing(isLoading: Boolean) {
        this.isRefreshing.set(isLoading)
    }

    fun setNavigator(navigator: N) {
        this.navigator = WeakReference(navigator)
    }

    fun getNavigator(): N? {
        return navigator.get()
    }

    override fun onCleared() {
        parentJob.cancel()
        viewModelScope.cancel()
        super.onCleared()
    }
}