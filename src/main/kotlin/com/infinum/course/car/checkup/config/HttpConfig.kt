package com.infinum.course.car.checkup.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class HttpConfig{

    @Bean
    fun provideRestTemplate(
        restTemplateBuilder: RestTemplateBuilder,
        @Value("\${model-data.base-url}") baseUrl: String
    ): RestTemplate {
        return restTemplateBuilder.rootUri(baseUrl).build()
    }

}