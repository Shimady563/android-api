package com.shimady563.android.gallery.repository

import com.shimady563.android.gallery.model.Painting
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PaintingRepository : JpaRepository<Painting, UUID> {
}