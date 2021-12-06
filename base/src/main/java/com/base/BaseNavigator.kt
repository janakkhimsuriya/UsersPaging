package com.base

import android.app.Activity
import androidx.annotation.StringRes
import com.base.listeners.ClickListener

interface BaseNavigator {
    fun getActivityB(): Activity?

    fun string(@StringRes resId: Int): String

    fun showInternetConnectionDialog(listener: ClickListener? = null)

    fun showConfirmationDialog(
        title: String,
        message: String,
        positive: String? = null,
        negative: String? = null,
        listener: ClickListener? = null
    )

    fun showConfirmationDialog(
        @StringRes title: Int = 0,
        @StringRes message: Int = 0,
        @StringRes positive: Int = 0,
        @StringRes negative: Int = 0,
        listener: ClickListener? = null
    )

    fun showLoadingDialog()
    fun hideLoadingDialog()
}