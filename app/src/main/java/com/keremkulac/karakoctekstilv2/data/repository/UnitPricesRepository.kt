package com.keremkulac.karakoctekstilv2.data.repository

import com.keremkulac.karakoctekstilv2.data.local.dao.UnitPricesDAO
import com.keremkulac.karakoctekstilv2.model.unitPrices.UnitPrices
import javax.inject.Inject

class UnitPricesRepository @Inject constructor(
    private val unitPricesDAO: UnitPricesDAO
) {
    suspend fun insertUnitPrices (unitPrices: UnitPrices) = unitPricesDAO.insertUnitPrices(unitPrices)

    suspend fun getUnitPrices ( ) = unitPricesDAO.getAllUnitPrices()

}