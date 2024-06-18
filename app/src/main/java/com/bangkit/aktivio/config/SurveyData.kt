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
                        field = "age",
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
                        value = "heart / cardiovascular disease",
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
                        value = "pregnant",
                    ),
                    OptionModel(
                        id = 6,
                        title = "Asthma or Respiratory disease",
                        value = "asthma / respiratory disease",
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
                        value = "mahal",
                        icon = R.drawable.ic_fancy_equipment
                    ),
                    OptionModel(
                        id = 2,
                        title = "Standard Equipment",
                        description = "Equipment invest up to Rp. 2.000.000",
                        value = "sedang",
                        icon = R.drawable.ic_standard_equipment
                    ),
                    OptionModel(
                        id = 3,
                        title = "Cheap Equipment",
                        description = "Equipment invest up to Rp. 500.000",
                        value = "murah",
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
                        value = "meningkatkan kesehatan jantung",
                    ),
                    OptionModel(
                        id = 2,
                        title = "Improve muscle strength",
                        value = "meningkatkan kekuatan otot",
                    ),
                    OptionModel(
                        id = 3,
                        title = "Improve flexibility and body posture",
                        value = "meningkatkan fleksibilitas dan postur tubuh",
                    ),
                    OptionModel(
                        id = 4,
                        title = "Improve coordination and motoric skills",
                        value = "meningkatkan koordinasi dan keterampilan motorik",
                    ),
                    OptionModel(
                        id = 5,
                        title = "Improve concentration and focus",
                        value = "meningkatkan konsentrasi dan fokus",
                    ),
                    OptionModel(
                        id = 6,
                        title = "Reduce stress and mood improvement",
                        value = "mengurangi stres dan meningkatkan mood",
                    ),
                    OptionModel(
                        id = 7,
                        title = "Social skill improvement",
                        value = "memperbaiki keterampilan sosial dan bersosialisasi",
                    ),
                    OptionModel(
                        id = 8,
                        title = "Enjoying the nature",
                        value = "menawarkan kesempatan menikmati alam",
                    ),
                    OptionModel(
                        id = 9,
                        title = "Improve stamina",
                        value = "meningkatkan stamina",
                    ),
                )
            ),
            SurveyQuestion(
                id = 8,
                field = "placePreference",
                type = QuestionType.DOUBLE_BOX,
                question = "What is your exercise\n<red>preference place?<red>",
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
                question = "Do you prefer to\n<red>exercise alone?<red>",
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
                question = "<red>How long<red> do you\nwant to exercise?",
                description = "Please choose the option below",
                options = listOf(
                    OptionModel(
                        id = 1,
                        title = "< 15 minutes",
                        value = "< 15 menit",
                    ),
                    OptionModel(
                        id = 2,
                        title = "15-30 minutes",
                        value = "15-30 menit",
                    ),
                    OptionModel(
                        id = 3,
                        title = "30-45 minutes",
                        value = "30-45 menit",
                    ),
                    OptionModel(
                        id = 4,
                        title = "45-60 minutes",
                        value = "45-60 menit",
                    ),
                    OptionModel(
                        id = 5,
                        title = "> 60 minutes",
                        value = "> 60 menit",
                    ),
                )
            ),
            SurveyQuestion(
                id = 11,
                field = "2wh",
                type = QuestionType.INPUT_BOX,
                question = "Nearly there!" +
                        "enter your\n<red>weight and height<red>",
                description = "Please input on given field",
                options = listOf(
                    OptionModel(
                        id = 1,
                        field = "weight",
                        title = "Enter your weight",
                        hint = "Weight",
                        icon = R.drawable.ic_weight
                    ),
                    OptionModel(
                        id = 2,
                        field = "height",
                        title = "Enter your height",
                        hint = "Height",
                        icon = R.drawable.ic_height
                    ),
                )
            ),
        )
    }



}