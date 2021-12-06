package com.practicle.techflitter.presentation.detail

import android.widget.RadioGroup
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.base.BaseNavigator
import com.base.BaseViewModel
import com.base.utils.isValidEmail
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
class UserDetailViewModel @Inject constructor(private val repository: UsersRepository) :
    BaseViewModel<UserDetailNavigator>() {

    val user = ObservableField(User(System.currentTimeMillis(), "", "", "", ""))

    fun onSubmit() {
        getValidUser()?.let {
            viewModelScope.launch(Dispatchers.Main) {
                repository.createUser(it).collect { result ->
                    result.onLoading {
                        getNavigator()?.showLoadingDialog()
                    }
                    result.onSuccess { user ->
                        getNavigator()?.hideLoadingDialog()
                        getNavigator()?.onUserUpdated(user)
                    }
                    result.onError { exception ->
                        getNavigator()?.hideLoadingDialog()
                        getNavigator()?.getActivityB()?.let {
                            Toast.makeText(it, exception.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun getValidUser(): User? {
        return user.get()?.let { user ->
            when {
                user.name.isEmpty() -> {
                    getNavigator()?.getActivityB()?.let {
                        Toast.makeText(
                            it,
                            it.getString(R.string.error_message_name),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    return null
                }
                user.email.isEmpty() -> {
                    getNavigator()?.getActivityB()?.let {
                        Toast.makeText(
                            it,
                            it.getString(R.string.error_message_email_empty),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    return null
                }
                !user.email.isValidEmail() -> {
                    getNavigator()?.getActivityB()?.let {
                        Toast.makeText(
                            it,
                            it.getString(R.string.error_message_email_invalid),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    return null
                }
                user.gender.isEmpty() -> {
                    getNavigator()?.getActivityB()?.let {
                        Toast.makeText(
                            it,
                            it.getString(R.string.error_message_gender),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    return null
                }
                user.status.isEmpty() -> {
                    getNavigator()?.getActivityB()?.let {
                        Toast.makeText(
                            it,
                            it.getString(R.string.error_message_status),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    return null
                }
                else -> {
                    return user
                }
            }
        }
    }

    fun onNameChanged(value: CharSequence) {
        user.get()?.let {
            it.name = value.toString()
            user.set(it)
        }
    }

    fun onEmailChanged(value: CharSequence) {
        user.get()?.let {
            it.email = value.toString()
            user.set(it)
        }
    }

    fun onGenderChanged(radioGroup: RadioGroup, id: Int) {
        if (id == R.id.radio_button_male) {
            user.get()?.let {
                it.gender = "male"
            }
        } else {
            user.get()?.let {
                it.gender = "female"
            }
        }
    }

    fun onStatusChanged(radioGroup: RadioGroup, id: Int) {
        if (id == R.id.radio_button_active) {
            user.get()?.let {
                it.status = "active"
            }
        } else {
            user.get()?.let {
                it.status = "inactive"
            }
        }
    }
}

interface UserDetailNavigator : BaseNavigator {
    fun onUserUpdated(user: User)
}