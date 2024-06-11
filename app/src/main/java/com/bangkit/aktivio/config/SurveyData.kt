package com.bangkit.aktivio.config

import com.bangkit.aktivio.R
import com.bangkit.aktivio.core.domain.model.OptionModel
import com.bangkit.aktivio.core.domain.model.SurveyQuestion

object SurveyData {
    fun getSurveyData() : List<SurveyQuestion> {
        return listOf(
            SurveyQuestion(
                id = 1,
                field = "location",
                type = QuestionType.SINGLE_BOX,
                question = "Select Your Location",
                description = "Please choose your location in map box",
            ),
            SurveyQuestion(
                id = 2,
                field = "gender",
                type = QuestionType.DOUBLE_BOX,
                question = "Select Your Gender",
                description = "Please Choose between Two Options",
                options = listOf(
                    OptionModel(
                        id = 1,
                        title = "Male",
                        value = "male",
                        icon = R.drawable.ic_male
                    ),
                    OptionModel(
                        id = 2,
                        title = "Female",
                        value = "female",
                        icon = R.drawable.ic_female
                    )
                )
            ),
            SurveyQuestion(
                id = 4,
                field = "equipment",
                type = QuestionType.TRIPLE_BOX,
                question = "Select Your Equipment",
                description = "Please choose your location in map box",
                options = listOf(
                    OptionModel(
                        id = 1,
                        title = "Fancy Equipment",
                        description = "Equipment invest up to Rp. 4.000.000",
                        value = "fancy",
                        icon = R.drawable.ic_fancy_equipment
                    ),
                    OptionModel(
                        id = 2,
                        title = "Standard Equipment",
                        description = "Equipment invest up to Rp. 2.000.000",
                        value = "standard",
                        icon = R.drawable.ic_standard_equipment
                    ),
                    OptionModel(
                        id = 3,
                        title = "Cheap Equipment",
                        description = "Equipment invest up to Rp. 500.000",
                        value = "cheap",
                        icon = R.drawable.ic_cheap_equipment
                    ),
                )
            ),
            SurveyQuestion(
                id = 5,
                field = "motivation",
                type = QuestionType.MULTI_CHECKBOX,
                question = "What is your main motivation?",
                description = "You can choose more than 1",
                options = listOf(
                    OptionModel(
                        id = 1,
                        title = "Improve Heart Health",
                        value = "improve heart health",
                    ),
                    OptionModel(
                        id = 2,
                        title = "Improve Muscle Strength",
                        value = "improve muscle strength",
                    ),
                    OptionModel(
                        id = 3,
                        title = "Improve Flexibility and Body Posture",
                        value = "improve flexibility and body posture",
                    ),
                    OptionModel(
                        id = 4,
                        title = "Improve Coordination and Motoric Skills",
                        value = "improve coordination and motoric skills",
                    ),
                    OptionModel(
                        id = 5,
                        title = "Improve Concentration and Focus",
                        value = "improve concentration and focus",
                    ),
                )
            )
        )
    }



}