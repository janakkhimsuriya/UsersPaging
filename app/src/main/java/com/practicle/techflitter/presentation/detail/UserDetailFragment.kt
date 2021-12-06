package com.practicle.techflitter.presentation.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.base.BaseFragment
import com.practicle.techflitter.BR
import com.practicle.techflitter.R
import com.practicle.techflitter.databinding.FragmentUserDetailBinding
import com.practicle.techflitter.domain.model.User
import com.practicle.techflitter.utils.AppConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : BaseFragment<FragmentUserDetailBinding, UserDetailViewModel>(),
    UserDetailNavigator {
    override val viewModel: UserDetailViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_user_detail

    override fun getVariable(): Int = BR.viewModel

    override fun onInitialize(savedInstanceState: Bundle?) {
        viewModel.setNavigator(this)

        binding?.toolBar?.setNavigationIcon(R.drawable.ic_arrow_back)
        binding?.toolBar?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onTerminate() {}

    override fun onShow() {}

    override fun onHide() {}

    override fun onUserUpdated(user: User) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set(
            AppConstants.BUNDLE_EXTRA_PARAMS, user
        )
        findNavController().popBackStack()
    }
}