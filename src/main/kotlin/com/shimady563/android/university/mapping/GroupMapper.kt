package com.shimady563.android.university.mapping

import com.shimady563.android.common.mapping.Mapper
import com.shimady563.android.university.model.Group
import com.shimady563.android.university.model.dto.GroupDto
import org.springframework.stereotype.Component

@Component
class GroupMapper : Mapper<Group, GroupDto> {
    override fun toDto(entity: Group) = GroupDto(
        entity.id!!,
        entity.name,
        entity.faculty?.id!!
    )

    override fun toEntity(dto: GroupDto) = Group(
        dto.id,
        dto.name
    )
}