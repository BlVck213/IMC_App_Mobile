package com.example.imc.util

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun convertStringToLocalDate(brazilDate:String) : LocalDate {
    val dateFormatterFromBrazil = DateTimeFormatter.ofPattern("dd/MM/yyyy") 

    val localDateFormat = LocalDate.parse(brazilDate, dateFormatterFromBrazil)

    return localDateFormat
}

fun calcularIdade(dataNascimento: String): Int {

    // Obter data atual (hoje)
    val hoje = LocalDate.now()

    val nascimentoArray = dataNascimento.split("-").toTypedArray()

    //Converter a data de nascimento em LocalDate
    val nascimento = LocalDate.of(
                                nascimentoArray[0].toInt(),
                                nascimentoArray[1].toInt(),
                                nascimentoArray[2].toInt())

    // Obter a idade
    val idade = Period.between(nascimento, hoje).years

    return idade


}
