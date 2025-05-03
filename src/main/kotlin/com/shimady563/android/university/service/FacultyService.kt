package com.shimady563.android.university.service

import com.shimady563.android.common.mapping.Mapper
import com.shimady563.android.common.service.AbstractService
import com.shimady563.android.university.model.Faculty
import com.shimady563.android.university.model.dto.FacultyDto
import com.shimady563.android.university.repository.FacultyRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class FacultyService(
    facultyRepository: FacultyRepository,
    mapper: Mapper<Faculty, FacultyDto>
) : AbstractService<Faculty, FacultyDto>(
    facultyRepository,
    mapper
) {

    @Transactional
    override fun updateById(id: UUID, request: FacultyDto) {
        log.info("Updating faculty with id: $id, request: $request")
        val oldFaculty = getById(id)
        oldFaculty.name = request.name
        repository.save(oldFaculty)
    }
}