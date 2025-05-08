package com.shimady563.android.gallery.model.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.util.*

data class PaintingDto(

    @NotNull(message = "id cannot be null")
    val id: UUID,

    @NotEmpty(message = "title cannot be empty")
    val title: String,

    @NotNull(message = "date of writing cannot be null")
    val dateOfWriting: Date,

    @NotEmpty(message = "description cannot be empty")
    val description: String,

    @NotNull(message = "artist id cannot be null")
    val artistId: UUID
)