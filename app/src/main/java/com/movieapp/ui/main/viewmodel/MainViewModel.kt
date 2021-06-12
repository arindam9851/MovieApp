package com.movieapp.ui.main.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.movieapp.data.repository.MainRepository
import com.movieapp.domainmodel.Trending
import com.movieapp.utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository,
    @Assisted private val saveStateHandle: SavedStateHandle

):ViewModel()
{
    private val _dataState:MutableLiveData<DataState<List<Trending>>> = MutableLiveData()

    val datastate: LiveData<DataState<List<Trending>>>
        get() = _dataState
    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetNetworkTrendingEvent ->{
                    mainRepository.getTrending()
                        .onEach { dataState ->
                            _dataState.value=dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.GetCacheTrendingEvent->{
                    mainRepository.getCacheData()
                            .onEach { dataState ->
                                _dataState.value=dataState
                            }
                            .launchIn(viewModelScope)
                }
            }


        }
    }


}
sealed class MainStateEvent{

    object GetNetworkTrendingEvent: MainStateEvent()

    object GetCacheTrendingEvent: MainStateEvent()
}