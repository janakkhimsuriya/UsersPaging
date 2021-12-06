package com.base.utils

inline fun <reified T : Any> Any.cast(): T {
    return this as T
}
