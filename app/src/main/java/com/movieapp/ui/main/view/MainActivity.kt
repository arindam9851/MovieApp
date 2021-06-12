package com.movieapp.ui.main.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.movieapp.R
import com.movieapp.domainmodel.Trending
import com.movieapp.ui.main.adapter.TrendingAdapter
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
    private lateinit var mAdapter: TrendingAdapter
    private var mList = ArrayList<Trending>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUI()
        subscribeObverser()

        if(networkHelper.isNetworkConnected()){
            viewModel.setStateEvent(MainStateEvent.GetNetworkTrendingEvent)
        }
        else{
            viewModel.setStateEvent(MainStateEvent.GetCacheTrendingEvent)

        }
    }

    private fun setUpUI() {
        mAdapter = TrendingAdapter(mList, this)
        trending_recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL,false)
            adapter = mAdapter
        }

    }

    private fun subscribeObverser(){
        viewModel.datastate.observe(this, Observer {dataState->
            when(dataState){
                is DataState.Success<List<Trending>>->{
                    displayProgressBar(false)
                    setData(dataState.data)
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
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    private fun setData(data: List<Trending>){
        mList.clear()
        mList.addAll(data)
        mAdapter.notifyDataSetChanged()

    }
}