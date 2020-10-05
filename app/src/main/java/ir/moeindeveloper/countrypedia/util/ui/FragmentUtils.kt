package ir.moeindeveloper.countrypedia.util.ui

import android.view.View
import ir.moeindeveloper.countrypedia.databinding.FragmentHomeBinding
import ir.moeindeveloper.countrypedia.core.state.State

fun FragmentHomeBinding.changeState(state: State) {
    when(state) {
        State.LOADING -> {
            if (homeLayout.root.isRefreshing) homeLayout.root.isRefreshing = true
            this.noConnectionLayout.root.visibility = View.GONE
            this.homeLayout.root.visibility = View.GONE
            this.loadingLayout.root.visibility = View.VISIBLE
            this.loadingLayout.loadingIndicator.visibility = View.VISIBLE
            this.loadingLayout.loadingIndicator.smoothToShow()
        }

        State.SUCCESS -> {
            this.loadingLayout.root.visibility = View.GONE
            this.noConnectionLayout.root.visibility = View.GONE
            this.homeLayout.root.visibility = View.VISIBLE
            if (homeLayout.root.isRefreshing) homeLayout.root.isRefreshing = false
        }

        State.ERROR -> {
            this.loadingLayout.root.visibility = View.GONE
            this.homeLayout.root.visibility = View.GONE
            this.noConnectionLayout.root.visibility = View.VISIBLE
            this.noConnectionLayout.noConnectionAnim.visibility = View.VISIBLE
            this.noConnectionLayout.tryAgainButton.visibility = View.VISIBLE
            if (homeLayout.root.isRefreshing) homeLayout.root.isRefreshing = false
        }
    }
}