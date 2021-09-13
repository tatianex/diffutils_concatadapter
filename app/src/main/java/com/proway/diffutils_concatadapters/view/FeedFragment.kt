package com.proway.diffutils_concatadapters.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proway.diffutils_concatadapters.R
import com.proway.diffutils_concatadapters.adapter.FeedImageAdapter
import com.proway.diffutils_concatadapters.adapter.FeedVideoAdapter
import com.proway.diffutils_concatadapters.adapter.HeaderAdapter
import com.proway.diffutils_concatadapters.databinding.FragmentFeedBinding
import com.proway.diffutils_concatadapters.model.Image
import com.proway.diffutils_concatadapters.model.VideoConfig
import com.proway.diffutils_concatadapters.view_model.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment(private val feedType: FeedType): Fragment(R.layout.fragment_feed) {

    private lateinit var viewModel: FeedViewModel
    private lateinit var binding: FragmentFeedBinding
    lateinit var adapters: ConcatAdapter
    private val adapterFeed = FeedImageAdapter()
    private val adapterVideo = FeedVideoAdapter()
    private val adapterHeader = HeaderAdapter {
        viewModel.fetchImages(it)
    }

    private val observerImages = Observer<List<Image>> {
        adapterFeed.submitList(it)
    }

    private val observerVideos = Observer<List<VideoConfig>> {
        adapterVideo.submitList(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFeedBinding.bind(view)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.images.observe(viewLifecycleOwner, observerImages)
        viewModel.videos.observe(viewLifecycleOwner, observerVideos)

        adapters = if (feedType == FeedType.VIDEO) ConcatAdapter(adapterVideo) else ConcatAdapter(adapterHeader, adapterFeed)
        setupRecyclerView()
    }

    private fun setupRecyclerView() = with(binding.feedRecyclerView) {
        adapter = adapters
        layoutManager = LinearLayoutManager(requireContext())
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                hideSoftInput()
            }
        })

        if (feedType == FeedType.VIDEO) viewModel.fetchVideo() else viewModel.fetchImages()
    }
}

enum class FeedType{
    VIDEO,
    IMAGE
}

fun View.hideSoftInput() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}