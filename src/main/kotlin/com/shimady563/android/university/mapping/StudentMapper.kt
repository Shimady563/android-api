package com.shimady563.android.university.mapping

import com.shimady563.android.common.mapping.Mapper
import com.shimady563.android.university.model.Student
import com.shimady563.android.university.model.dto.StudentDto
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Component
class StudentMapper : Mapper<Student, StudentDto> {
    override fun toDto(entity: Student) = StudentDto(
        entity.id!!,
        entity.lastName,
        entity.firstName,
        entity.middleName,
        Date.from(entity.birthDate.atZone(ZoneId.systemDefault()).toInstant()),
        entity.phone,
        entity.gender,
        entity.group?.id!!
    )

    override fun toEntity(dto: StudentDto) = Student(
        dto.id,
        dto.lastName,
        dto.firstName,
        dto.middleName,
        LocalDateTime.ofInstant(dto.birthDate.toInstant(), ZoneId.systemDefault()),
        dto.phone,
        dto.gender
    )
}