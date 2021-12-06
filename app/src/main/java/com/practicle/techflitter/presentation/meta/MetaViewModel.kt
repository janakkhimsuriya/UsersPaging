package com.practicle.techflitter.presentation.meta

import androidx.databinding.ObservableField
import com.base.BaseNavigator
import com.base.BaseViewModel
import com.practicle.techflitter.domain.model.Pagination
import com.practicle.techflitter.domain.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MetaViewModel @Inject constructor(private val repository: UsersRepository) :
    BaseViewModel<MetaNavigator>() {

    val meta = ObservableField<Pagination>()

    init {
        repository.getPagination().observeForever {
            if (it.isNotEmpty()) {
                meta.set(it[0])
            }
        }
    }
}

interface MetaNavigator : BaseNavigator