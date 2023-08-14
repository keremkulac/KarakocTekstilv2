package com.keremkulac.karakoctekstilv2.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.keremkulac.karakoctekstilv2.model.unitPrices.UnitPrices

@Dao
interface UnitPricesDAO {

    @Insert
    suspend fun insertUnitPrices(unitPrices: UnitPrices)

    @Query("SELECT * FROM unitPrices")
    suspend fun getAllUnitPrices() : UnitPrices
}