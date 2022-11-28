package com.e444er.wall.fragment

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.e444er.wall.R
import com.e444er.wall.databinding.DownloadFragmentBinding
import com.e444er.wall.util.BlurHashDecoder
import com.e444er.wall.util.viewBinding

class DownloadFragment : Fragment(R.layout.download_fragment) {

    private val binding: DownloadFragmentBinding by viewBinding()
    private val args: DownloadFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadWall(args.imageData[0])
        addCallBack()
    }

    private fun loadWall(url: String) {
        val blurHashAsDrawable = BlurHashDecoder.decode(args.imageData[1])
        Glide.with(this)
            .load(url)
            .centerCrop()
            .placeholder(blurHashAsDrawable?.toDrawable(this.resources))
            .error(blurHashAsDrawable)
            .into(binding.downloadImageView)

        binding.constraintDownload.background = BitmapDrawable(this.resources, blurHashAsDrawable)
    }

    private fun addCallBack() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}