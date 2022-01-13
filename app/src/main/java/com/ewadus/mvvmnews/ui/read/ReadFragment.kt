package com.ewadus.mvvmnews.ui.read

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ewadus.mvvmnews.R
import com.ewadus.mvvmnews.databinding.FragmentReadBinding
import com.ewadus.mvvmnews.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReadFragment : Fragment() {

    private var _binding: FragmentReadBinding? = null
    private val binding get() = _binding!!
    private val args: ReadFragmentArgs by navArgs()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReadBinding.inflate(inflater, container, false)

        val article = args.article
        binding.webView.apply {
            webViewClient = WebViewClient()
            article?.url?.let { loadUrl(it) }
        }

        binding.favBtn.setOnClickListener {
            viewModel.saveNews(article!!)
            Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }


}