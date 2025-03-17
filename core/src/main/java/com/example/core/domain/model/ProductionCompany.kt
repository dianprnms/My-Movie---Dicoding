package com.example.core.domain.model

data class ProductionCompany(
    val id: Int?,
    val logoPath: String?,
    val name: String?,
    val originCountry: String?
)

fun ProductionCompany.toDomainModel(): ProductionCompany {
    return ProductionCompany(
        id = this.id,
        logoPath = this.logoPath,
        name = this.name,
        originCountry = this.originCountry
    )
}