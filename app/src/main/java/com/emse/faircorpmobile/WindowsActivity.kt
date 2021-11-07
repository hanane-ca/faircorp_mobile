package com.emse.faircorpmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emse.faircorpmobile.model.WindowsAdapterView
import com.emse.faircorpmobile.services.WindowService

class WindowsActivity : BasicActivity() , OnWindowSelectedListener {
    val windowService = WindowService() // (1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_windows)

        val recyclerView = findViewById<RecyclerView>(R.id.list_windows) // (2)
        val adapter = WindowsAdapterView(this) // (3)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        adapter.update(windowService.findAll()) // (4)
    }
    override fun onWindowSelected(id: Long) {
        val intent = Intent(this, WindowActivity::class.java).putExtra(WINDOW_NAME_PARAM, id)
        startActivity(intent)
    }
}