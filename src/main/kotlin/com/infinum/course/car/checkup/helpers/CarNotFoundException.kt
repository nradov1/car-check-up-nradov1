package com.infinum.course.car.checkup.helpers

class CarNotFoundException(id: Long) : RuntimeException("Car ID $id not found")