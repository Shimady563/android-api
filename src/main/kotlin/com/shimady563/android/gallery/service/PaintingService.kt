package com.shimady563.android.gallery.service

import com.shimady563.android.common.mapping.Mapper
import com.shimady563.android.common.service.AbstractService
import com.shimady563.android.gallery.model.Painting
import com.shimady563.android.gallery.model.dto.PaintingDto
import com.shimady563.android.gallery.repository.PaintingRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class PaintingService(
    paintingRepository: PaintingRepository,
    mapper: Mapper<Painting, PaintingDto>,
    private val artistService: ArtistService
) : AbstractService<Painting, PaintingDto>(
    paintingRepository,
    mapper
) {

    @Transactional
    override fun create(request: PaintingDto) {
        log.info("Creating painting from request: $request")
        val painting = mapper.toEntity(request)
        val artist = artistService.getById(request.artistId)
        painting.artist = artist
        repository.save(painting)
    }

    @Transactional
    override fun updateById(id: UUID, request: PaintingDto) {
        log.info("Updating painting with id: $id, request: $request")
        val oldPainting = getById(id)
        oldPainting.title = request.title
        oldPainting.writingDate = LocalDateTime.ofInstant(request.dateOfWriting.toInstant(), ZoneId.systemDefault())
        oldPainting.description = request.description
        val artist = artistService.getById(request.artistId)
        oldPainting.artist = artist
        repository.save(oldPainting)
    }
}