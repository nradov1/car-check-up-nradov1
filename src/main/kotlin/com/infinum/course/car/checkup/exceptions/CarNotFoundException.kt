package com.infinum.course.car.checkup.exceptions

class CarNotFoundException(id: Long) : RuntimeException("Car ID $id not found")