package com.infinum.course.car.checkup.helpers

class CarCheckUpNotFoundException(id: Long) : RuntimeException("Car check-up ID $id not found")