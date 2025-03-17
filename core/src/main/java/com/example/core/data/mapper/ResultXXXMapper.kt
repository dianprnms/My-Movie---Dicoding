package com.example.core.data.mapper

import com.example.core.data.remote.model.ResultXXX as DataResult
import com.example.core.domain.model.ResultXXX

object ResultXXXMapper {
    fun DataResult.mapToDomain(): ResultXXX {
        return ResultXXX(
            id = this.id,
            iso31661 = this.iso31661,
            iso6391 = this.iso6391,
            key = this.key,
            name = this.name,
            official = this.official,
            publishedAt = this.publishedAt,
            site = this.site,
            size = this.size,
            type = this.type
        )
    }
}