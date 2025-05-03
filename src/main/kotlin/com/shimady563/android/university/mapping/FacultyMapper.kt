package com.shimady563.android.university.mapping

import com.shimady563.android.common.mapping.Mapper
import com.shimady563.android.university.model.Faculty
import com.shimady563.android.university.model.dto.FacultyDto
import org.springframework.stereotype.Component

@Component
class FacultyMapper : Mapper<Faculty, FacultyDto> {
    override fun toDto(entity: Faculty) = FacultyDto(
        entity.id!!,
        entity.name
    )

    override fun toEntity(dto: FacultyDto) = Faculty(
        dto.id,
        dto.name
    )
}