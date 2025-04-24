package com.shimady563.android.university.repository

import com.shimady563.android.university.model.Faculty
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FacultyRepository : JpaRepository<Faculty, UUID> {
}