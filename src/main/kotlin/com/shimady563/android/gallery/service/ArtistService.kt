package com.shimady563.android.gallery.service

import com.shimady563.android.common.mapping.Mapper
import com.shimady563.android.common.service.AbstractService
import com.shimady563.android.gallery.model.Artist
import com.shimady563.android.gallery.model.dto.ArtistDto
import com.shimady563.android.gallery.repository.ArtistRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ArtistService(
    artistRepository: ArtistRepository,
    mapper: Mapper<Artist, ArtistDto>,
    private val exhibitionService: ExhibitionService
) : AbstractService<Artist, ArtistDto>(
    artistRepository,
    mapper
) {

    @Transactional
    override fun create(request: ArtistDto) {
        log.info("Creating artist from request: $request")
        val artist = mapper.toEntity(request)
        val exhibition = exhibitionService.getById(request.exhibitionId)
        artist.exhibition = exhibition
        repository.save(artist)
    }

    @Transactional
    override fun updateById(id: UUID, request: ArtistDto) {
        log.info("Updating artist with id: $id, request: $request")
        val oldArtist = getById(id)
        oldArtist.name = request.name
        val exhibition = exhibitionService.getById(request.exhibitionId)
        oldArtist.exhibition = exhibition
        repository.save(oldArtist)
    }
}