package com.shimady563.android.university

import com.shimady563.android.university.model.Faculty
import com.shimady563.android.university.model.Group
import com.shimady563.android.university.model.Student
import com.shimady563.android.university.model.dto.FacultyDto
import com.shimady563.android.university.model.dto.GroupDto
import com.shimady563.android.university.model.dto.StudentDto

fun StudentDto.toStudent() = Student(
    id,
    lastName,
    firstName,
    middleName,
    birthDate,
    phone,
    gender
)

fun Student.toStudentDto() = StudentDto(
    id!!,
    lastName,
    firstName,
    middleName,
    birthDate,
    phone,
    gender,
    group?.id!!
)

fun Group.toGroupDto() = GroupDto(
    id!!,
    name,
    faculty?.id!!
)

fun GroupDto.toGroup() = Group(
    id,
    name
)

fun Faculty.toFacultyDto() = FacultyDto(
    id!!,
    name
)

fun FacultyDto.toFaculty() = Faculty(
    id,
    name
)