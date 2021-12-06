package com.practicle.techflitter.presentation.users

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.base.BaseFragment
import com.practicle.techflitter.BR
import com.practicle.techflitter.R
import com.practicle.techflitter.databinding.FragmentUsersBinding
import com.practicle.techflitter.domain.model.User
import com.practicle.techflitter.utils.AppConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding, UsersViewModel>(),
    UsersNavigator {
    override val viewModel: UsersViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_users

    override fun getVariable(): Int = BR.viewModel

    override fun onInitialize(savedInstanceState: Bundle?) {
        viewModel.setNavigator(this)
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<User>(AppConstants.BUNDLE_EXTRA_PARAMS)
            ?.observe(this) {
                viewModel.onUserUpdated(it)
            }
    }

    override fun onTerminate() {}

    override fun onShow() {}

    override fun onHide() {}

    override fun onUserSelected() {
        findNavController().navigate(R.id.action_home_to_user_detail)
    }
}