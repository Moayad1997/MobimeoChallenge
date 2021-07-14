package com.apps.mobimeochallenge.ui.search


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.apps.mobimeochallenge.R
import com.apps.mobimeochallenge.databinding.FragmentSearchBinding
import com.apps.mobimeochallenge.ui.search.viewUtils.SearchAdapter
import com.apps.mobimeochallenge.utils.getQueryTextChangeStateFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@FlowPreview
class SearchFragment : Fragment() {
    private val searchViewModel: SearchViewModel by viewModel()

    private lateinit var searchFragmentBinding: FragmentSearchBinding

    private lateinit var searchAdapter: SearchAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (::searchFragmentBinding.isInitialized.not()) {
            searchFragmentBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        }
        searchFragmentBinding.searchViewModel = searchViewModel
        return searchFragmentBinding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchFragmentBinding.lifecycleOwner = this.viewLifecycleOwner
        setupSearchListAdapter()
        setupGifSearch()
        setupLoadingMoreItems()
    }

    private var currentPage = 1
    private fun setupLoadingMoreItems() {
        searchFragmentBinding.smartRefreshLayout.setOnLoadMoreListener {

        }
    }


    private fun setupSearchListAdapter() {
        val viewModel = searchFragmentBinding.searchViewModel
        if (viewModel != null) {
            searchAdapter = SearchAdapter(viewModel)
            searchFragmentBinding.searchList.adapter = searchAdapter
        } else {
            println("ViewModel not initialized when attempting to set up adapter.")
        }
    }



    private fun setupGifSearch() {
        lifecycleScope.launch(Dispatchers.Default) {
            searchFragmentBinding.searchView.getQueryTextChangeStateFlow()
                .debounce(300)
                .filter { textQuery ->
                    return@filter textQuery.isNotEmpty()
                }
                .distinctUntilChanged()
                .collectLatest { textQuery ->
                    currentPage = 1
                    searchViewModel.search(textQuery, currentPage)
                }
        }


    }


}


