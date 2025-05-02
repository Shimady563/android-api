package com.shimady563.android.gallery.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "exhibition")
class Exhibition(
    @Id
    var id: UUID? = null,

    @Column(name = "title", nullable = false)
    var title: String
)