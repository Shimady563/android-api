package com.shimady563.android.university.model.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.util.*

data class FacultyDto(

    @NotNull(message = "id cannot be null")
    var id: UUID,

    @NotEmpty(message = "name cannot be empty")
    var name: String
)
