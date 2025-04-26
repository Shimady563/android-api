package com.shimady563.android.university.service

import com.shimady563.android.exception.ResourceNotFoundException
import com.shimady563.android.university.model.Group
import com.shimady563.android.university.model.Student
import com.shimady563.android.university.model.dto.StudentDto
import com.shimady563.android.university.repository.StudentRepository
import com.shimady563.android.university.toStudent
import com.shimady563.android.university.toStudentDto
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class StudentService(
    private val studentRepository: StudentRepository,
    private val groupService: GroupService
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Transactional(readOnly = true)
    fun getAllStudents(): List<StudentDto> {
        log.info("Getting all students")
        return studentRepository.findAll()
            .map { it.toStudentDto() }
    }

    @Transactional(readOnly = true)
    internal fun getStudentById(id: UUID): Student {
        log.info("Getting student with id: $id")
        return studentRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Student with id: $id not found") }
    }

    @Transactional
    fun createStudent(request: StudentDto) {
        log.info("Creating student from request: $request")
        val student = request.toStudent()
        val group = groupService.getGroupById(request.groupId)
        student.group = group
        studentRepository.save(student)
    }

    @Transactional
    fun updateStudent(id: UUID, request: StudentDto) {
        log.info("Updating student with id: $id, request: $request")
        val oldStudent = getStudentById(id)
        oldStudent.firstName = request.firstName
        oldStudent.lastName = request.lastName
        oldStudent.middleName = request.middleName
        oldStudent.birthDate = request.birthDate
        oldStudent.gender = request.gender
        oldStudent.phone = request.phone
        val group = groupService.getGroupById(request.groupId)
        oldStudent.group = group
        studentRepository.save(oldStudent)
    }

    @Transactional(readOnly = true)
    fun getStudentsByGroupId(groupId: UUID): List<StudentDto> {
        log.info("Getting students with group id: $groupId")
        val group: Group = groupService.getGroupById(groupId)
        return studentRepository.findByGroup(group)
            .map { it.toStudentDto() }
    }
}
