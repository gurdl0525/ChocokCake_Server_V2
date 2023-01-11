package com.example.chocokcakeV2.global.config.filter.servlet

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest

@Component
class ServletFilter(): Filter {
    private companion object val logger: Logger = LoggerFactory.getLogger(ServletFilter::class.java)

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val req: HttpServletRequest = request as HttpServletRequest;
        logger.info("Request auth:${req.authType}, method:${req.method}")

        chain.doFilter(request, response)

        logger.info("Return auth:${req.authType}, method:${req.method}")
    }
}