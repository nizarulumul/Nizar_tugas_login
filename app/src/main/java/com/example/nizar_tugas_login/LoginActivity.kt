package com.example.nizar_tugas_login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : Activity() {

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // 👁 toggle password visibility
        etPassword.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val DRAWABLE_END = 2
                val drawable = etPassword.compoundDrawables[DRAWABLE_END]
                if (drawable != null) {
                    val touchX = event.x
                    val width = etPassword.width
                    val paddingEnd = etPassword.paddingEnd
                    val drawableWidth = drawable.bounds.width()

                    if (touchX >= (width - paddingEnd - drawableWidth)) {
                        isPasswordVisible = !isPasswordVisible
                        if (isPasswordVisible) {
                            etPassword.transformationMethod =
                                HideReturnsTransformationMethod.getInstance()
                            etPassword.setCompoundDrawablesWithIntrinsicBounds(
                                etPassword.compoundDrawables[0],
                                etPassword.compoundDrawables[1],
                                getDrawable(android.R.drawable.ic_menu_close_clear_cancel),
                                etPassword.compoundDrawables[3]
                            )
                        } else {
                            etPassword.transformationMethod =
                                PasswordTransformationMethod.getInstance()
                            etPassword.setCompoundDrawablesWithIntrinsicBounds(
                                etPassword.compoundDrawables[0],
                                etPassword.compoundDrawables[1],
                                getDrawable(android.R.drawable.ic_menu_view),
                                etPassword.compoundDrawables[3]
                            )
                        }
                        etPassword.setSelection(etPassword.text?.length ?: 0)
                        return@setOnTouchListener true
                    }
                }
            }
            false
        }

        // 🟢 Login button click
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username == "admin" && password == "1234") {
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()

                // 👉 arahkan ke MainActivity (bukan DashboardActivity lagi)
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("USERNAME", username)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
