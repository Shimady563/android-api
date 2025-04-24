package com.shimady563.android.university.controller

import com.shimady563.android.university.model.dto.FacultyDto
import com.shimady563.android.university.service.FacultyService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/faculties")
class FacultyController(private val facultyService: FacultyService) {

    @GetMapping("")
    fun getAllFaculties(): List<FacultyDto> {
        return facultyService.getAllFaculties()
    }

    @PostMapping("")
    fun createFaculty(@Valid @RequestBody request: FacultyDto) {
        facultyService.createFaculty(request)
    }

    @PutMapping("/{id}")
    fun updateFaculty(@PathVariable id: UUID, @Valid @RequestBody request: FacultyDto) {
        return facultyService.updateFaculty(id, request)
    }
}