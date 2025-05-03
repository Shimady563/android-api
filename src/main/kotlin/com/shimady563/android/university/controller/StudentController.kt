package com.shimady563.android.university.controller

import com.shimady563.android.university.model.dto.StudentDto
import com.shimady563.android.university.service.StudentService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/students")
class StudentController(private val studentService: StudentService) {

    @GetMapping("")
    fun getAllStudents(): List<StudentDto> {
        return studentService.getAll()
    }

    @PostMapping("")
    fun createStudent(@Valid @RequestBody request: StudentDto) {
        studentService.create(request)
    }

    @PutMapping("/{id}")
    fun updateStudent(@PathVariable id: UUID, @Valid @RequestBody request: StudentDto) {
        return studentService.updateById(id, request)
    }

    @GetMapping("/group")
    fun getStudentsByGroupId(@RequestParam groupId: UUID): List<StudentDto> {
        return studentService.getByGroupId(groupId)
    }

    @DeleteMapping("{id}")
    fun deleteStudent(@PathVariable id: UUID) {
        return studentService.deleteById(id)
    }
}