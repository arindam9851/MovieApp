package com.movieapp.ui.main.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.movieapp.R
import com.movieapp.domainmodel.Trending
import com.movieapp.ui.main.viewmodel.MainStateEvent
import com.movieapp.ui.main.viewmodel.MainViewModel
import com.movieapp.utils.DataState
import com.movieapp.utils.NetworkHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    @Inject
    lateinit var networkHelper: NetworkHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeObverser()

        if(networkHelper.isNetworkConnected()){
            viewModel.setStateEvent(MainStateEvent.GetNetworkTrendingEvent)
        }
        else{
            viewModel.setStateEvent(MainStateEvent.GetCacheTrendingEvent)

        }
    }
    private fun subscribeObverser(){
        viewModel.datastate.observe(this, Observer {dataState->
            when(dataState){
                is DataState.Success<List<Trending>>->{
                    displayProgressBar(false)
                    appendBlogTitles(dataState.data)
                }
                is DataState.Error ->{
                    displayProgressBar(false)
                    displayError(dataState.exception.message)

                }
                is DataState.Loading->{
                    displayProgressBar(true)

                }
            }
        })
    }

    private fun displayProgressBar(isDisplayed: Boolean){
        progress_bar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    private fun displayError(message: String?){
        println("Debug: $message")
    }

    private fun appendBlogTitles(data: List<Trending>){
        println("Debug: $data")

    }
}