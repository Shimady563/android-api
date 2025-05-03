package com.shimady563.android.gallery.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "artist")
class Artist(
    @Id
    var id: UUID? = null,

    @Column(name = "name", nullable = false)
    var name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_id")
    var exhibition: Exhibition? = null
)