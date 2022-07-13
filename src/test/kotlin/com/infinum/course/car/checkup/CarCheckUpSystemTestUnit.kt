package com.infinum.course.car.checkup
import io.mockk.every
import java.time.LocalDateTime
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class CarCheckUpSystemTestUnit {
    private val mockCarRepository = mockk<CarCheckUpRepository>()
    val checkUps = listOf(
        CarCheckUp(1,LocalDateTime.parse("2022-01-06T21:30:10"),Car("Toyota","Aygo","D7VZVDUSHVS9VZ")),
        CarCheckUp(2,LocalDateTime.parse("2021-12-07T21:30:10"),Car("Porsche","Macan","D7VZVNJXEHEW7")),
        CarCheckUp(3,LocalDateTime.parse("2020-10-08T21:30:10"),Car("Mercedes-Benz","Aygo","JEWIUFZF4893ZHFGRE")),
        CarCheckUp(4,LocalDateTime.parse("2019-11-09T21:30:10"),Car("Rolls Royce","Silver Seraph","NJDWEI38F78XH8FF"))
    )

    @Test
    @DisplayName("Should return Rolls Royce checkup")
    fun getCheckUpsTest() {
        val carCheckUpSystem = CarCheckUpSystem(mockCarRepository)
        every { mockCarRepository.findAll().values } returns checkUps

        val actualCheckUp = carCheckUpSystem.getCheckUps("NJDWEI38F78XH8FF")
        val expectedCheckUp = listOf(CarCheckUp(4,LocalDateTime.parse("2019-11-09T21:30:10"),Car("Rolls Royce","Silver Seraph","NJDWEI38F78XH8FF")))

        assertThat(actualCheckUp).isEqualTo(expectedCheckUp)
    }


    @Test
    @DisplayName("Should return 0")
    fun testCount() {
        val carCheckUpSystem = CarCheckUpSystem(mockCarRepository)
        every { mockCarRepository.findAll().values } returns checkUps
        val expected = 0
        val actual = carCheckUpSystem.countCheckUps("FRUVR9888VUR8VT")
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    @DisplayName("Should throw exception")
    fun findCarTest() {
        val carCheckUpSystem = CarCheckUpSystem(mockCarRepository)
        every { mockCarRepository.findAll().values} returns checkUps
        assertThatThrownBy { carCheckUpSystem.getCheckUps("904FH9DJE8D3R89V3") }.isInstanceOf(Exception::class.java)
    }



}

