package com.keremkulac.karakoctekstilv2.repository

import com.keremkulac.karakoctekstilv2.repository.model.ExchangeRate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeRateRepository {
    @GET("dc1922020d2b5354bdc85ecf/latest/{currency_unit}")
    suspend fun getExchangeRates(@Path("currency_unit") currencyUnit : String) : Response<ExchangeRate>
}