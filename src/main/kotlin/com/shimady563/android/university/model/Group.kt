package com.shimady563.android.university.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "app_group")
class Group(
    @Id
    var id: UUID? = null,

    @Column(name = "name", nullable = false)
    var name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    var faculty: Faculty? = null,

    @OneToMany(
        targetEntity = Student::class,
        fetch = FetchType.LAZY,
        mappedBy = "group",
        cascade = [CascadeType.ALL]
    )
    var students: List<Student> = emptyList()
)
