package com.shimady563.android.university.service

import com.shimady563.android.university.exception.ResourceNotFoundException
import com.shimady563.android.university.model.Faculty
import com.shimady563.android.university.model.Group
import com.shimady563.android.university.model.dto.GroupDto
import com.shimady563.android.university.repository.GroupRepository
import com.shimady563.android.university.toGroup
import com.shimady563.android.university.toGroupDto
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Slf4j
@Service
class GroupService(
    private val groupRepository: GroupRepository,
    private val facultyService: FacultyService
) {

    @Transactional(readOnly = true)
    fun getAllGroups(): List<GroupDto> {
        return groupRepository.findAll()
            .map { it.toGroupDto() }
    }

    @Transactional(readOnly = true)
    internal fun getGroupById(id: UUID): Group {
        return groupRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Group with id: $id not found") }
    }

    @Transactional
    fun createGroup(request: GroupDto) {
        val group = request.toGroup()
        val faculty = facultyService.getFacultyById(request.facultyId)
        group.faculty = faculty
        groupRepository.save(group)
    }

    @Transactional
    fun updateGroup(id: UUID, request: GroupDto) {
        val oldGroup = getGroupById(id)
        oldGroup.name = request.name
        val faculty = facultyService.getFacultyById(request.facultyId)
        oldGroup.faculty = faculty
        groupRepository.save(oldGroup)
    }

    @Transactional(readOnly = true)
    fun getGroupByFacultyId(facultyId: UUID): List<GroupDto> {
        val faculty: Faculty = facultyService.getFacultyById(facultyId)
        return groupRepository.findByFaculty(faculty)
            .map { it.toGroupDto() }
    }
}
