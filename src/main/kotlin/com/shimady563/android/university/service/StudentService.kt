package com.shimady563.android.university.service

import com.shimady563.android.common.mapping.Mapper
import com.shimady563.android.common.service.AbstractService
import com.shimady563.android.university.model.Group
import com.shimady563.android.university.model.Student
import com.shimady563.android.university.model.dto.StudentDto
import com.shimady563.android.university.repository.StudentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class StudentService(
    studentRepository: StudentRepository,
    mapper: Mapper<Student, StudentDto>,
    private val groupService: GroupService
) : AbstractService<StudentDto, Student, StudentRepository>(
    studentRepository,
    mapper
) {

    @Transactional
    override fun create(request: StudentDto) {
        log.info("Creating student from request: $request")
        val student = mapper.toEntity(request)
        val group = groupService.getById(request.groupId)
        student.group = group
        repository.save(student)
    }

    @Transactional
    override fun updateById(id: UUID, request: StudentDto) {
        log.info("Updating student with id: $id, request: $request")
        val oldStudent = getById(id)
        oldStudent.firstName = request.firstName
        oldStudent.lastName = request.lastName
        oldStudent.middleName = request.middleName
        oldStudent.birthDate = LocalDateTime.ofInstant(request.birthDate.toInstant(), ZoneId.systemDefault())
        oldStudent.gender = request.gender
        oldStudent.phone = request.phone
        val group = groupService.getById(request.groupId)
        oldStudent.group = group
        repository.save(oldStudent)
    }

    @Transactional(readOnly = true)
    fun getByGroupId(groupId: UUID): List<StudentDto> {
        log.info("Getting students with group id: $groupId")
        val group: Group = groupService.getById(groupId)
        return repository.findByGroup(group)
            .map { mapper.toDto(it) }
    }
}
