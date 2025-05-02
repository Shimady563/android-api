package com.shimady563.android.gallery

import com.shimady563.android.gallery.model.Artist
import com.shimady563.android.gallery.model.Exhibition
import com.shimady563.android.gallery.model.Painting
import com.shimady563.android.gallery.model.dto.ArtistDto
import com.shimady563.android.gallery.model.dto.ExhibitionDto
import com.shimady563.android.gallery.model.dto.PaintingDto
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

fun ArtistDto.toArtist() = Artist(
    id,
    name
)

fun Artist.toArtistDto() = ArtistDto(
    id!!,
    name,
    exhibition?.id!!
)

fun Exhibition.toExhibitionDto() = ExhibitionDto(
    id!!,
    title
)

fun ExhibitionDto.toExhibition() = Exhibition(
    id,
    title
)

fun Painting.toPaintingDto() = PaintingDto(
    id!!,
    title,
    Date.from(writingDate.atZone(ZoneId.systemDefault()).toInstant()),
    description,
    artist?.id!!
)

fun PaintingDto.toPainting() = Painting(
    id,
    title,
    LocalDateTime.ofInstant(dateOfWriting.toInstant(), ZoneId.systemDefault()),
    description
)