package br.com.hellodev.core.functions

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    return email.matches(emailRegex.toRegex())
}

fun isValidName(name: String): Boolean {
    val regex = "^[a-zA-Z]{3,}$".toRegex()
    return regex.matches(name)
}

fun isValidSurname(surname: String): Boolean {
    val regex = "^[a-zA-Z]{3,}$".toRegex()
    return regex.matches(surname)
}

fun isValidFullName(fullName: String): Boolean {
    val parts = fullName.trim().split(" ".toRegex())
    return parts.size >= 2 && parts.all { it.length >= 3 }
}


fun isValidPhone(phone: String): Boolean {
    val regex = """^\d{2}9\d{8}$""".toRegex()
    return regex.matches(phone)
}

fun isValidBirthDate(birthDate: String?): Boolean {
    // Verifica se o parâmetro é nulo ou vazio
    if (birthDate.isNullOrBlank() || birthDate.length != 8) return false

    // Define o formato esperado da data
    val dateFormat = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
    dateFormat.isLenient = false // Garante validação estrita da data

    return try {
        // Faz o parsing da data
        val parsedDate = dateFormat.parse(birthDate) ?: return false

        // Obtém a data atual
        val calendarNow = Calendar.getInstance()

        // Calcula a idade
        val calendarBirth = Calendar.getInstance().apply { time = parsedDate }
        val age = calendarNow.get(Calendar.YEAR) - calendarBirth.get(Calendar.YEAR)

        // Ajusta a idade caso o aniversário ainda não tenha ocorrido este ano
        if (calendarNow.get(Calendar.DAY_OF_YEAR) < calendarBirth.get(Calendar.DAY_OF_YEAR)) {
            age - 1
        } else {
            age
        } >= 18 // Verifica se a idade é maior ou igual a 18
    } catch (e: Exception) {
        false // Retorna falso para qualquer erro (data inválida ou fora do formato)
    }
}