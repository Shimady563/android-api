package com.shimady563.android.gallery.service

import com.shimady563.android.exception.ResourceNotFoundException
import com.shimady563.android.gallery.model.Painting
import com.shimady563.android.gallery.model.dto.PaintingDto
import com.shimady563.android.gallery.repository.PaintingRepository
import com.shimady563.android.gallery.toPainting
import com.shimady563.android.gallery.toPaintingDto
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class PaintingService(
    private val paintingRepository: PaintingRepository,
    private val artistService: ArtistService
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Transactional(readOnly = true)
    fun getAllPaintings(): List<PaintingDto> {
        log.info("Getting all paintings")
        return paintingRepository.findAll()
            .map { it.toPaintingDto() }
    }

    @Transactional(readOnly = true)
    internal fun getPaintingById(id: UUID): Painting {
        log.info("Getting painting with id: $id")
        return paintingRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Painting with id: $id not found") }
    }

    @Transactional
    fun createPainting(request: PaintingDto) {
        log.info("Creating painting from request: $request")
        val painting = request.toPainting()
        val artist = artistService.getArtistById(request.artistId)
        painting.artist = artist
        paintingRepository.save(painting)
    }

    @Transactional
    fun updatePainting(id: UUID, request: PaintingDto) {
        log.info("Updating painting with id: $id, request: $request")
        val oldPainting = getPaintingById(id)
        oldPainting.title = request.title
        oldPainting.writingDate = LocalDateTime.ofInstant(request.dateOfWriting.toInstant(), ZoneId.systemDefault())
        oldPainting.description = request.description
        val artist = artistService.getArtistById(request.artistId)
        oldPainting.artist = artist
        paintingRepository.save(oldPainting)
    }

    @Transactional
    fun deletePaintingById(id: UUID) {
        log.info("Deleting painting with id: $id")
        paintingRepository.deleteById(id)
    }
}