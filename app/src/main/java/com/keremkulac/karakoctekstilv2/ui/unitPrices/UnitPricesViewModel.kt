package com.keremkulac.karakoctekstilv2.ui.unitPrices

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keremkulac.karakoctekstilv2.data.repository.UnitPricesRepository
import com.keremkulac.karakoctekstilv2.model.unitPrices.UnitPrices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UnitPricesViewModel
@Inject constructor(private val unitPricesRepository: UnitPricesRepository) : ViewModel() {
    private val _unitPrices = MutableLiveData<UnitPrices>()
    val unitPrices: LiveData<UnitPrices>
        get() = _unitPrices

    init {
        getUnitPrices()
        get()
    }
    fun saveUnitPricesUpdates(unitPrices: UnitPrices){
        viewModelScope.launch{
            unitPricesRepository.insertUnitPrices(unitPrices)
        }
    }


    private fun get() = viewModelScope.launch {
         _unitPrices.postValue(unitPricesRepository.getUnitPrices())
    }
    private fun getUnitPrices() {
        /*
        val unitPrices  :UnitPrices? = unitPricesDatabase.unitPricesDao().getAllUnitPrices()
        unitPrices?.let {unitPrices->
                _unitPrices.postValue(unitPrices)
        }

         */
    }
}