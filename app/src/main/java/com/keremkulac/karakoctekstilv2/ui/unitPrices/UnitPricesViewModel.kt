package com.keremkulac.karakoctekstilv2.ui.unitPrices

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keremkulac.karakoctekstilv2.Resource
import com.keremkulac.karakoctekstilv2.data.repository.UnitPricesRepository
import com.keremkulac.karakoctekstilv2.data.repository.repo.exchangeRate.ExchangeRateRepositoryImp
import com.keremkulac.karakoctekstilv2.model.exchangeRate.ExchangeRate
import com.keremkulac.karakoctekstilv2.model.unitPrices.UnitPrices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UnitPricesViewModel
@Inject constructor(private val unitPricesRepository: UnitPricesRepository,
                    private val exchangeRateRepositoryImp: ExchangeRateRepositoryImp) : ViewModel() {

    private val _unitPrices = MutableLiveData<UnitPrices>()
    val unitPrices: LiveData<UnitPrices>
        get() = _unitPrices
    private val _exchangeRateDollar = MutableLiveData<Resource<ExchangeRate>>()
    val exchangeRateDollar: LiveData<Resource<ExchangeRate>>
        get() = _exchangeRateDollar
    private val _exchangeRateEuro = MutableLiveData<Resource<ExchangeRate>>()
    val exchangeRateEuro: LiveData<Resource<ExchangeRate>>
        get() = _exchangeRateEuro

    init {
        getUnitPrices()
        getExchangeRateEuro()
        getExchangeRateDollar()
    }
    fun saveUnitPricesUpdates(unitPrices: UnitPrices){
        viewModelScope.launch{
            unitPricesRepository.insertUnitPrices(unitPrices)
        }
    }


    private fun getUnitPrices() = viewModelScope.launch {
         _unitPrices.postValue(unitPricesRepository.getUnitPrices())
    }

    fun updateUnitPrices(unitPrices: UnitPrices) {
        viewModelScope.launch{
            unitPricesRepository.updateUnitPrices(unitPrices)
        }
    }


    private fun getExchangeRateDollar() = viewModelScope.launch {
        val result = exchangeRateRepositoryImp.getExchangeRateDollar()
        result.let {
            _exchangeRateDollar.postValue(it)
        }
    }

    private fun getExchangeRateEuro() = viewModelScope.launch {
        val result = exchangeRateRepositoryImp.getExchangeRateEuro()
        result.let {
            _exchangeRateEuro.postValue(it)
        }
    }

}