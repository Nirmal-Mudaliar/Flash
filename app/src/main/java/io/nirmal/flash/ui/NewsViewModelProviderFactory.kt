package io.nirmal.flash.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.nirmal.flash.repository.NewsRepository
import io.nirmal.flash.ui.viewModel.NewsViewModel

class NewsViewModelProviderFactory(
    val newsRepository: NewsRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}