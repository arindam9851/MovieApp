package com.movieapp.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TrendingDatabseTest : TestCase(){
    private lateinit var db: TrendingDatabse
    private lateinit var dao: TrendingDao

    @Before
    public override fun setUp() {
        val context= ApplicationProvider.getApplicationContext<Context>()
        db=Room.inMemoryDatabaseBuilder(context, TrendingDatabse::class.java).build()
        dao= db.trendingDoa()

    }

    @After
    fun closeDb(){
        db.close()
    }

    @Test
    fun insertAndGet()= runBlocking{
        val trendingCacheEntity= TrendingCacheEntity(1, "Loki", 8.5, "Great show", 1121, "image.jpg")
        dao.insert(trendingCacheEntity)
        val trendingData= dao.getTrending()
        assertThat(trendingData.contains(trendingCacheEntity)).isTrue()
    }
}