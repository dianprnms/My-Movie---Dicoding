package com.example.core.data.mapper

import com.example.core.domain.model.ProductionCountry
import com.example.core.data.remote.model.ProductionCountry as DataProductionCountry

object DataProductionCountriesMapper {
    fun DataProductionCountry.toDomainModel(): ProductionCountry {
        return ProductionCountry(
            iso31661 = this.iso31661,
            name = this.name,
        )
    }
}