package com.shimady563.android.gallery.service

import com.shimady563.android.exception.ResourceNotFoundException
import com.shimady563.android.gallery.model.Exhibition
import com.shimady563.android.gallery.model.dto.ExhibitionDto
import com.shimady563.android.gallery.repository.ExhibitionRepository
import com.shimady563.android.gallery.toExhibition
import com.shimady563.android.gallery.toExhibitionDto
import org.slf4j.LoggerFactory
import org.springframework.transaction.annotation.Transactional
import java.util.*

class ExhibitionService(private val exhibitionRepository: ExhibitionRepository) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Transactional(readOnly = true)
    fun getAllExhibitions(): List<ExhibitionDto> {
        log.info("Getting all exhibitions")
        return exhibitionRepository.findAll()
            .map { it.toExhibitionDto() }
    }

    @Transactional(readOnly = true)
    internal fun getExhibitionById(id: UUID): Exhibition {
        log.info("Getting exhibition with id: $id")
        return exhibitionRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Exhibition with id: $id not found") }
    }

    @Transactional
    fun createExhibition(request: ExhibitionDto) {
        log.info("Creating exhibition from request: $request")
        val painting = request.toExhibition()
        exhibitionRepository.save(painting)
    }

    @Transactional
    fun updateExhibition(id: UUID, request: ExhibitionDto) {
        log.info("Updating exhibition with id: $id, request: $request")
        val oldExhibition = getExhibitionById(id)
        oldExhibition.title = request.title
        exhibitionRepository.save(oldExhibition)
    }

    @Transactional
    fun deleteExhibitionById(id: UUID) {
        log.info("Deleting exhibition with id: $id")
        exhibitionRepository.deleteById(id)
    }
}
