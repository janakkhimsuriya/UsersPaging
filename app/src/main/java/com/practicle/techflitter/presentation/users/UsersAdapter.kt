package com.practicle.techflitter.presentation.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.base.listeners.ClickListener
import com.base.paging.GenericDiffCallBack
import com.practicle.techflitter.BR
import com.practicle.techflitter.databinding.ViewUserItemBinding
import com.practicle.techflitter.domain.model.User
import okhttp3.internal.notify

class UsersAdapter constructor(private val clickListener: ClickListener) :
    PagingDataAdapter<User, UsersAdapter.ViewHolder>(GenericDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewUserItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
            holder.binding.root.setOnClickListener { view ->
                clickListener.onClick(view, it)
            }
        }
    }

    class ViewHolder(internal val binding: ViewUserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: User) {
            binding.apply {
                setVariable(BR.model, model)
            }
        }
    }
}