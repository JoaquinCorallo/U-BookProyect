package com.okbit.ubook.crud

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okbit.ubook.database.BkDatabase

class MainBookViewModel: ViewModel() {
    private var _bkList = MutableLiveData<MutableList<Book>>()
    val bkList: LiveData<MutableList<Book>>
    get() = _bkList

    init {
        fetchBook()
    }

    private fun fetchBook() {
        TODO("Not yet implemented")
    }
}