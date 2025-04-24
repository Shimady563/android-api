package com.shimady563.android.university.service

import com.shimady563.android.university.exception.ResourceNotFoundException
import com.shimady563.android.university.model.Faculty
import com.shimady563.android.university.model.dto.FacultyDto
import com.shimady563.android.university.repository.FacultyRepository
import com.shimady563.android.university.toFaculty
import com.shimady563.android.university.toFacultyDto
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Slf4j
@Service
class FacultyService(private val facultyRepository: FacultyRepository) {

    @Transactional(readOnly = true)
    fun getAllFaculties(): List<FacultyDto> {
        return facultyRepository.findAll()
            .map { it.toFacultyDto() }
    }

    @Transactional(readOnly = true)
    protected fun getFacultyById(id: UUID): Faculty {
        return facultyRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Faculty with id: $id not found") }
    }

    @Transactional
    fun createFaculty(request: FacultyDto) {
        val faculty = request.toFaculty()
        facultyRepository.save(faculty)
    }

    @Transactional
    fun updateFaculty(id: UUID, request: FacultyDto) {
        val oldFaculty = getFacultyById(id)
        oldFaculty.name = request.name
        facultyRepository.save(oldFaculty)
    }
}