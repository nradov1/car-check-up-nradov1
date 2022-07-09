package com.infinum.course.car.checkup

fun main() {
    //println( CarCheckUpSystem.addCheckUp("T98UITJGROE4WIG8UJT4")) //exception
    println( CarCheckUpSystem.addCheckUp("F73HF7R39DJ8L49G6"))
    println( CarCheckUpSystem.countCheckUps("Toyota"))
    println( CarCheckUpSystem.countCheckUps("Mercedes-Benz"))
    //println(CarCheckUpSystem.isCheckUpNecessary("T98UITJGROE4WIG8UJT4")) //exception
    println(CarCheckUpSystem.isCheckUpNecessary("F73HF7R39DJ8L49G6"))
    println(CarCheckUpSystem.getCheckUps("3G6F8H7225F6027G5"))

}