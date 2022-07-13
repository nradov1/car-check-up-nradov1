package com.infinum.course.car.checkup


import org.springframework.beans.factory.getBean
import org.springframework.context.annotation.*
import java.time.LocalDateTime


fun main(){
    val car1 = Car("Toyota","Aygo","D7VZVDUSHVS9VZ")
    val car2 = Car("Porsche","Macan","D7VZVNJXEHEW7")
    val car3 = Car("Mercedes-Benz","Aygo","JEWIUFZF4893ZHFGRE")
    val car4 = Car("Rolls Royce","Silver Seraph","NJDWEI38F78XH8FF")

    val applicationContext = AnnotationConfigApplicationContext(ApplicationConfig::class.java)
    val service = applicationContext.getBean<CarCheckUpSystem>("carCheckUpSystem")

    service.repo.insert(LocalDateTime.parse("2022-01-06T21:30:10"),car1)
    service.repo.insert(LocalDateTime.parse("2021-12-07T21:30:10"),car2)
    service.repo.insert(LocalDateTime.parse("2020-10-08T21:30:10"),car3)
    service.repo.insert(LocalDateTime.parse("2019-11-09T21:30:10"),car4)
    service.repo.insert(LocalDateTime.parse("2018-04-10T21:30:10"),car1)
    service.repo.insert(LocalDateTime.parse("2019-09-11T21:30:10"),car2)
    service.repo.insert(LocalDateTime.parse("2020-10-12T21:30:10"),car3)
    service.repo.insert(LocalDateTime.parse("2021-11-30T21:30:10"),car4)
    service.repo.insert(LocalDateTime.parse("2022-02-24T21:30:10"),car1)
    service.repo.insert(LocalDateTime.parse("2021-03-29T21:30:10"),car4)
    service.addCheckUp("JEWIUFZF4893ZHFGRE")
    service.addCheckUp("D7VZVDUSHVS9VZ")
    println(service.countCheckUps("Toyota"))
    println(service.addCheckUp("JEWIUFZF4893ZHFGRE"))
    println(service.getCheckUps("JEWIUFZF4893ZHFGRE"))
    //println(service.getCheckUps("V8ZR9VE8EVEV9ER8"))

    println(service.isCheckUpNecessary("NJDWEI38F78XH8FF"))
    println(service.addCheckUp("JEWIUFZF4893ZHFGRE"))
    println(service.getCheckUps("JEWIUFZF4893ZHFGRE"))
    //println(service.getCheckUps("V8ZR9VE8EVEV9ER8"))




}






