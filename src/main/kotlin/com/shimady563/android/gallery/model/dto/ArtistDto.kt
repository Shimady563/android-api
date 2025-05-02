package com.shimady563.android.gallery.model.dto

import java.util.UUID

data class ArtistDto(
    val id: UUID,
    val name: String,
    val exhibitionId: UUID
)