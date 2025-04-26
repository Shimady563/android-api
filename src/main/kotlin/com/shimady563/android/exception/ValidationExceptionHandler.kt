package com.shimady563.android.exception

import com.shimady563.android.exception.ValidationError.Violation
import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@Slf4j
@RestControllerAdvice
class ValidationExceptionHandler {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(e: ConstraintViolationException): ResponseEntity<ValidationError?> {
        val violations = e.constraintViolations.stream()
            .map<Violation?> { violation: ConstraintViolation<*>? -> this.mapConstraintViolationToViolation(violation!!) }
            .toList()
        log.info("Validation errors: {}", violations)
        return ResponseEntity<ValidationError?>(
            ValidationError(
                violations,
                HttpStatus.BAD_REQUEST.value()
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ValidationError?> {
        val violations = e.bindingResult.fieldErrors
            .stream()
            .map<Violation?> { error: FieldError? -> this.mapFieldErrorToViolation(error!!) }
            .toList()
        log.info("Validation errors: {}", violations)
        return ResponseEntity<ValidationError?>(
            ValidationError(
                violations,
                HttpStatus.BAD_REQUEST.value()
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    private fun mapConstraintViolationToViolation(violation: ConstraintViolation<*>): Violation {
        return Violation(
            violation.propertyPath.toString(),
            violation.message
        )
    }

    private fun mapFieldErrorToViolation(error: FieldError): Violation {
        return Violation(
            error.field,
            error.defaultMessage!!
        )
    }
}