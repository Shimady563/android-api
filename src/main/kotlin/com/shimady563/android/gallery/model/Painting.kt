package com.shimady563.android.gallery.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "painting")
class Painting(
    @Id
    var id: UUID? = null,

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "writing_date", nullable = false)
    var writingDate: LocalDateTime,

    @Column(name = "description", nullable = false)
    var description: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    var artist: Artist? = null,
)