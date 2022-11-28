package com.e444er.wall.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.e444er.wall.R
import com.e444er.wall.adapter.RecyclerViewAdapter
import com.e444er.wall.databinding.HomeFragmentBinding
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
        binding.rvHome.adapter = mAdapter
        lifecycleScope.launch {
            viewModel.homePage.collectLatest {
                mAdapter.submitData(it)
            }
        }
    }
}