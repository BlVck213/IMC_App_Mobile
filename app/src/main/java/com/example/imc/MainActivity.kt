package com.example.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonCalcular = findViewById<Button>(R.id.button_calcular)

        val editTextPeso = findViewById<EditText>(R.id.edit_text_peso)
        val editTextAltura = findViewById<EditText>(R.id.edit_text_altura)

        val textImc = findViewById<TextView>(R.id.textViewImc)

        val textStatus = findViewById<TextView>(R.id.textViewStatus)



            buttonCalcular.setOnClickListener {
                textImc.text = calcularImc(
                    editTextPeso.text.toString().toInt(),
                    editTextAltura.text.toString().toDouble()
                ).toString()


            }

    }


}