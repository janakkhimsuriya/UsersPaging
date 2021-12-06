package com.practicle.techflitter.presentation.dashboard

import androidx.databinding.ObservableField
import androidx.fragment.app.FragmentActivity
import com.base.BaseNavigator
import com.base.BaseViewModel
import com.practicle.techflitter.presentation.meta.MetaFragment
import com.practicle.techflitter.presentation.users.UsersFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor() :
    BaseViewModel<DashboardNavigator>() {

    val adapter = ObservableField<PagerAdapter>()

    fun initialize() {
        getNavigator()?.getActivityB()?.let {
            adapter.set(
                PagerAdapter(
                    it as FragmentActivity,
                    arrayListOf(UsersFragment(), MetaFragment())
                )
            )
        }
    }

}

interface DashboardNavigator : BaseNavigator