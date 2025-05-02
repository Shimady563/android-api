package com.shimady563.android.gallery.service

import com.shimady563.android.exception.ResourceNotFoundException
import com.shimady563.android.gallery.model.Artist
import com.shimady563.android.gallery.model.dto.ArtistDto
import com.shimady563.android.gallery.repository.ArtistRepository
import com.shimady563.android.gallery.toArtist
import com.shimady563.android.gallery.toArtistDto
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ArtistService(
    private val artistRepository: ArtistRepository,
    private val exhibitionService: ExhibitionService
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Transactional(readOnly = true)
    fun getAllArtists(): List<ArtistDto> {
        log.info("Getting all artists")
        return artistRepository.findAll()
            .map { it.toArtistDto() }
    }

    @Transactional(readOnly = true)
    internal fun getArtistById(id: UUID): Artist {
        log.info("Getting artist with id: $id")
        return artistRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Artist with id: $id not found") }
    }

    @Transactional
    fun createArtist(request: ArtistDto) {
        log.info("Creating artist from request: $request")
        val artist = request.toArtist()
        val exhibition = exhibitionService.getExhibitionById(request.exhibitionId)
        artist.exhibition = exhibition
        artistRepository.save(artist)
    }

    @Transactional
    fun updateArtist(id: UUID, request: ArtistDto) {
        log.info("Updating artist with id: $id, request: $request")
        val oldArtist = getArtistById(id)
        oldArtist.name = request.name
        val exhibition = exhibitionService.getExhibitionById(request.exhibitionId)
        oldArtist.exhibition = exhibition
        artistRepository.save(oldArtist)
    }

    @Transactional
    fun deleteArtistById(id: UUID) {
        log.info("Deleting artist with id: $id")
        artistRepository.deleteById(id)
    }
}