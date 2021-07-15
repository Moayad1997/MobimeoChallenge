package com.apps.mobimeochallenge.ui.search

import androidx.lifecycle.*
import com.apps.service.repository.gifRepository.IGifRepository
import com.apps.service.repository.gifRepository.response.Gif
import com.apps.service.retrofit.utils.Status
import com.apps.service.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch



/**
 * Created by Moayad Albarbary on 7/13/2021.
 */
class SearchViewModel(private val gifRepository: IGifRepository) : ViewModel() {


    private val _items = MutableLiveData<MutableList<Gif>>().apply { value = mutableListOf() }
    val items: LiveData<MutableList<Gif>> = _items

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean> = _isLoading

    val empty: LiveData<Boolean> = Transformations.map(_items) {
        it.isEmpty()
    }

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    var offset = 0
    private var lastSearchQuery = ""


    fun loadMoreItems() {
        viewModelScope.launch(Dispatchers.IO) {
            offset += Constants.ITEMS_LIMIT
            gifRepository.search(
                query = lastSearchQuery,
                offset = offset,
                itemLimit = Constants.ITEMS_LIMIT
            )
                .collectLatest {
                    when (it.status) {
                        Status.SUCCESS -> {
                            if (it.data != null && it.data!!.meta.status == 200) {
                                _items.postValue(it.data?.searchResult)
                            } else {
                                _error.postValue(
                                    it.data?.meta?.msg ?: "Something happen try again please"
                                )
                            }
                            _isLoading.postValue(false)
                        }
                        Status.ERROR -> {
                            _error.postValue(it.message ?: "Something happen try again please")
                            _isLoading.postValue(false)
                        }
                        Status.LOADING -> {
                            _isLoading.postValue(true)
                        }
                    }

                }


        }
    }

    fun clearSearchData() {
        offset = 0
        _items.value = mutableListOf()
        lastSearchQuery = ""
    }

    fun isLastSearchQueryEmpty(): Boolean {
        return lastSearchQuery.isEmpty()
    }

    fun search(query: String) {
        lastSearchQuery = query
        offset = 0
        viewModelScope.launch(Dispatchers.IO) {
            gifRepository.search(query = query, offset = offset, itemLimit = Constants.ITEMS_LIMIT)
                .collectLatest {
                    when (it.status) {
                        Status.SUCCESS -> {
                            _items.postValue(it.data?.searchResult)
                            _isLoading.postValue(false)
                        }
                        Status.ERROR -> {
                            _isLoading.postValue(false)
                            _error.postValue(it.message ?: "Something happen try again please")
                        }
                        Status.LOADING -> {
                            _isLoading.postValue(true)
                        }
                    }

                }


        }
    }

}