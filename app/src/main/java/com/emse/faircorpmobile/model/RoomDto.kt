package com.emse.faircorpmobile.model

data class RoomDto(val id: Long,
                   val name: String,
                   val currentTemp: Double?,
                   val targetTemp: Double?)