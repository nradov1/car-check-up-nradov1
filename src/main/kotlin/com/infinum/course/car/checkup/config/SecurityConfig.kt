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
                authorize( HttpMethod.POST,"/api/v1/car", hasAnyAuthority("SCOPE_ADMIN","SCOPE_USER"))
                authorize( HttpMethod.GET, "/api/v1/car/paged", hasAuthority("SCOPE_ADMIN"))
                authorize( HttpMethod.GET, "/api/v1/car/{id}", hasAnyAuthority("SCOPE_ADMIN","SCOPE_USER"))
                authorize( HttpMethod.POST,"/api/v1/create-checkup", hasAuthority("SCOPE_ADMIN"))
                authorize( HttpMethod.GET, "/api/v1/car/{carId}/checkup", hasAnyAuthority("SCOPE_ADMIN","SCOPE_USER"))
                authorize( HttpMethod.GET, "/api/v1/manufacturer-analytics", permitAll)
                authorize( HttpMethod.GET, "/api/v1/car/delete", hasAuthority("SCOPE_ADMIN"))
                authorize( HttpMethod.GET, "/api/v1/checkups/delete", hasAuthority("SCOPE_ADMIN"))
                authorize(anyRequest, authenticated)
            }
            oauth2ResourceServer {
                jwt {}
            }
        }
        return http.build()
    }


}