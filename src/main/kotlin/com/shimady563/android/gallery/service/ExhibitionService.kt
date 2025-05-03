package com.shimady563.android.gallery.service

import com.shimady563.android.common.mapping.Mapper
import com.shimady563.android.common.service.AbstractService
import com.shimady563.android.gallery.model.Exhibition
import com.shimady563.android.gallery.model.dto.ExhibitionDto
import com.shimady563.android.gallery.repository.ExhibitionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ExhibitionService(
    exhibitionRepository: ExhibitionRepository,
    mapper: Mapper<Exhibition, ExhibitionDto>
) : AbstractService<Exhibition, ExhibitionDto>(
    exhibitionRepository,
    mapper
) {

    @Transactional
    override fun updateById(id: UUID, request: ExhibitionDto) {
        log.info("Updating exhibition with id: $id, request: $request")
        val oldExhibition = getById(id)
        oldExhibition.title = request.title
        repository.save(oldExhibition)
    }
}
