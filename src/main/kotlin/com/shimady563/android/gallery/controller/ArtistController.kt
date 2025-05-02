package com.shimady563.android.gallery.controller

import com.shimady563.android.gallery.model.dto.ArtistDto
import com.shimady563.android.gallery.service.ArtistService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/artists")
class ArtistController(private val artistService: ArtistService) {

    @GetMapping("")
    fun getAllArtists(): List<ArtistDto> {
        return artistService.getAllArtists()
    }

    @PostMapping("")
    fun createArtist(@Valid @RequestBody request: ArtistDto) {
        artistService.createArtist(request)
    }

    @PutMapping("/{id}")
    fun updateArtist(@PathVariable id: UUID, @Valid @RequestBody request: ArtistDto) {
        return artistService.updateArtist(id, request)
    }

    @DeleteMapping("{id}")
    fun deleteArtist(@PathVariable id: UUID) {
        return artistService.deleteArtistById(id)
    }
}