package com.shimady563.android.gallery.repository

import com.shimady563.android.gallery.model.Artist
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ArtistRepository : JpaRepository<Artist, UUID> {
}