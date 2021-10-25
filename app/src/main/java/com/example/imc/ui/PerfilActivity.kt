package com.example.imc.ui

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.imc.R
import com.example.imc.model.Usuario
import com.example.imc.util.convertStringToLocalDate
import java.time.LocalDate
import java.util.*



class PerfilActivity: AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var editNome: EditText
    lateinit var editProfissao: EditText
    lateinit var editAltura: EditText
    lateinit var editData: EditText
    lateinit var editSexo: RadioGroup
    lateinit var textSexo: TextView
    lateinit var editSexoFem: RadioButton
    lateinit var editSexoMasc: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        editEmail = findViewById<EditText>(R.id.edit_email)
        editSenha = findViewById<EditText>(R.id.edit_senha)
        editNome = findViewById<EditText>(R.id.edit_nome)
        editProfissao = findViewById<EditText>(R.id.edit_profissao)
        editAltura = findViewById<EditText>(R.id.edit_altura)
        editData = findViewById<EditText>(R.id.data)
        editSexo = findViewById<RadioGroup>(R.id.edit_sexo)
        editSexoMasc = findViewById<RadioButton>(R.id.edit_sexo_masc)
        editSexoFem = findViewById<RadioButton>(R.id.edit_sexo_femin)
        textSexo = findViewById<TextView>(R.id.textSexo)



        supportActionBar!!.title = "Perfil"
        supportActionBar!!.subtitle = "Crie seu Perfil"

        // Criar Calendario
        val calendario = Calendar.getInstance()

        // Determinar os dados (dia, mês, e ano) do calendário
        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)



        // Abrir componente DatePicker
        val eDataNascimento = findViewById<EditText>(R.id.data)

        eDataNascimento.setOnClickListener {
            val dp = DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { view, _ano, _mes, _dia ->
                        eDataNascimento.setText("$_dia/${_mes + 1}/$_ano")
                    }, ano, mes, dia)

            dp.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_perfil, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(validarCampos()){
            var nascimento = convertStringToLocalDate(editData.text.toString())

            val usuario = Usuario(
                1,
                editNome.text.toString(),
                editEmail.text.toString(),
                editSenha.text.toString(),
                0,
                editAltura.text.toString().toDouble(),
                LocalDate.of( nascimento.year, nascimento.monthValue, nascimento.dayOfMonth ),
                editProfissao.text.toString(),
                if(editSexoFem.isChecked){
                    'F'
                } else {
                    'M'
                }
            )

            val dados = getSharedPreferences("usuario", Context.MODE_PRIVATE)

            val editor = dados.edit()
            editor.putInt("id", usuario.id)
            editor.putString("nome", usuario.nome)
            editor.putString("email", usuario.email)
            editor.putString("senha", usuario.senha)
            editor.putInt("peso", usuario.peso)
            editor.putFloat("altura", usuario.altura.toFloat())
            editor.putString("dataNascimento", usuario.dataNascimento.toString())
            editor.putString("profissao", usuario.profissao)
            editor.putString("sexo", usuario.sexo.toString())
            editor.apply()
        }

        when (item.itemId){
            R.id.menu_save -> {
                Toast.makeText(this, "Salvar", Toast.LENGTH_SHORT).show()
            }
        }

        validarCampos()

        return true
    }

    fun validarCampos() : Boolean{
        var valido = true

        if (editEmail.text.isEmpty()) {
            editEmail.error = "O e-mail é obrigatório"
            valido = false
        }
        if (editSenha.text.isEmpty()) {
            editSenha.error = "A senha é obrigatória"
            valido = false
        } else if (editSenha.text.length <= 8 || editSenha.text.length >= 12){
            editSenha.error = "A senha deve ter no mínimo 8 e no máximo 12 caracteres"
            valido = false
        }
        if (editNome.text.isEmpty()) {
            editNome.error = "O nome é obrigatório"
            valido = false
        }
        if (editProfissao.text.isEmpty()) {
            editProfissao.error = "A profissão é obrigatória"
            valido = false
        }
        if (editAltura.text.isEmpty()) {
            editAltura.error = "A altura é obrigatória"
            valido = false
        }
        if (editData.text.isEmpty()) {
            editData.error = "A data é obrigatória"
            valido = false
        } else {
            editData.error = null
            valido = true
        }
        if (editSexoMasc.isChecked || editSexoFem.isChecked) {
            textSexo.error = null
            valido = true
        } else {
            textSexo.error = "O sexo não foi definido"
            valido = false
        }


        return valido
    }

}