package com.shimady563.android.university.controller

import com.shimady563.android.university.model.dto.GroupDto
import com.shimady563.android.university.service.GroupService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/groups")
class GroupController(private val groupService: GroupService) {

    @GetMapping("")
    fun getAllGroups(): List<GroupDto> {
        return groupService.getAll()
    }

    @PostMapping("")
    fun createGroup(@Valid @RequestBody request: GroupDto) {
        groupService.create(request)
    }

    @PutMapping("/{id}")
    fun updateGroup(@PathVariable id: UUID, @Valid @RequestBody request: GroupDto) {
        return groupService.updateById(id, request)
    }

    @GetMapping("/faculty")
    fun getGroupsByFacultyId(@RequestParam facultyId: UUID): List<GroupDto> {
        return groupService.getByFacultyId(facultyId)
    }

    @DeleteMapping("{id}")
    fun deleteGroup(@PathVariable id: UUID) {
        return groupService.deleteById(id)
    }
}