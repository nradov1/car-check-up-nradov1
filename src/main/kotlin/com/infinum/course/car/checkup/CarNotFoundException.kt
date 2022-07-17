package com.infinum.course.car.checkup

class CarNotFoundException(id: Long) : RuntimeException("Car ID $id not found")