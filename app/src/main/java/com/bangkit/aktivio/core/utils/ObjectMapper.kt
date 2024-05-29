package com.bangkit.aktivio.core.utils


import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor

inline fun <reified I : Any, reified O : Any> I.mapTo(): O {
    val inputClass = I::class
    val outputClass = O::class

    val inputProperties = inputClass.declaredMemberProperties
    val primaryConstructor = outputClass.primaryConstructor
        ?: throw IllegalArgumentException("Class ${outputClass.simpleName} does not have a primary constructor")

    val args = primaryConstructor.parameters.associateWith { parameter ->
        inputProperties.firstOrNull { it.name == parameter.name }?.get(this)
    }

    return primaryConstructor.callBy(args)
}
