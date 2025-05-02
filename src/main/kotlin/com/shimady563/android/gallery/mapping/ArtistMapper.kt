package com.shimady563.android.gallery.mapping

import com.shimady563.android.common.mapping.Mapper
import com.shimady563.android.gallery.model.Artist
import com.shimady563.android.gallery.model.dto.ArtistDto
import org.springframework.stereotype.Component

@Component
class ArtistMapper : Mapper<Artist, ArtistDto> {
    override fun toDto(entity: Artist) = ArtistDto(
        entity.id!!,
        entity.name,
        entity.exhibition?.id!!
    )

    override fun toEntity(dto: ArtistDto) = Artist(
        dto.id,
        dto.name
    )
}