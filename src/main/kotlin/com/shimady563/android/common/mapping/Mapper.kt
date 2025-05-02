package com.shimady563.android.common.mapping

interface Mapper<E, D> {
    fun toDto(entity: E): D

    fun toEntity(dto: D): E
}