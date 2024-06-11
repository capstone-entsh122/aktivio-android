package com.bangkit.aktivio.core.domain.model

data class OptionModel(
    val id: Int,
    val icon: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val value: String? = null,
    val hint: String? = null,
)