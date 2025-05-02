package com.shimady563.android.common.service

import java.util.*

interface Service<D> {

    fun create(request: D)

    fun updateById(id: UUID, request: D)

    fun getAll(): List<D>

    fun deleteById(id: UUID)
}