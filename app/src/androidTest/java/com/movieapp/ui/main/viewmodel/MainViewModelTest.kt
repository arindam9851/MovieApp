package com.movieapp.ui.main.viewmodel


import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.google.common.truth.Truth.assertThat
import com.movieapp.data.api.ApiInterface
import com.movieapp.data.api.NetworkMapper
import com.movieapp.data.repository.MainRepository
import com.movieapp.di.RepositoryModule
import com.movieapp.di.RoomModule
import com.movieapp.room.CacheMapper
import com.movieapp.room.TrendingCacheEntity
import com.movieapp.room.TrendingDao
import com.movieapp.room.TrendingDatabse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton


@UninstallModules(RoomModule::class, RepositoryModule::class)
@HiltAndroidTest
class MainViewModelTest{

    @get:Rule(order = 1)
    var hiltRule= HiltAndroidRule(this)

    @get:Rule(order = 2)
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Inject
    lateinit var appDao: TrendingDao

    @Inject
    lateinit var repository: MainRepository

    private lateinit var viewModel: MainViewModel
    @Before
    fun init(){
        hiltRule.inject()
        viewModel= MainViewModel(repository)
    }

    @After
    @Test
    fun roomDbCheck()= runBlocking{
        val trendingCacheEntity= TrendingCacheEntity(1, "Loki", 8.5, "Great show", 1121, "image.jpg")
        appDao.insert(trendingCacheEntity)
        val trendingData= appDao.getTrending()
        assertThat(trendingData.contains(trendingCacheEntity)).isTrue()

    }

    @After
    @Test
    fun  testViewModel()= runBlocking {
        viewModel.setStateEvent(MainStateEvent.GetCacheTrendingEvent)
        val result=  viewModel.datastate.getOrAwaitValue()
        assertThat(result != null).isTrue()

    }



    @Module
    @InstallIn(ApplicationComponent::class)
    object RoomTestModule{
        @Singleton
        @Provides
        fun provideTestingTrendingDb(@ApplicationContext context: Context): TrendingDatabse {
            return Room.databaseBuilder(
                context,
                TrendingDatabse::class.java,
                TrendingDatabse.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        @Singleton
        @Provides
        fun provideTestingTrendingDao(trendingDatabse: TrendingDatabse): TrendingDao {
            return trendingDatabse.trendingDoa()
        }
    }

    @Module
    @InstallIn(ApplicationComponent::class)
    object RepositoryTestModule{
        @Singleton
        @Provides
        fun provideTestRepository(
            trendingDao: TrendingDao,
            apiInterface: ApiInterface,
            cacheMapper: CacheMapper,
            networkMapper: NetworkMapper
        ): MainRepository {
            return MainRepository(trendingDao, apiInterface, cacheMapper, networkMapper)
        }
    }




}
