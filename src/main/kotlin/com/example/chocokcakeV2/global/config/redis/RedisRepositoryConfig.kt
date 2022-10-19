package com.example.chocokcakeV2.global.config.redis

import com.example.chocokcakeV2.global.config.redis.dotenv.RedisProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.StringRedisSerializer

@EnableRedisRepositories
@Configuration
class RedisRepositoryConfig(
    private val property: RedisProperty
) {
    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory{
        return LettuceConnectionFactory(property.host, property.port)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, String>{
        val template: RedisTemplate<String, String> = RedisTemplate()
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = StringRedisSerializer()
        template.setConnectionFactory(redisConnectionFactory())
        return template
    }
}