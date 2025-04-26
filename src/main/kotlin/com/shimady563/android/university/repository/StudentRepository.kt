package com.shimady563.android.university.repository

import com.shimady563.android.university.model.Group
import com.shimady563.android.university.model.Student
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface StudentRepository : JpaRepository<Student, UUID> {
    fun findByGroup(group: Group): List<Student>
}