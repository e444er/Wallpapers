package com.e444er.wall.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.e444er.wall.R
import com.e444er.wall.adapter.RecyclerViewAdapter
import com.e444er.wall.databinding.PopularFragmentBinding
import com.e444er.wall.util.viewBinding
import com.e444er.wall.viewmodel.PopularViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PopularFragment : Fragment(R.layout.popular_fragment) {

    private val binding: PopularFragmentBinding by viewBinding()
    private val viewModel: PopularViewModel by viewModels()
    private val mAdapter by lazy { RecyclerViewAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPop.adapter = mAdapter
        lifecycleScope.launch {
            viewModel.popularPage.collectLatest {
                mAdapter.submitData(it)
            }
        }
    }
}