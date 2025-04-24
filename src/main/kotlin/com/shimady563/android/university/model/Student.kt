package com.shimady563.android.university.model

import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import java.time.LocalDateTime
import java.util.*

@Getter
@Setter
@Entity
@Table(name = "student")
class Student(
    @Id
    var id: UUID? = null,

    @Column(name = "last_name", nullable = false)
    var lastName: String,

    @Column(name = "first_name", nullable = false)
    var firstName: String,

    @Column(name = "middle_name", nullable = false)
    var middleName: String,

    @Column(name = "birth_date", nullable = false)
    var birthDate: LocalDateTime,

    @Column(name = "phone", nullable = false)
    var phone: String,

    @Column(name = "gender", nullable = false)
    var gender: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    var group: Group? = null
)
