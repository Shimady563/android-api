package com.shimady563.android.gallery.mapping

import com.shimady563.android.common.mapping.Mapper
import com.shimady563.android.gallery.model.Exhibition
import com.shimady563.android.gallery.model.dto.ExhibitionDto
import org.springframework.stereotype.Component

@Component
class ExhibitionMapper : Mapper<Exhibition, ExhibitionDto> {
    override fun toDto(entity: Exhibition) = ExhibitionDto(
        entity.id!!,
        entity.title
    )

    override fun toEntity(dto: ExhibitionDto) = Exhibition(
        dto.id,
        dto.title
    )
}
