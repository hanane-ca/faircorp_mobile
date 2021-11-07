package com.emse.faircorpmobile.model

data class RoomDto(val id: Long,
                   val name: String,
                   val currentTemperature: Double?,
                   val targetTemperature: Double?)