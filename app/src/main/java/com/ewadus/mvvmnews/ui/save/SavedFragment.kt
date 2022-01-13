package com.ewadus.mvvmnews.ui.save

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ewadus.mvvmnews.R
import com.ewadus.mvvmnews.databinding.FragmentSavedBinding
import com.ewadus.mvvmnews.ui.MainViewModel
import com.ewadus.mvvmnews.ui.feed.FeedAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : Fragment(),OnClickListener {

    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private val saveAdapter = SaveAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedBinding.inflate(inflater, container, false)

        observeData()
        setupRecyclerView()
        swipeHelper()

        return binding.root

    }

    private fun swipeHelper() {
        val itemTouchHelperSimpleCallbackList = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = saveAdapter.currentList[position]
                viewModel.deleteNews(article)
            }

        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperSimpleCallbackList)
        itemTouchHelper.attachToRecyclerView(binding.recyclerviewSave)

    }
    private fun setupRecyclerView() {
        binding.recyclerviewSave.apply {
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun observeData() {
        viewModel.getSaveNews().observe(this, Observer {
            saveAdapter.submitList(it)
            binding.recyclerviewSave.adapter = saveAdapter
        })


    }

    override fun onClickItemView(position: Int) {
        val bundle = Bundle().apply {
            putSerializable("article", saveAdapter.currentList[position])
        }
        findNavController().navigate(R.id.action_savedFragment_to_readFragment, bundle)
    }

    override fun onClickShare(position: Int) {
        val sendIntent = Intent().apply {
            this.action = Intent.ACTION_SEND
            this.putExtra(Intent.EXTRA_TEXT,saveAdapter.currentList[position].url)
            this.type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }


}