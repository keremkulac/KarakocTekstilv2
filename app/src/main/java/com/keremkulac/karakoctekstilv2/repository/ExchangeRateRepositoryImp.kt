package com.keremkulac.karakoctekstilv2.repository

import com.keremkulac.karakoctekstilv2.repository.model.ExchangeRate
import com.keremkulac.karakoctekstilv2.Resource
import javax.inject.Inject

class ExchangeRateRepositoryImp @Inject constructor(private val api : ExchangeRateRepository):
    ExchangeRateRepositoryInterface {

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