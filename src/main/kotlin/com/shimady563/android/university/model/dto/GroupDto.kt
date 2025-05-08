package com.shimady563.android.university.model.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.util.*

data class GroupDto(

    @NotNull(message = "id cannot be null")
    var id: UUID,

    @NotEmpty(message = "name cannot be empty")
    var name: String,

    @NotNull(message = "faculty id cannot be null")
    var facultyId: UUID
)
