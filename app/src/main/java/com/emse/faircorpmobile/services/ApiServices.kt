package com.emse.faircorpmobile.services

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServices {
    val windowsApiService : WindowApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://dev-mind.fr/training/android/")
            .build()
            .create(WindowApiService::class.java)
    }

    val myApiService : WindowApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://hanane-chrif.cleverapps.io/api/")
            .build()
            .create(WindowApiService::class.java)
    }

    val myRoomApiService : RoomApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://hanane-chrif.cleverapps.io/api/")
            .build()
            .create(RoomApiService::class.java)
    }
}