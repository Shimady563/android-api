package com.shimady563.android.university.model.dto

import java.time.LocalDateTime
import java.util.*

data class StudentDto(
    var id: UUID,
    var lastName: String,
    var firstName: String,
    var middleName: String,
    var birthDate: LocalDateTime,
    var phone: String,
    var gender: Int,
    var groupId: UUID
)
