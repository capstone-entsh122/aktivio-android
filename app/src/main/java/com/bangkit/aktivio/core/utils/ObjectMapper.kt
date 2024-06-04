package com.bangkit.aktivio.core.utils


import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor

/// Part of RsAndro (RsKit Families) : Mobile Development Tools
/// BaseRequest Utility by Resma Adi Nugroho : @reezcode
/// Version 1.0.0

/// Object Mapper Utility
/// Tired of writing mapping code for your data classes? Use this extension function to map one data class to another.
/// Its dynamic and allows you to map any data class to another as long as the properties match.
inline fun <reified I : Any, reified O : Any> I.mapTo(): O {
    /// Get the input and output classes
    val inputClass = I::class
    val outputClass = O::class

    /// Get the properties of the input class
    val inputProperties = inputClass.declaredMemberProperties
    /// Get the primary constructor of the output class
    val primaryConstructor = outputClass.primaryConstructor
        ?: throw IllegalArgumentException("Class ${outputClass.simpleName} does not have a primary constructor")

    /// Map the properties of the input class to the properties of the output class
    val args = primaryConstructor.parameters.associateWith { parameter ->
        inputProperties.firstOrNull { it.name == parameter.name }?.get(this)
    }

    /// Create an instance of the output class using the mapped properties
    return primaryConstructor.callBy(args)
}

/// Having a map and want to convert it to a data class? Use this extension function to convert a map to a data class.
/// What's different with mapTo function? This function is specifically for map to avoid error when mapping to data class.
inline fun <reified T : Any> Map<String, Any?>.toDataClass(): T {
    val constructor = T::class.primaryConstructor
        ?: throw IllegalArgumentException("No primary constructor found for ${T::class}")

    val args = constructor.parameters.associateWith { param ->
        this[param.name]
    }

    return constructor.callBy(args)
}
