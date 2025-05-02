package com.shimady563.android.gallery.repository

import com.shimady563.android.gallery.model.Exhibition
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ExhibitionRepository : JpaRepository<Exhibition, UUID> {
}