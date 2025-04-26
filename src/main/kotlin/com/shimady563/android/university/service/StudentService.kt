package com.shimady563.android.university.service

import com.shimady563.android.university.exception.ResourceNotFoundException
import com.shimady563.android.university.model.Group
import com.shimady563.android.university.model.Student
import com.shimady563.android.university.model.dto.StudentDto
import com.shimady563.android.university.repository.StudentRepository
import com.shimady563.android.university.toStudent
import com.shimady563.android.university.toStudentDto
import org.springframework.transaction.annotation.Transactional
import java.util.*

class StudentService(
    private val studentRepository: StudentRepository,
    private val groupService: GroupService
) {

    @Transactional(readOnly = true)
    fun getAllStudents(): List<StudentDto> {
        return studentRepository.findAll()
            .map { it.toStudentDto() }
    }

    @Transactional(readOnly = true)
    internal fun getStudentById(id: UUID): Student {
        return studentRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Student with id: $id not found") }
    }

    @Transactional
    fun createStudent(request: StudentDto) {
        val student = request.toStudent()
        val group = groupService.getGroupById(request.groupId)
        student.group = group
        studentRepository.save(student)
    }

    @Transactional
    fun updateStudent(id: UUID, request: StudentDto) {
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
        val group: Group = groupService.getGroupById(groupId)
        return studentRepository.findByGroup(group)
            .map { it.toStudentDto() }
    }
}
