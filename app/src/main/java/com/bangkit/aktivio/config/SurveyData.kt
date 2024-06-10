package com.bangkit.aktivio.config

import com.bangkit.aktivio.core.domain.model.SurveyQuestion

object SurveyData {
    fun getPostRegisQuestion() : List<SurveyQuestion> {
        return listOf(
            SurveyQuestion(
                id = 1,
                type = QuestionType.DOUBLE_BOX,
                question = "Select Your Gender",
                description = "Please Choose between Two Options",
                options = listOf(
                    mapOf(
                        "id" to 1,
                        "icon" to "1",
                        "label" to "Male"
                    ),
                    mapOf(
                        "id" to 2,
                        "icon" to "2",
                        "label" to "Female"
                    )
                )
            ),
            SurveyQuestion(
                id = 2,
                type = QuestionType.SINGLE_BOX,
                question = "Select Your Location",
                description = "Please choose your location in map box",
            ),
            SurveyQuestion(
                id = 3,
                type = QuestionType.TRIPLE_BOX,
                question = "Select Your Equipment",
                description = "Please choose your location in map box",
            )
        )
    }
}