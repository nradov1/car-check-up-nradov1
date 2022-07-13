package com.infinum.course.car.checkup

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [ApplicationConfig::class])
class CarCheckUpSystemTestIntegration @Autowired constructor(private var applicationContext: ApplicationContext,
                                                             private val service: CarCheckUpSystem) {
    @Test
    fun verifyCarCheckUpSystemBean() {
        assertThat(applicationContext.getBean(CarCheckUpSystem::class.java)).isNotNull
    }

    @Test
    fun isCheckUpNecessaryTest(){
        service.repo.insert(LocalDateTime.parse("2022-01-06T21:30:10"),Car("Toyota","Aygo","D7VZVDUSHVS9VZ"))
        service.repo.insert(LocalDateTime.parse("2021-12-07T21:30:10"),Car("Porsche","Macan","D7VZVNJXEHEW7"))
        service.repo.insert(LocalDateTime.parse("2020-10-08T21:30:10"),Car("Mercedes-Benz","Aygo","JEWIUFZF4893ZHFGRE"))
        service.repo.insert(LocalDateTime.parse("2019-11-09T21:30:10"),Car("Rolls Royce","Silver Seraph","NJDWEI38F78XH8FF"))
        val expected = true
        assertThat(service.isCheckUpNecessary("NJDWEI38F78XH8FF")).isEqualTo(expected)

    }


}