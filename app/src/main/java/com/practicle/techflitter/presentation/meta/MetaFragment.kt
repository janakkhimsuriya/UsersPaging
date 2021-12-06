package com.practicle.techflitter.presentation.meta

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.base.BaseFragment
import com.practicle.techflitter.BR
import com.practicle.techflitter.R
import com.practicle.techflitter.databinding.FragmentMetaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MetaFragment : BaseFragment<FragmentMetaBinding, MetaViewModel>(),
    MetaNavigator {
    override val viewModel: MetaViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_meta

    override fun getVariable(): Int = BR.viewModel

    override fun onInitialize(savedInstanceState: Bundle?) {
        viewModel.setNavigator(this)
    }

    override fun onTerminate() {}

    override fun onShow() {}

    override fun onHide() {}
}