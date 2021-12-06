package com.base.paging

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class GenericDiffCallBack<E>(private val forceUpdate: Boolean = false) :
    DiffUtil.ItemCallback<E>() {
    override fun areItemsTheSame(oldItem: E, newItem: E): Boolean {
        return if (forceUpdate) false else oldItem.hashCode() == newItem.hashCode()
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: E, newItem: E): Boolean {
        return if (forceUpdate) false else oldItem == newItem
    }
}