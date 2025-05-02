package com.shimady563.android.gallery.controller

import com.shimady563.android.gallery.model.dto.PaintingDto
import com.shimady563.android.gallery.service.PaintingService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/paintings")
class PaintingController(private val paintingService: PaintingService) {

    @GetMapping("")
    fun getAllPaintings(): List<PaintingDto> {
        return paintingService.getAll()
    }

    @PostMapping("")
    fun createPainting(@Valid @RequestBody request: PaintingDto) {
        paintingService.createPainting(request)
    }

    @PutMapping("/{id}")
    fun updatePainting(@PathVariable id: UUID, @Valid @RequestBody request: PaintingDto) {
        return paintingService.updateById(id, request)
    }

    @DeleteMapping("{id}")
    fun deletePainting(@PathVariable id: UUID) {
        return paintingService.deleteById(id)
    }
}