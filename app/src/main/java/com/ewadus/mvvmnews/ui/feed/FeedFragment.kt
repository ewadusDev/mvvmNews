package com.ewadus.mvvmnews.ui.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ewadus.mvvmnews.databinding.FragmentFeedBinding
import com.ewadus.mvvmnews.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment() {

    private var _binding : FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private val feedAdapter = FeedAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(inflater,container,false)



        observeData()
        setupRecyclerView()

        return binding.root
    }

    private fun observeData() {
        viewModel.article.observe(this, Observer {
            feedAdapter.submitList(it)
            binding.recyclerview.adapter = feedAdapter
        })

    }

    private fun setupRecyclerView() {
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}