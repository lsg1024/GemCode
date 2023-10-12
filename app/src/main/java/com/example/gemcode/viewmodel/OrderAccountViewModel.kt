package com.example.gemcode.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.gemcode.dto.FactoryAndStore
import com.example.gemcode.repository.StoreRepository

class OrderAccountViewModel(application: Application) : AndroidViewModel(application) {
    private val storeRepository = StoreRepository()

    fun getStoreList() : List<FactoryAndStore> {
        return storeRepository.getFactoryList()
    }

}