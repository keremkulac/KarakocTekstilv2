package com.keremkulac.karakoctekstilv2.data.repository.repo.exchangeRate

import com.keremkulac.karakoctekstilv2.model.exchangeRate.ExchangeRate
import com.keremkulac.karakoctekstilv2.Resource
import com.keremkulac.karakoctekstilv2.data.remote.ExchangeRateService
import javax.inject.Inject

interface ExchangeRateRepository {
    suspend fun getExchangeRateDollar() : Resource<ExchangeRate>
    suspend fun getExchangeRateEuro() : Resource<ExchangeRate>

}

class ExchangeRateRepositoryImp @Inject constructor(private val api : ExchangeRateService):
    ExchangeRateRepository {

    override suspend fun getExchangeRateDollar(): Resource<ExchangeRate> {
        return try {
            val response = api.getExchangeRates("USD")
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            }else{
                Resource.error("Error", null)
            }
        }catch (e : java.lang.Exception){
            Resource.error("No data", null)
        }    }

    override suspend fun getExchangeRateEuro(): Resource<ExchangeRate> {
        return try {
            val response = api.getExchangeRates("EUR")
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            }else{
                Resource.error("Error", null)
            }
        }catch (e : java.lang.Exception){
            Resource.error("No data", null)
        }    }


}

