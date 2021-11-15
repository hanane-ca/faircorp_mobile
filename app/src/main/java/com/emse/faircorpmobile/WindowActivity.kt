package com.emse.faircorpmobile

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.emse.faircorpmobile.services.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WindowActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getLongExtra(WINDOW_NAME_PARAM, 0)

        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().myApiService.findById(id).execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        val window = it.body()
                        if (window != null) {
                            findViewById<TextView>(R.id.txt_window_name).text = window.name
                            findViewById<TextView>(R.id.txt_room_name).text = window.roomName
                            findViewById<TextView>(R.id.txt_window_status).text =
                                window.windowStatus.toString()
                        }
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) { // (3)
                        Toast.makeText(
                            applicationContext,
                            "Error on window loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }


        val changeStatus: Button = findViewById(R.id.changeButton)

        // function for the change status button
        changeStatus.setOnClickListener {
            lifecycleScope.launch(context = Dispatchers.IO) {
                runCatching { ApiServices().windowsApiService.switchStatus(id).execute() }
                    .onSuccess {
                        withContext(context = Dispatchers.Main) {
                            val window = it.body()
                            if (window != null) {
                                findViewById<TextView>(R.id.txt_window_status).text =
                                    window.windowStatus.toString()
                            }
                        }
                    }
                    .onFailure {
                        withContext(context = Dispatchers.Main) {
                            Toast.makeText(
                                applicationContext,
                                "Error on windowStatus switching $it",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }
    }
}
