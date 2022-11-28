package com.e444er.wall.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.e444er.wall.R
import com.e444er.wall.adapter.CategoriesAdapter
import com.e444er.wall.databinding.CategoriesFragmentBinding
import com.e444er.wall.util.ApiListCategory
import com.e444er.wall.util.viewBinding

class CategoriesFragment : Fragment(R.layout.categories_fragment) {

    private val binding: CategoriesFragmentBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.catRV.adapter = CategoriesAdapter(ApiListCategory.list)
    }
}