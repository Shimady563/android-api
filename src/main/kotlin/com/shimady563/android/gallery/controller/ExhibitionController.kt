package com.shimady563.android.gallery.controller

import com.shimady563.android.gallery.model.dto.ExhibitionDto
import com.shimady563.android.gallery.service.ExhibitionService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/exhibitions")
class ExhibitionController(private val exhibitionService: ExhibitionService) {

    @GetMapping("")
    fun getAllExhibitions(): List<ExhibitionDto> {
        return exhibitionService.getAllExhibitions()
    }

    @PostMapping("")
    fun createExhibition(@Valid @RequestBody request: ExhibitionDto) {
        exhibitionService.createExhibition(request)
    }

    @PutMapping("/{id}")
    fun updateExhibition(@PathVariable id: UUID, @Valid @RequestBody request: ExhibitionDto) {
        return exhibitionService.updateExhibition(id, request)
    }

    @DeleteMapping("{id}")
    fun deleteExhibition(@PathVariable id: UUID) {
        return exhibitionService.deleteExhibitionById(id)
    }
}