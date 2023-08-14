package com.keremkulac.karakoctekstilv2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.keremkulac.karakoctekstilv2.data.local.dao.UnitPricesDAO
import com.keremkulac.karakoctekstilv2.model.unitPrices.UnitPrices

@Database(entities = [UnitPrices::class], version = 1)
abstract class UnitPricesDatabase : RoomDatabase(){
    abstract fun unitPricesDao(): UnitPricesDAO
}