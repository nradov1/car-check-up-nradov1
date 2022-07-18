package com.infinum.course.car.checkup

import java.time.LocalDate

object CarDetails {
    var manufacturer:String=""
    var model:String=""
    var productionYear:Int=0
    var vin:String=""
    var checkUps:List<CarCheckUp> = listOf()
    var checkUpNeccessity:Boolean = false

}