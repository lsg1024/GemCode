package com.example.gemcode.repository

import com.example.gemcode.dto.FactoryAndStore

class StoreRepository {

    fun getFactoryList() : List<FactoryAndStore> {

        val testDataList = List(1000) { FactoryAndStore("상점${it + 1}") }

        return testDataList
    }
}