package com.apps.mobimeochallenge.utils

import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.core.widget.doAfterTextChanged
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Moayad Albarbary on 7/14/2021.
 */
fun EditText.getQueryTextChangeStateFlow(): StateFlow<String> {
    val query = MutableStateFlow("")
    doAfterTextChanged {
        query.value = it.toString().trim()
    }
    return query
}