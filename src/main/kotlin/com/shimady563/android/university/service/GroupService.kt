package com.shimady563.android.university.service

import com.shimady563.android.common.mapping.Mapper
import com.shimady563.android.common.service.AbstractService
import com.shimady563.android.university.model.Faculty
import com.shimady563.android.university.model.Group
import com.shimady563.android.university.model.dto.GroupDto
import com.shimady563.android.university.repository.GroupRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class GroupService(
    groupRepository: GroupRepository,
    mapper: Mapper<Group, GroupDto>,
    private val facultyService: FacultyService
) : AbstractService<GroupDto, Group, GroupRepository>(
    groupRepository,
    mapper
) {

    @Transactional
    override fun create(request: GroupDto) {
        log.info("Creating group from request: $request")
        val group = mapper.toEntity(request)
        val faculty = facultyService.getById(request.facultyId)
        group.faculty = faculty
        repository.save(group)
    }

    @Transactional
    override fun updateById(id: UUID, request: GroupDto) {
        log.info("Updating group with id: $id, request: $request")
        val oldGroup = getById(id)
        oldGroup.name = request.name
        val faculty = facultyService.getById(request.facultyId)
        oldGroup.faculty = faculty
        repository.save(oldGroup)
    }

    @Transactional(readOnly = true)
    fun getByFacultyId(facultyId: UUID): List<GroupDto> {
        log.info("Getting groups with faculty id: $facultyId")
        val faculty: Faculty = facultyService.getById(facultyId)
        return repository.findByFaculty(faculty)
            .map { mapper.toDto(it) }
    }
}
