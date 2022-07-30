package com.infinum.course.car.checkup

import com.infinum.course.car.checkup.car.entity.Car
import com.infinum.course.car.checkup.carmodel.entity.CarModels
import com.infinum.course.car.checkup.carmodel.repository.CarModelRepository
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
import java.time.LocalDateTime
import java.util.*

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoryTest @Autowired constructor(val carRepository: CarRepository,
                                            val checkUpRepository: CheckUpRepository,
                                            val carModelRepository: CarModelRepository) {

    @BeforeEach
    fun setUp() {
        val carModel1 = carModelRepository.save(CarModels(UUID.randomUUID(),"Citroen","C3"))
        val carModel2 = carModelRepository.save(CarModels(UUID.randomUUID(),"Porsche","Panamera"))
        val carModel3 = carModelRepository.save(CarModels(UUID.randomUUID(),"Hyundai","i30"))
        val car1 = carRepository.save(Car(0, LocalDateTime.now(),2018,"84RF8GJH5G98T5HU5", carModel1))
        val car2 = carRepository.save(Car(0,LocalDateTime.now(),2017,"84RHG8054GUH4N", carModel2))
        val car3 = carRepository.save(Car(0,LocalDateTime.now(),2019,"848FHGGU4N80ZBN0", carModel3))
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
        assertThat(allCars.content[0].carModel.manufacturer).isEqualTo("Porsche")
    }

}