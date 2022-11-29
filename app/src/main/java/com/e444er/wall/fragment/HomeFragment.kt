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
import com.e444er.wall.databinding.HomeFragmentBinding
import com.e444er.wall.paging.lodingstate.LoadStateAdapter
import com.e444er.wall.util.Constants
import com.e444er.wall.util.viewBinding
import com.e444er.wall.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.home_fragment) {

    private val binding: HomeFragmentBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModels()
    private val mAdapter by lazy { RecyclerViewAdapter(Constants.NavigationIntent.FromHomeToDownload) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHome.adapter = mAdapter.withLoadStateHeaderAndFooter(
            header = LoadStateAdapter{mAdapter.retry()},
            footer = LoadStateAdapter{mAdapter.retry()}
        )

        mAdapter.addLoadStateListener { loadState ->
            binding.rvHome.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
            handelError(loadState)
        }

        binding.buttonRetry.setOnClickListener {
            mAdapter.retry()
        }

        lifecycleScope.launch {
            viewModel.homePage.collectLatest {
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