package com.infinum.course.car.checkup

import com.infinum.course.car.checkup.car.entity.Car
import com.infinum.course.car.checkup.checkup.entity.CarCheckUp
import com.infinum.course.car.checkup.checkup.repository.CheckUpRepository
import com.infinum.course.car.checkup.repository.CarRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import java.time.LocalDate
import java.time.LocalDateTime

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoryTest @Autowired constructor(val carRepository: CarRepository, val checkUpRepository: CheckUpRepository) {

    @BeforeEach
    fun setUp() {
        val car1 = carRepository.save(Car(0,LocalDate.now().toString(),"Porsche","Taycan",2018,"0UM9VMCT8MV"))
        val car2 = carRepository.save(Car(0,LocalDate.now().toString(),"Porsche","Macan",2020,"85NV985NB6BN9B"))
        val car3 = carRepository.save(Car(0,LocalDate.now().toString(),"Rolls Royce","Neki kul",2017,"98VN2FMFKEJEC"))
        checkUpRepository.save(CarCheckUp(0, LocalDateTime.now(),"Perica",1999,car1))
        checkUpRepository.save(CarCheckUp(0, LocalDateTime.now(),"Ivica",7999,car2))
        checkUpRepository.save(CarCheckUp(0, LocalDateTime.now(),"Luka",8999,car3))
    }

    @Test
    fun test() {
        val car = carRepository.findCarById(1)
        assertThat(car).isNotNull
    }

    @Test
    fun test2() {
        val analytics = checkUpRepository.analytics()
        assertThat(analytics.size).isEqualTo(2)
    }

    @Test
    fun pageable() {
        val pageable = PageRequest.of(0, 2)
        val allCars = carRepository.findAll(pageable)
        assertThat(allCars.totalPages).isEqualTo(2)
        assertThat(allCars.content[0].manufacturer).isEqualTo("Porsche")
    }

}