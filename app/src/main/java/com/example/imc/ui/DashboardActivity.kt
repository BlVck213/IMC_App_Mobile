package com.example.imc.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.imc.R
import com.example.imc.util.calcularIdade
import com.example.imc.util.convertBase64toBitmap

class DashboardActivity: AppCompatActivity() {

    lateinit var tvNome: TextView
    lateinit var ivFotoPerfil: ImageView
    lateinit var tvProfissao: TextView
    lateinit var tvImc: TextView
    lateinit var tvNcd: TextView
    lateinit var tvPeso: TextView
    lateinit var tvIdade: TextView
    lateinit var tvAltura: TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar!!.hide()

        tvNome = findViewById(R.id.NOME)
        tvProfissao = findViewById(R.id.PROFISSAO)
        tvNcd = findViewById(R.id.NCD)
        tvImc = findViewById(R.id.IMC)
        tvIdade = findViewById(R.id.IDADE)
        tvAltura = findViewById(R.id.ALTURA)
        tvPeso = findViewById(R.id.PESO)
        ivFotoPerfil = findViewById(R.id.FOTO_PERFIL)

        carregarDashboard()

        val pesarAgora = findViewById<ImageView>(R.id.dois)

        pesarAgora.setOnClickListener {
            val abrirPesoActivity = Intent(this, PesoActivity::class.java)

            startActivity(abrirPesoActivity)


        }


    }


    private fun carregarDashboard(){
        val arquivo = getSharedPreferences("usuario", MODE_PRIVATE)

        tvNome.text = arquivo.getString("nome", "")
        tvProfissao.text = arquivo.getString("profissao", "")
        tvIdade.text = calcularIdade(arquivo.getString("dataNascimento", "")!!).toString()
        tvAltura.text = arquivo.getFloat("altura", 0.0f).toString()

        val bitmap = convertBase64toBitmap(arquivo.getString("fotoPerfil", "")!!)
                ivFotoPerfil.setImageBitmap(bitmap)
    }
}