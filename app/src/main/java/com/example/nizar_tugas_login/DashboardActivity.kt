package com.example.nizar_tugas_login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class DashboardActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Ambil username dari LoginActivity
        val username = intent.getStringExtra("USERNAME")

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // Tampilkan username
        tvWelcome.text = "Selamat Datang, $username!"

        btnLogout.setOnClickListener {
            Toast.makeText(this, "Logout Berhasil", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
