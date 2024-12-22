package br.com.hellodev.core.functions

import br.com.hellodev.core.R
import br.com.hellodev.core.enums.input.InputType
import br.com.hellodev.core.enums.input.InputType.COUNTRY
import br.com.hellodev.core.enums.input.InputType.DATE_BIRTH
import br.com.hellodev.core.enums.input.InputType.EMAIL
import br.com.hellodev.core.enums.input.InputType.FIRST_NAME
import br.com.hellodev.core.enums.input.InputType.GENRE
import br.com.hellodev.core.enums.input.InputType.PHONE
import br.com.hellodev.core.enums.input.InputType.SURNAME
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

fun capitalizeFirstLetter(input: String): String {
    return input.lowercase().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}

fun inputErrorMessage(type: InputType?): Int {
    return when (type) {
        FIRST_NAME -> R.string.error_first_name_invalid
        SURNAME -> R.string.error_surname_invalid
        DATE_BIRTH -> R.string.error_date_birth_invalid
        EMAIL -> R.string.error_email_invalid
        PHONE -> R.string.error_phone_invalid
        GENRE -> R.string.error_genre_invalid
        COUNTRY -> R.string.error_country_invalid
        else -> R.string.error_generic
    }
}