package com.example.chocokcakeV2.global.error

import com.example.chocokcakeV2.global.error.exception.BusinessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.multipart.MaxUploadSizeExceededException
import org.springframework.web.multipart.MultipartException
import javax.validation.ConstraintViolationException
import javax.validation.ValidationException

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(BusinessException::class)
    fun globalExceptionHandler(error: BusinessException): ResponseEntity<*> {
        return ResponseEntity.status(error.errorCode.status).body(
            ErrorResponse(
                error.errorCode.message,
                error.data
            )
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidExceptionHandler(e: MethodArgumentNotValidException): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse(
                e.bindingResult.allErrors
                    .map {
                        it.defaultMessage.toString()
                    }.toString(),
                e.bindingResult.allErrors
                    .map {
                        it.arguments
                    }.toString()
            )
        )
    }

    @ExceptionHandler(ValidationException::class)
    fun validationExceptionHandler(e: ConstraintViolationException): ResponseEntity<*> {
        return ResponseEntity.badRequest().body(
            ErrorResponse(
                e.message.toString(),
                e.stackTrace.toString()
            )
        )
    }

    @ExceptionHandler(MultipartException::class)
    fun maxUploadSizeExceededException(e: MaxUploadSizeExceededException): ResponseEntity<*> {
        return ResponseEntity.badRequest().body(
            ErrorResponse(
                e.message.toString(),
                e.cause.toString()
            )
        )
    }
}