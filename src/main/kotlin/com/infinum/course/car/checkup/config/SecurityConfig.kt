package com.infinum.course.car.checkup.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {


    @Bean
    fun securityFilterChain(http: HttpSecurity) : SecurityFilterChain {


        http {
            cors {  }
            csrf { disable() }
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
            authorizeRequests {
                authorize( HttpMethod.POST,"/api/v1/car", hasAnyAuthority("admin","user"))
                authorize( HttpMethod.GET, "/api/v1/car/paged", hasAuthority("admin"))
                authorize( HttpMethod.GET, "/api/v1/car/{id}", hasAnyAuthority("admin","user"))
                authorize( HttpMethod.POST,"/api/v1/create-checkup", hasAuthority("admin"))
                authorize( HttpMethod.GET, "/api/v1/car/{carId}/checkup", hasAnyAuthority("admin","user"))
                authorize( HttpMethod.GET, "/api/v1/manufacturer-analytics", permitAll)
                authorize( HttpMethod.GET, "/api/v1/car/delete", hasAuthority("admin"))
                authorize( HttpMethod.GET, "/api/v1/checkups/delete", hasAuthority("admin"))
                authorize(anyRequest, authenticated)
            }
            oauth2ResourceServer {
                jwt {}
            }
        }
        return http.build()
    }


}