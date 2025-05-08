package com.shimady563.android.gallery.model.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.util.UUID

data class ExhibitionDto(

    @NotNull(message = "id cannot be null")
    val id: UUID,

    @NotEmpty(message = "title cannot be empty")
    val title: String
)