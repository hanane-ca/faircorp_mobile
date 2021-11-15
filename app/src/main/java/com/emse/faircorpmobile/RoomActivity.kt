package com.emse.faircorpmobile

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.emse.faircorpmobile.services.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getLongExtra(ROOM_NAME_PARAM, 0)

        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().myRoomApiService.findById(id).execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        val room = it.body()
                        if (room != null) {
                            findViewById<TextView>(R.id.txt_room_name2).text = room.name
                            findViewById<TextView>(R.id.txt_current_temp2).text = room.currentTemp.toString()
                            findViewById<TextView>(R.id.txt_target_temp2).text = room.targetTemp.toString()
                        }
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "Error on room loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
}