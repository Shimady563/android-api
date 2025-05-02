package com.shimady563.android.gallery.mapping

import com.shimady563.android.common.mapping.Mapper
import com.shimady563.android.gallery.model.Painting
import com.shimady563.android.gallery.model.dto.PaintingDto
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Component
class PaintingMapper : Mapper<Painting, PaintingDto> {
    override fun toDto(entity: Painting) = PaintingDto(
        entity.id!!,
        entity.title,
        Date.from(entity.writingDate.atZone(ZoneId.systemDefault()).toInstant()),
        entity.description,
        entity.artist?.id!!
    )

    override fun toEntity(dto: PaintingDto) = Painting(
        dto.id,
        dto.title,
        LocalDateTime.ofInstant(dto.dateOfWriting.toInstant(), ZoneId.systemDefault()),
        dto.description
    )
}