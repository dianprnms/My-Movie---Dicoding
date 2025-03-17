package com.example.core.data.mapper

import com.example.core.domain.model.ProductionCompany
import com.example.core.data.remote.model.ProductionCompany as DataProductionCompany

object DataProductionCompanyMapper {
    fun DataProductionCompany.toDomainModel(): ProductionCompany {
        return ProductionCompany(
            id = this.id,
            name = this.name,
            logoPath = this.logoPath,
            originCountry = this.originCountry
        )
    }
}