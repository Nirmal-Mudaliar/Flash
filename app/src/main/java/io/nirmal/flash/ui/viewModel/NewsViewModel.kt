package io.nirmal.flash.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.nirmal.flash.models.NewsResponse
import io.nirmal.flash.repository.NewsRepository
import io.nirmal.flash.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.http.Query

class NewsViewModel(
    val newsRepository: NewsRepository
): ViewModel() {
    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val breakingNewsPage = 1

    val searchedNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val searchedNewsPage = 1

    init {
        getAllBreakingNews(countryCode = "in")
    }

     fun getAllBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getAllBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun getAllSearchedNews(query: String) = viewModelScope.launch {
        searchedNews.postValue(Resource.Loading())
        val response = newsRepository.getSearchedNews(query, searchedNewsPage)
        searchedNews.postValue(handleSearchedNewsResponse(response))
    }

    fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    fun handleSearchedNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}