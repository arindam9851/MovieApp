package com.movieapp.ui.stateevent

sealed  class MainStateEvent {

    object GetNetworkTrendingEvent: MainStateEvent()

    object GetCacheTrendingEvent: MainStateEvent()
}