package com.infinum.course.car.checkup.exceptions


class CarModelError(manufacturer: String, model:String) : RuntimeException("Car model manufacturer $manufacturer and model $model not found")