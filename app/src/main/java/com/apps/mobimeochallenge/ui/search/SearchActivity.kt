package com.apps.mobimeochallenge.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.apps.mobimeochallenge.R
import com.apps.mobimeochallenge.databinding.ActivitySearchBinding
import com.apps.mobimeochallenge.ui.details.GifDetailsActivity
import com.apps.mobimeochallenge.ui.search.viewUtils.OnClickListener
import com.apps.mobimeochallenge.ui.search.viewUtils.SearchAdapter
import com.apps.mobimeochallenge.utils.GeneralUtils
import com.apps.mobimeochallenge.utils.getQueryTextChangeStateFlow
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {
    private val searchViewModel: SearchViewModel by viewModel()

    private lateinit var searchActivityBinding: ActivitySearchBinding

    private lateinit var searchAdapter: SearchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (::searchActivityBinding.isInitialized.not()) {
            searchActivityBinding = ActivitySearchBinding.inflate(layoutInflater)
        }
        searchActivityBinding.searchViewModel = searchViewModel
        setContentView(searchActivityBinding.root)

        searchActivityBinding.lifecycleOwner = this
        setupSearchListAdapter()
        setupGifSearch()
        setupClearSearchView()
        setupSearchObserver()
        setupLoadingMoreItems()
    }


    private fun setupSearchObserver() {
        searchViewModel.items.observe(this) {
            if (searchViewModel.offset != 0) {
                searchActivityBinding.smartRefreshLayout.finishLoadMore()
                searchAdapter.updateData(it)
            } else {
                searchAdapter.setNewList(it)
            }
        }

        searchViewModel.error.observe(this) {
            searchActivityBinding.smartRefreshLayout.finishLoadMore()
            showErrorDialog(it)

        }
    }

    private fun setupLoadingMoreItems() {
        searchActivityBinding.smartRefreshLayout.setOnLoadMoreListener {
            searchViewModel.loadMoreItems()
        }
    }

    private var errorAlertDialog: AlertDialog? = null
    private fun showErrorDialog(message: String?) {
        val errorMessage = when {
            message == null -> "Something went wrong try again please"
            message.contains("No address associated with hostname") -> getString(R.string.network_error)
            else -> message
        }
        if (errorAlertDialog != null && errorAlertDialog!!.isShowing) {
            errorAlertDialog!!.setMessage(errorMessage)
        } else {
            errorAlertDialog = AlertDialog.Builder(this)
                .setTitle(getString(R.string.error_title))
                .setMessage(errorMessage)
                .setPositiveButton(getString(R.string.ok)) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        }


    }

    private fun setupSearchListAdapter() {
        val viewModel = searchActivityBinding.searchViewModel
        if (viewModel != null) {
            searchAdapter = SearchAdapter(mutableListOf(), object : OnClickListener {
                override fun onGifClick(url: String) {
                    GeneralUtils.hideKeyboard(this@SearchActivity)
                    val intent = Intent(this@SearchActivity, GifDetailsActivity::class.java).apply {
                        putExtra("URL", url)
                    }
                    this@SearchActivity.startActivity(intent)
                }
            })
            searchActivityBinding.searchList.adapter = searchAdapter
        } else {
            println("ViewModel not initialized when attempting to set up adapter.")
        }
    }

    private fun setupClearSearchView() {
        searchActivityBinding.clearTextIcon.setOnClickListener {
            searchViewModel.clearSearchData()
            searchActivityBinding.searchEditText.clearFocus()
            searchActivityBinding.searchEditText.text = null


        }
    }

    private fun setupGifSearch() {

        Glide.with(searchActivityBinding.loading)
            .asGif().load(R.drawable.spinner)
            .into(searchActivityBinding.loading)

        searchActivityBinding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                GeneralUtils.hideKeyboard(this@SearchActivity)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        lifecycleScope.launch(Dispatchers.Default) {
            searchActivityBinding.searchEditText.getQueryTextChangeStateFlow()
                .debounce(300)
                .filter { textQuery ->
                    return@filter textQuery.isNotEmpty()
                }
                .distinctUntilChanged()
                .collectLatest { textQuery ->
                    searchViewModel.search(textQuery)
                }
        }


    }

}