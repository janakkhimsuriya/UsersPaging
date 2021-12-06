package com.base.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.base.BR
import com.base.databinding.ViewItemLoadingStateBinding
import com.base.state.NetworkState

class PagingLoadStateAdapter<T : Any, VH : RecyclerView.ViewHolder>(private val adapter: PagingDataAdapter<T, VH>) :
    LoadStateAdapter<PagingLoadStateAdapter.LoadingStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
        holder.bindingImpl.imgRetry.setOnClickListener {
            adapter.retry()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder {
        return LoadingStateViewHolder(
            ViewItemLoadingStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class LoadingStateViewHolder(internal val bindingImpl: ViewItemLoadingStateBinding) :
        RecyclerView.ViewHolder(bindingImpl.root) {

        fun bind(loadState: LoadState) {
            val networkState = NetworkState()
            when (loadState) {
                is LoadState.Loading -> {
                    networkState.status = NetworkState.Status.RUNNING
                    networkState.message = ""
                }
                is LoadState.NotLoading -> {
                    networkState.status = NetworkState.Status.SUCCESS
                    networkState.message = ""
                }
                is LoadState.Error -> {
                    networkState.status = NetworkState.Status.FAILED
                    networkState.message = loadState.error.message ?: ""
                }
            }

            bindingImpl.setVariable(BR.dataModel, networkState)
            bindingImpl.executePendingBindings()
        }
    }
}