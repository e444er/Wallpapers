package com.e444er.wall.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.e444er.wall.R
import com.e444er.wall.adapter.RecyclerViewAdapter
import com.e444er.wall.databinding.PopularFragmentBinding
import com.e444er.wall.databinding.RandomFragmentBinding
import com.e444er.wall.paging.lodingstate.LoadStateAdapter
import com.e444er.wall.util.Constants
import com.e444er.wall.util.viewBinding
import com.e444er.wall.viewmodel.PopularViewModel
import com.e444er.wall.viewmodel.RandomViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RandomFragment:Fragment(R.layout.random_fragment) {

    private val binding: RandomFragmentBinding by viewBinding()
    private val viewModel: RandomViewModel by viewModels()
    private val mAdapter by lazy { RecyclerViewAdapter(Constants.NavigationIntent.FromHomeToDownload) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvRan.adapter = mAdapter.withLoadStateHeaderAndFooter(
            header = LoadStateAdapter{mAdapter.retry()},
            footer = LoadStateAdapter{mAdapter.retry()}
        )

        mAdapter.addLoadStateListener { loadState ->
            binding.rvRan.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
            handelError(loadState)
        }

        binding.buttonRetry.setOnClickListener {
            mAdapter.retry()
        }
        lifecycleScope.launch {
            viewModel.randomPage.collectLatest {
                mAdapter.submitData(it)
            }
        }
    }
    private fun handelError(loadStates: CombinedLoadStates) {
        val errorState = loadStates.source.append as? LoadState.Error
            ?: loadStates.source.prepend as? LoadState.Error

        errorState?.let {
            Toast.makeText(context, "try again later", Toast.LENGTH_LONG).show()
        }

    }
}