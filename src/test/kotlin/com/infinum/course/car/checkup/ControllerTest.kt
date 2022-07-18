package com.infinum.course.car.checkup

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get


@WebMvcTest
class ControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

   @MockkBean
   lateinit var carService: CarCheckUpSystem

    //LocalDate.now(),"Porsche","Macan",2018,"8DHF88VFHDSDCNDD")


    @BeforeEach
    fun setUp() {
        every { carService.getManufacturers() } answers { mutableSetOf<String>("Toyota") }
        every { carService.countCheckUps("Toyota")} answers {0}
    }

    @Test
    fun testGetCars() {
        val expected = mutableMapOf<String,Int>("Toyota" to 0)
        mockMvc.get("/manufacturer-analytics")
            .andExpect {
                content { expected.toString() }
            }
    }
}