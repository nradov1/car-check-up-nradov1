package com.infinum.course.car.checkup

import java.time.LocalDate

data class Car constructor (val createdOn: LocalDate, val manufacturer: String, val model:String, val productionYear: Int, val vin:String){

}