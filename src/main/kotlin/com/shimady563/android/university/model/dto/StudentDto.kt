package com.shimady563.android.university.model.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.util.*

data class StudentDto(

    @NotNull(message = "id cannot be null")
    var id: UUID,

    @NotEmpty(message = "last name cannot be empty")
    var lastName: String,

    @NotEmpty(message = "first name cannot be empty")
    var firstName: String,

    @NotEmpty(message = "middle name cannot be empty")
    var middleName: String,

    @NotNull(message = "birth date cannot be null")
    var birthDate: Date,

    @NotEmpty(message = "phone cannot be empty")
    var phone: String,

    @NotNull(message = "gender cannot be null")
    var gender: Int,

    @NotNull(message = "group id cannot be null")
    var groupId: UUID
)
