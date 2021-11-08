package com.example.imc.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.imc.R
import com.example.imc.util.autenticar

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar!!.hide()
        val CriarPerfil = findViewById<TextView>(R.id.CriarPerfil)
        val buttonEntrar = findViewById<Button>(R.id.buttonOK)


        val  editEmailLogin = findViewById<EditText>(R.id.edit_email_login)
        val  editSenhaLogin = findViewById<EditText>(R.id.edit_password_login)

        buttonEntrar.setOnClickListener {
        val autenticou = autenticar(editEmailLogin.text.toString(), editSenhaLogin.text.toString(), this)

        if (autenticou) {
            val intent = Intent(
                    this,
                    DashboardActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Usuário ou Senha estão incorretos", Toast.LENGTH_SHORT).show()
        }

        }


        CriarPerfil.setOnClickListener {
            val abrirPerfil = Intent(this, PerfilActivity::class.java)

            startActivity(abrirPerfil)


        }

    }




}