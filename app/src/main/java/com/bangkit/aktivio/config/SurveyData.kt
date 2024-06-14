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
                question = "Select your <red>location<red>",
                description = "Please choose your location in map box",
            ),
            SurveyQuestion(
                id = 2,
                field = "gender",
                type = QuestionType.DOUBLE_BOX,
                question = "Select your <red>gender<red>",
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
                id = 3,
                field = "age",
                type = QuestionType.INPUT_BOX,
                question = "How <red>old<red> are you?",
                description = "Please type your age in the box",
                options = listOf(
                    OptionModel(
                        id = 1,
                        title = "Enter your age",
                        hint = "Age",
                        icon = R.drawable.ic_age
                    ),
                )
            ),
            SurveyQuestion(
                id = 4,
                field = "fitnessLevel",
                type = QuestionType.TRIPLE_BOX,
                question = "What is your <red>current fitness?<red>",
                description = "Please choose the option below",
                options = listOf(
                    OptionModel(
                        id = 1,
                        title = "Good Condition",
                        description = "You’re frequently doing exercise or physical activity",
                        value = "good",
                        icon = R.drawable.ic_good_condition
                    ),
                    OptionModel(
                        id = 2,
                        title = "Average Condition",
                        description = "You’re doing exercise or physical activity but not consistently",
                        value = "average",
                        icon = R.drawable.ic_avg_condition
                    ),
                    OptionModel(
                        id = 3,
                        title = "Unfit Condition",
                        description = "You’re rarely or maybe never doing exercise or physical activity",
                        value = "unfit",
                        icon = R.drawable.ic_low_condition
                    ),
                )
            ),
            SurveyQuestion(
                id = 5,
                field = "diseaseHistory",
                type = QuestionType.MULTI_CHECKBOX,
                question = "What is your <red>health condition?<red>",
                description = "You can choose more than 1",
                options = listOf(
                    OptionModel(
                        id = 1,
                        title = "Heart or Cardiovascular disease",
                        value = "heart or cardiovascular disease",
                    ),
                    OptionModel(
                        id = 2,
                        title = "Hypertension",
                        value = "hypertension",
                    ),
                    OptionModel(
                        id = 3,
                        title = "Physical injury",
                        value = "physical injury",
                    ),
                    OptionModel(
                        id = 4,
                        title = "Difable",
                        value = "difable",
                    ),
                    OptionModel(
                        id = 5,
                        title = "Pregnant",
                        value = "improve concentration and focus",
                    ),
                    OptionModel(
                        id = 6,
                        title = "Asthma or Respiratory disease",
                        value = "asthma or respiratory disease",
                    ),
                )
            ),
            SurveyQuestion(
                id = 6,
                field = "equipment",
                type = QuestionType.TRIPLE_BOX,
                question = "Select your <red>equipment<red>",
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
                id = 7,
                field = "motivation",
                type = QuestionType.MULTI_CHECKBOX,
                question = "What is your <red>motivation?<red>",
                description = "You can choose more than 1",
                options = listOf(
                    OptionModel(
                        id = 1,
                        title = "Improve heart health",
                        value = "improve heart health",
                    ),
                    OptionModel(
                        id = 2,
                        title = "Improve muscle strength",
                        value = "improve muscle strength",
                    ),
                    OptionModel(
                        id = 3,
                        title = "Improve flexibility and body posture",
                        value = "improve flexibility and body posture",
                    ),
                    OptionModel(
                        id = 4,
                        title = "Improve coordination and motoric skills",
                        value = "improve coordination and motoric skills",
                    ),
                    OptionModel(
                        id = 5,
                        title = "Improve concentration and focus",
                        value = "improve concentration and focus",
                    ),
                    OptionModel(
                        id = 6,
                        title = "Reduce stress and mood improvement",
                        value = "reduce stress and mood improvement",
                    ),
                    OptionModel(
                        id = 7,
                        title = "Social skill improvement",
                        value = "social skill improvement",
                    ),
                    OptionModel(
                        id = 8,
                        title = "Enjoying the nature",
                        value = "enjoying the nature",
                    ),
                    OptionModel(
                        id = 9,
                        title = "Improve stamina",
                        value = "improve stamina",
                    ),
                )
            ),
            SurveyQuestion(
                id = 8,
                field = "placePreference",
                type = QuestionType.DOUBLE_BOX,
                question = "What is your exercise <red>preference place?<red>",
                description = "Please choose the option below",
                options = listOf(
                    OptionModel(
                        id = 1,
                        title = "Outdoor",
                        value = "luar rumah",
                        icon = R.drawable.ic_outdoor
                    ),
                    OptionModel(
                        id = 2,
                        title = "Indoor",
                        value = "rumah",
                        icon = R.drawable.ic_indoor
                    )

                )
            ),
            SurveyQuestion(
                id = 9,
                field = "socialPreference",
                type = QuestionType.DOUBLE_BOX,
                question = "Do you prefer to <red>exercise alone?<red>",
                description = "Please choose the option below",
                options = listOf(
                    OptionModel(
                        id = 1,
                        title = "Yes, alone",
                        value = "ya",
                        icon = R.drawable.ic_alone
                    ),
                    OptionModel(
                        id = 2,
                        title = "No, I’d prefer with others",
                        value = "tidak",
                        icon = R.drawable.ic_others
                    )

                )
            ),
            SurveyQuestion(
                id = 10,
                field = "availableTime",
                type = QuestionType.MULTI_RADIO,
                question = "<red>How long<red> do you want to exercise?",
                description = "Please choose the option below",
                options = listOf(
                    OptionModel(
                        id = 1,
                        title = "< 15 minutes",
                        value = "<15",
                    ),
                    OptionModel(
                        id = 2,
                        title = "15-30 minutes",
                        value = "15-30",
                    ),
                    OptionModel(
                        id = 3,
                        title = "30-45 minutes",
                        value = "30-45",
                    ),
                    OptionModel(
                        id = 4,
                        title = "45-60 minutes",
                        value = "45-60",
                    ),
                    OptionModel(
                        id = 5,
                        title = "> 60 minutes",
                        value = ">60",
                    ),
                )
            ),
        )
    }



}