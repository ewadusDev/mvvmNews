package com.ewadus.mvvmnews.ui.feed

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ewadus.mvvmnews.R
import com.ewadus.mvvmnews.databinding.FragmentFeedBinding
import com.ewadus.mvvmnews.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment(), OnclickListener {

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private val feedAdapter = FeedAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)



        observeData()
        setupRecyclerView()

        return binding.root
    }

    private fun observeData() {
        viewModel.articleNetwork.observe(this, Observer {
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

    override fun onClickItemView(position: Int) {

        val bundle = Bundle().apply {
            putSerializable("article", feedAdapter.currentList[position])
        }
        findNavController().navigate(R.id.action_feedFragment_to_readFragment, bundle)

    }

    override fun onClickShare(position: Int) {
        val sendIntent = Intent().apply {
            this.action = Intent.ACTION_SEND
            this.putExtra(Intent.EXTRA_TEXT,feedAdapter.currentList[position].url)
            this.type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}