package com.shimady563.android.gallery.model.dto

import java.time.LocalDateTime
import java.util.*

data class PaintingDto(
    val id: UUID,
    val title: String,
    val dateOfWriting: Date,
    val description: String,
    val artistId: UUID,
)