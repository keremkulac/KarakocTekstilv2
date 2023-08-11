package com.keremkulac.karakoctekstilv2.repository

import com.keremkulac.karakoctekstilv2.repository.model.ExchangeRate
import com.keremkulac.karakoctekstilv2.Resource

interface ExchangeRateRepositoryInterface {
    suspend fun getExchangeRateDollar() : Resource<ExchangeRate>
    suspend fun getExchangeRateEuro() : Resource<ExchangeRate>

}