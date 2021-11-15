package com.emse.faircorpmobile.model

enum class Status { OPEN, CLOSED}

//data class WindowDto(val id: Long, val name: String, val room: RoomDto, val status: Status)

data class WindowDto(val id: Long, val name: String, val roomName: String, val roomId: Long, val windowStatus: Status)