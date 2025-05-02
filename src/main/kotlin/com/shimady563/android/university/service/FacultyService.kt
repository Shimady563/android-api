package com.shimady563.android.university.service

import com.shimady563.android.exception.ResourceNotFoundException
import com.shimady563.android.university.model.Faculty
import com.shimady563.android.university.model.dto.FacultyDto
import com.shimady563.android.university.repository.FacultyRepository
import com.shimady563.android.university.toFaculty
import com.shimady563.android.university.toFacultyDto
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class FacultyService(private val facultyRepository: FacultyRepository) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Transactional(readOnly = true)
    fun getAllFaculties(): List<FacultyDto> {
        log.info("Getting all faculties")
        return facultyRepository.findAll()
            .map { it.toFacultyDto() }
    }

    @Transactional(readOnly = true)
    internal fun getFacultyById(id: UUID): Faculty {
        log.info("Getting faculty with id: $id")
        return facultyRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Faculty with id: $id not found") }
    }

    @Transactional
    fun createFaculty(request: FacultyDto) {
        log.info("Creating faculty from request: $request")
        val faculty = request.toFaculty()
        facultyRepository.save(faculty)
    }

    @Transactional
    fun updateFaculty(id: UUID, request: FacultyDto) {
        log.info("Updating faculty with id: $id, request: $request")
        val oldFaculty = getFacultyById(id)
        oldFaculty.name = request.name
        facultyRepository.save(oldFaculty)
    }

    @Transactional
    fun deleteFacultyById(id: UUID) {
        log.info("Deleting faculty with id: $id")
        facultyRepository.deleteById(id)
    }
}