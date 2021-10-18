package com.example.imc

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar!!.hide()
        val CriarPerfil = findViewById<TextView>(R.id.CriarPerfil)

        CriarPerfil.setOnClickListener {
            val abrirPerfil = Intent(this, PerfilActivity::class.java)

            startActivity(abrirPerfil)


        }

    }
}