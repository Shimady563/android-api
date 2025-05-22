package com.shimady563.android.university.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "faculty")
class Faculty(
    @Id
    var id: UUID? = null,

    @Column(name = "name", nullable = false)
    var name: String,

    @OneToMany(
        targetEntity = Group::class,
        fetch = FetchType.LAZY,
        mappedBy = "faculty",
        cascade = [CascadeType.ALL]
    )
    var groups: List<Group> = emptyList()
)
