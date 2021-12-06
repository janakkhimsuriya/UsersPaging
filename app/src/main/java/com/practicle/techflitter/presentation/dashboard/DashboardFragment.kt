package com.practicle.techflitter.presentation.dashboard

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.base.BaseFragment
import com.practicle.techflitter.BR
import com.practicle.techflitter.R
import com.practicle.techflitter.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>(),
    DashboardNavigator {
    override val viewModel: DashboardViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_dashboard

    override fun getVariable(): Int = BR.viewModel

    override fun onInitialize(savedInstanceState: Bundle?) {
        viewModel.setNavigator(this)
        viewModel.initialize()
    }

    override fun onTerminate() {}

    override fun onShow() {}

    override fun onHide() {}
}