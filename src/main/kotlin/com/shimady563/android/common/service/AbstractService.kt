package com.shimady563.android.common.service

import com.shimady563.android.common.exception.ResourceNotFoundException
import com.shimady563.android.common.mapping.Mapper
import org.slf4j.LoggerFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@org.springframework.stereotype.Service
abstract class AbstractService<D, E : Any, ER : JpaRepository<E, UUID>>(
    internal val repository: ER,
    internal val mapper: Mapper<E, D>
) : Service<D> {
    internal val log = LoggerFactory.getLogger(this.javaClass)

    @Transactional(readOnly = true)
    internal fun getById(id: UUID): E {
        log.info("Getting entity with id: $id")
        return repository.findById(id)
            .orElseThrow { ResourceNotFoundException("Entity with id: $id not found") }
    }

    @Transactional
    override fun create(request: D) {
        log.info("Creating entity from request: $request")
        val entity = mapper.toEntity(request)
        repository.save(entity)
    }

    @Transactional
    override fun getAll(): List<D> {
        return repository.findAll().map { mapper.toDto(it) }
    }

    @Transactional
    override fun deleteById(id: UUID) {
        log.info("Deleting entity with id: $id")
        repository.deleteById(id)
    }
}