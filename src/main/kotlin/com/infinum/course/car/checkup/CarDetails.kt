package com.infinum.course.car.checkup


data class CarDetails(var manufacturer:String="",
                 var model:String="",
                 var productionYear:Int=0,
                 var vin:String="",
                 var checkUps:MutableList<CarCheckUp> = mutableListOf(),
                 var checkUpNeccessity:Boolean = false)