package com.example.chocokcakeV2.global.config.web

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
open class WebMvcConfig: WebMvcConfigurer {

    @Throws(Exception::class)
    override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("/**")
            .allowedHeaders("*")
            .allowedOrigins("http://localhost:3000")
            .allowedMethods("*")
    }
}