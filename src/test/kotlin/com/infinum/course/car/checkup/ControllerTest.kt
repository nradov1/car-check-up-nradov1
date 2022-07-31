package com.infinum.course.car.checkup


import com.fasterxml.jackson.databind.ObjectMapper
import com.infinum.course.car.checkup.car.controller.CarDetails
import com.infinum.course.car.checkup.carmodel.entity.CarModels
import com.infinum.course.car.checkup.carmodel.repository.CarModelRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockserver.springtest.MockServerTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.util.*



@MockServerTest
@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
    private val carModelRepository: CarModelRepository
) {

    @BeforeEach
    fun setUp() {
        val carModel1 = carModelRepository.save(CarModels(UUID.randomUUID(), "Porsche", "Panamera"))
        val carModel2 = carModelRepository.save(CarModels(UUID.randomUUID(), "Citroen", "C3"))
        val carModel3 = carModelRepository.save(CarModels(UUID.randomUUID(), "Hyundai", "i30"))
    }


    @Test
    fun test() {
        mockMvc.post("/api/v1/car") {
            content = objectMapper.writeValueAsString(CarDetails("Porsche","Panamera",2019,"94JGF8G5H9FI4FR"))
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { is2xxSuccessful() }
        }

    }

    @Test
    fun test2() {
        mockMvc.get("/api/v1/car/1") {
            //param("id", "1")
        }
            .andExpect { status { isOk() } }
    }
}