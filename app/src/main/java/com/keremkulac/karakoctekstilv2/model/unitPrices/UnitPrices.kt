package com.keremkulac.karakoctekstilv2.model.unitPrices

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unitPrices")
data class UnitPrices(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0    ,
    @ColumnInfo(name = "yarnWeightPrice")
    var yarnWeightPrice: String,
    @ColumnInfo(name = "clothMeterPrice")
    var clothMeterPrice: String,
    @ColumnInfo(name = "updated")
    var updated: Boolean,
)
