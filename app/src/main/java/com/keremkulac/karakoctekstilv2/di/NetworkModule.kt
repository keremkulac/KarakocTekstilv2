package com.keremkulac.karakoctekstilv2.di

import android.content.Context
import androidx.room.Room
import com.keremkulac.karakoctekstilv2.BASE_URL
import com.keremkulac.karakoctekstilv2.data.remote.ExchangeRateService
import com.keremkulac.karakoctekstilv2.data.local.dao.UnitPricesDAO
import com.keremkulac.karakoctekstilv2.data.local.UnitPricesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance() : ExchangeRateService {
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ExchangeRateService::class.java)
    }


    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext appContext: Context): UnitPricesDatabase {
        return Room.databaseBuilder(
            appContext,
            UnitPricesDatabase::class.java,
            "unitPrices.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(unitPricesDatabase: UnitPricesDatabase) : UnitPricesDAO {
        return unitPricesDatabase.unitPricesDao()
    }
}