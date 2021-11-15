package com.emse.faircorpmobile.services

import com.emse.faircorpmobile.model.RoomDto
import com.emse.faircorpmobile.model.Status
import com.emse.faircorpmobile.model.WindowDto

class WindowService {
    companion object {
        // Fake rooms
        val ROOMS: List<RoomDto> = listOf(
            RoomDto(1, "Room EF 6.10", 18.2, 20.0),
            RoomDto(2, "Hall", 18.2, 18.0),
            RoomDto(3, "Room EF 7.10", 21.2, 20.0)
        )

        // Fake windows
        val WINDOWS: List<WindowDto> = listOf(
            WindowDto(1, "Entry Window", "Room EF 6.10", 1, Status.CLOSED),
            WindowDto(2, "Back Window", "Room EF 6.10", 1, Status.CLOSED),
            WindowDto(3, "Sliding door", "Hall",2, Status.OPEN),
            WindowDto(4, "Window 1", "Room EF 7.10", 3, Status.CLOSED),
            WindowDto(5, "Window 2", "Room EF 7.10",3, Status.CLOSED),
        )
    }

    fun findById(id: Long) = WINDOWS.firstOrNull { it.id == id}

    fun findAll() = WINDOWS.sortedBy { it.name }
}