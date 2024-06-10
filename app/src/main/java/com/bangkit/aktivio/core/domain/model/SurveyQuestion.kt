package com.bangkit.aktivio.core.domain.model

import com.bangkit.aktivio.config.QuestionType

data class SurveyQuestion(
    val id: Int,
    val type: QuestionType,
    val question: String,
    val description: String,
    val options: List<Map<String, Any>>? = null
)
