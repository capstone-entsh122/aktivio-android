package com.bangkit.aktivio.core.utils

object ValidationHelper {

    fun email(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)\$"
        return email.matches(emailRegex.toRegex())
    }

    fun username(username: String): Boolean {
        return username.length >= 4
    }

    fun pass(password: String): Boolean {
        return password.length >= 8
    }

    fun matchpass(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    fun notempty(input: String): Boolean {
        return input.isNotEmpty()
    }

    fun phone(phoneNumber: String): Boolean {
        val phoneRegex = "^\\d{10,15}\$"
        return phoneNumber.matches(phoneRegex.toRegex())
    }

    fun number(input: String): Boolean {
        return input.toDoubleOrNull() != null
    }
}
