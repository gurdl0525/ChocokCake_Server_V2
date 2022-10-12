package com.example.chocokcakeV2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class ChocokCakeServerV2Application

fun main(args: Array<String>) {
    runApplication<ChocokCakeServerV2Application>(*args)
}
