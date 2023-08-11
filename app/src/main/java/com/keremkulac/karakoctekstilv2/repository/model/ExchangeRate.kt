package com.keremkulac.karakoctekstilv2.repository.model

import com.google.gson.annotations.SerializedName

data class ExchangeRate(
    @SerializedName("conversion_rates")
    val result: Map<String, Double>
)
