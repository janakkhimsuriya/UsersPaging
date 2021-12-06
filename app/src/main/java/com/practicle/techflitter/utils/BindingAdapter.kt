package com.practicle.techflitter.utils

import androidx.databinding.BindingAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.base.paging.PagingLoadStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.practicle.techflitter.presentation.dashboard.PagerAdapter

@BindingAdapter("app:bindAdapter", "app:bindTabLayout")
fun bindAdapter(view: ViewPager2, adapter: PagerAdapter?, tabLayout: TabLayout) {
    view.adapter = adapter
    view.adapter?.let {
        TabLayoutMediator(tabLayout, view) { tab, position ->
            tab.text = if (position == 0) "Users" else "Metadata"
        }.attach()
    }
}

@BindingAdapter("app:bindAdapter")
fun bindAdapter(view: RecyclerView, adapter: PagingDataAdapter<*, *>?) {
    adapter?.let {
        view.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter(adapter),
            footer = PagingLoadStateAdapter(adapter)
        )
    }
}

@BindingAdapter("app:bindAdapter")
fun bindAdapter(view: SwipeRefreshLayout, adapter: PagingDataAdapter<*, *>?) {
    view.setOnRefreshListener {
        adapter?.refresh()
    }
}