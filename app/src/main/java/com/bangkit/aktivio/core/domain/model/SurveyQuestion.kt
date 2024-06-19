package com.bangkit.aktivio.core.domain.model

import com.bangkit.aktivio.core.types.QuestionType

data class SurveyQuestion(
    val id: Int,
    val field: String,
    val type: QuestionType,
    val question: String,
    val description: String,
    val options: List<OptionModel>? = null
)
