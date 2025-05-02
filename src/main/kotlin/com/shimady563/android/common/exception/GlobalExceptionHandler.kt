package com.shimady563.android.common.exception

import org.slf4j.LoggerFactory
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(e: ResourceNotFoundException): ResponseEntity<AppError?> {
        log.error(e.message)
        return ResponseEntity<AppError?>(
            AppError(e.message ?: "", HttpStatus.NOT_FOUND.value()),
            HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(DataAccessException::class)
    fun handleDataAccessException(e: DataAccessException): ResponseEntity<AppError?> {
        log.error(e.message)
        return ResponseEntity<AppError?>(
            AppError(parseDataAccessExceptionMessage(e.message!!), HttpStatus.CONFLICT.value()),
            HttpStatus.CONFLICT
        )
    }

    private fun parseDataAccessExceptionMessage(message: String): String {
        if (message.contains("Detail:")) {
            return message.split("Detail:".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()[1].trim { it <= ' ' }
        }
        return message
    }
}