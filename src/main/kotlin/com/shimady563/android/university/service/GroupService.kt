package com.shimady563.android.university.service

import com.shimady563.android.exception.ResourceNotFoundException
import com.shimady563.android.university.model.Faculty
import com.shimady563.android.university.model.Group
import com.shimady563.android.university.model.dto.GroupDto
import com.shimady563.android.university.repository.GroupRepository
import com.shimady563.android.university.toGroup
import com.shimady563.android.university.toGroupDto
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class GroupService(
    private val groupRepository: GroupRepository,
    private val facultyService: FacultyService
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Transactional(readOnly = true)
    fun getAllGroups(): List<GroupDto> {
        log.info("Getting all groups")
        return groupRepository.findAll()
            .map { it.toGroupDto() }
    }

    @Transactional(readOnly = true)
    internal fun getGroupById(id: UUID): Group {
        log.info("Getting group with id: $id")
        return groupRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Group with id: $id not found") }
    }

    @Transactional
    fun createGroup(request: GroupDto) {
        log.info("Creating group from request: $request")
        val group = request.toGroup()
        val faculty = facultyService.getFacultyById(request.facultyId)
        group.faculty = faculty
        groupRepository.save(group)
    }

    @Transactional
    fun updateGroup(id: UUID, request: GroupDto) {
        log.info("Updating group with id: $id, request: $request")
        val oldGroup = getGroupById(id)
        oldGroup.name = request.name
        val faculty = facultyService.getFacultyById(request.facultyId)
        oldGroup.faculty = faculty
        groupRepository.save(oldGroup)
    }

    @Transactional(readOnly = true)
    fun getGroupsByFacultyId(facultyId: UUID): List<GroupDto> {
        log.info("Getting groups with faculty id: $facultyId")
        val faculty: Faculty = facultyService.getFacultyById(facultyId)
        return groupRepository.findByFaculty(faculty)
            .map { it.toGroupDto() }
    }

    @Transactional
    fun deleteGroupById(id: UUID) {
        log.info("Deleting group with id: $id")
        groupRepository.deleteById(id)
    }
}
