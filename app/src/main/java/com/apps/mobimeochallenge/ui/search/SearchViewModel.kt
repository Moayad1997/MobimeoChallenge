package com.apps.mobimeochallenge.ui.search

import androidx.lifecycle.*
import com.apps.service.repository.gifRepository.IGifRepository
import com.apps.service.repository.gifRepository.response.Gif
import com.apps.service.retrofit.utils.Status
import com.apps.service.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Moayad Albarbary on 7/13/2021.
 */
class SearchViewModel(private val gifRepository: IGifRepository) : ViewModel() {


    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _items = MutableLiveData<List<Gif>>().apply { value = emptyList() }
    val items: LiveData<List<Gif>> = _items


    // This LiveData depends on another liveData  so we can use a transformation for it .
    val empty: LiveData<Boolean> = Transformations.map(_items) {
        it.isEmpty()
    }

    fun loadMoreItem(){
/*        _dataLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            gifRepository.search(query = query, page = page, itemLimit = Constants.ITEMS_LIMIT)
                .collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            _dataLoading.postValue(false)
                            _items.value?
                            _items.postValue(it.data?.searchResult)
                        }
                        Status.ERROR -> {
                            _dataLoading.postValue(false)
                        }
                        Status.LOADING -> {
                            _dataLoading.postValue(true)
                        }
                    }

                }


        }*/
    }
    fun search(query: String, page: Int) {
        _dataLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            gifRepository.search(query = query, page = page, itemLimit = Constants.ITEMS_LIMIT)
                .collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            _dataLoading.postValue(false)
                            _items.postValue(it.data?.searchResult)
                        }
                        Status.ERROR -> {
                            _dataLoading.postValue(false)
                        }
                        Status.LOADING -> {
                            _dataLoading.postValue(true)
                        }
                    }

                }


        }
    }

}