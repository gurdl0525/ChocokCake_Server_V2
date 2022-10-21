package com.example.chocokcakeV2.global.error.handler

import com.example.chocokcakeV2.global.error.data.ErrorCode
import com.example.chocokcakeV2.global.error.data.ErrorResponse
import com.example.chocokcakeV2.global.error.exception.BusinessException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ExceptionHandlerFilter(
    private val objectMapper: ObjectMapper
): OncePerRequestFilter(){

    @Throws(IOException::class)
    private fun sendErrorResponse(
        errorCode: ErrorCode,
        data: String,
        response: HttpServletResponse
    ){
        response.status = errorCode.status.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"
        objectMapper.writeValue(
            response.writer,
            ErrorResponse(
                errorCode.message,
                data
            )
        )
    }

    @Throws(IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try{
            filterChain.doFilter(request, response)
        }catch (e: BusinessException){
            sendErrorResponse(e.errorCode, e.data, response)
        }catch (e: Forbidden){
            sendErrorResponse(ErrorCode.FORBIDDEN, "Token is Null", response)
        }catch (e: Exception){
            sendErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, "exception handler filter", response)
        }
    }
}