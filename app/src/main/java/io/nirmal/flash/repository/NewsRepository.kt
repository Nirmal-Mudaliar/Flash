package io.nirmal.flash.repository

import io.nirmal.flash.data.api.RetrofitInstance

class NewsRepository() {
    suspend fun getAllBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getAllBreakingNews(countryCode, pageNumber)

    suspend fun getSearchedNews(query: String, pageNumber: Int) =
        RetrofitInstance.api.getSearchedNews(query, pageNumber)
}