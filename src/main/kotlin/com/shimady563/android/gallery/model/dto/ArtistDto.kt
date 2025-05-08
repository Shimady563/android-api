package com.shimady563.android.gallery.model.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.util.*

data class ArtistDto(

    @NotNull(message = "id cannot be null")
    val id: UUID,

    @NotEmpty(message = "name cannot be empty")
    val name: String,

    @NotNull(message = "exhibition id cannot be null")
    val exhibitionId: UUID
)