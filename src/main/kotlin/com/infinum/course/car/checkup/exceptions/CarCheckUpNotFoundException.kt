package com.infinum.course.car.checkup.exceptions

class CarCheckUpNotFoundException(id: Long) : RuntimeException("Car check-up ID $id not found")