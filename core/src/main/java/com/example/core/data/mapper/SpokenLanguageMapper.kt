package com.example.core.data.mapper

import com.example.core.domain.model.SpokenLanguage
import com.example.core.data.remote.model.SpokenLanguage as DataSpokenLanguage

object SpokenLanguageMapper {
    fun DataSpokenLanguage.toDomainModel(): SpokenLanguage {
        return SpokenLanguage(
            englishName = this.englishName,
            iso6391 = this.iso6391,
            name = this.name
        )
    }
}