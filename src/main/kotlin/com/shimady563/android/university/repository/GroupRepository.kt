package com.shimady563.android.university.repository

import com.shimady563.android.university.model.Faculty
import com.shimady563.android.university.model.Group
import com.shimady563.android.university.model.dto.GroupDto
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface GroupRepository : JpaRepository<Group, UUID> {
    fun findByFaculty(faculty: Faculty): List<Group>
}