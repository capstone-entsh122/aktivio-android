package com.bangkit.aktivio.core.utils

import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import com.bangkit.aktivio.R
import com.bangkit.aktivio.core.types.ValidationRules

object FormValidator {
    fun setupFormValidation(
        forms: List<EditText>,
        validationRules: ValidationRules,
        actionButton: Button
    ) {
        forms.forEach { form ->
            form.doOnTextChanged { text, _, _, _ ->
                val isValid = validationRules[form]?.invoke(text.toString()) ?: false
                form.error = if (isValid) null else getErrorMessage(form, validationRules)
                actionButton.isEnabled = validationRules.all { (form, rule) -> rule(form.text.toString()) }
            }
        }
    }

    private fun getErrorMessage(form: EditText, validationRules: ValidationRules): String {
        return when (form) {
            in validationRules.keys -> when (form.id) {
                R.id.et_email -> "Invalid email"
                R.id.et_username -> "Username must be at least 4 characters"
                R.id.et_password -> "Password must be at least 8 characters"
                R.id.et_confirm_password -> "Password doesn't match"
                else -> "Invalid input"
            }
            else -> "Invalid input"
        }
    }
}
