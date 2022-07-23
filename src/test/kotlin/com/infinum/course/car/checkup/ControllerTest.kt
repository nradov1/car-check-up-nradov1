package com.infinum.course.car.checkup


import com.fasterxml.jackson.databind.ObjectMapper
import com.infinum.course.car.checkup.helpers.CarDetails
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post


@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
) {

    @Test
    fun test() {
        mockMvc.post("/create-car") {
            content = objectMapper.writeValueAsString(CarDetails("Porsche","Taycan",2019,"94JGF8G5H9FI4FR"))
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }

    }

    @Test
    fun test2() {
        mockMvc.get("/car-details") {
            param("id", "1")
        }
            .andExpect { status { isOk() } }
    }
}