package com.practicle.techflitter.presentation.users

import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.cachedIn
import com.base.BaseNavigator
import com.base.BaseViewModel
import com.base.listeners.ClickListener
import com.google.gson.Gson
import com.practicle.techflitter.R
import com.practicle.techflitter.domain.model.User
import com.practicle.techflitter.domain.repository.UsersRepository
import com.practicle.techflitter.utils.onError
import com.practicle.techflitter.utils.onLoading
import com.practicle.techflitter.utils.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val repository: UsersRepository) :
    BaseViewModel<UsersNavigator>(), ClickListener {

    val adapter = ObservableField(UsersAdapter(this))
    private val filter = ObservableField<String>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.syncUsers().collect { result ->
                result.onLoading {
                    setLoading(true)
                }
                result.onSuccess {
                    setLoading(false)
                    getUsers()
                }
                result.onError {
                    setLoading(false)
                }
            }
        }
    }

    private fun getUsers() {
        adapter.get()?.let { adapter ->
            adapter.addLoadStateListener { loadState ->
                if (adapter.snapshot().isEmpty()) {
                    setLoading(loadState.refresh is LoadState.Loading)
                } else {
                    setLoading(false)
                    setRefreshing(loadState.refresh is LoadState.Loading)
                }
            }
            viewModelScope.launch(Dispatchers.IO) {
                adapter.loadStateFlow.collect {
                    Log.d("LOAD_STATE", "getUsers: ")
                }
            }
            viewModelScope.launch(Dispatchers.IO) {
                repository.getUsers().cachedIn(viewModelScope).collect {
                    adapter.submitData(it)
                }
            }
        }
    }

    override fun onClick(view: View?, item: Any?) {
        item?.let {

        }
    }

    fun onClickUserInsert() {
        getNavigator()?.onUserSelected()
    }

    fun onClickFilter() {
        getNavigator()?.getActivityB()?.let { context ->
            val items = arrayOf(
                context.getString(R.string.name),
                context.getString(R.string.gender),
                context.getString(R.string.status),
            )
            AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.sort))
                .setSingleChoiceItems(items, -1) { dialog, which ->
                    filter.set(items[which])
                    dialog.dismiss()
                }.create().show()
        }
    }

    fun onUserUpdated(user: User?) {
        Log.d("USER_LIST", "onUserUpdated: " + Gson().toJson(user))
    }
}

interface UsersNavigator : BaseNavigator {
    fun onUserSelected()
}