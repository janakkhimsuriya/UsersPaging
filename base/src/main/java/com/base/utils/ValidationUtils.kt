package com.base.utils

import android.util.Patterns

fun CharSequence?.isValidEmail(): Boolean {
    return !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}