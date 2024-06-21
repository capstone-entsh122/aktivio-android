package com.bangkit.aktivio.core.utils


import kotlin.reflect.KClass
import kotlin.reflect.full.allSuperclasses
import kotlin.reflect.full.createType
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.isSubtypeOf

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

inline fun <reified T : Any> Map<String, Any?>.toFlexClass(): T {
    val constructor = T::class.primaryConstructor
        ?: throw IllegalArgumentException("No primary constructor found for ${T::class}")

    val args = constructor.parameters.associateWith { param ->
        val value = this[param.name]

        // Perform type conversion if necessary
        if (value != null) {
            convertValue(value, param.type.classifier as KClass<*>)
        } else {
            value
        }
    }

    return constructor.callBy(args)
}

fun convertValue(value: Any, targetType: KClass<*>): Any {
    return when (targetType) {
        String::class -> value.toString()
        Int::class -> value.toString().toIntOrNull() ?: throw IllegalArgumentException("Cannot convert $value to Int")
        Double::class -> value.toString().toDoubleOrNull() ?: throw IllegalArgumentException("Cannot convert $value to Double")
        Float::class -> value.toString().toFloatOrNull() ?: throw IllegalArgumentException("Cannot convert $value to Float")
        Long::class -> value.toString().toLongOrNull() ?: throw IllegalArgumentException("Cannot convert $value to Long")
        Boolean::class -> value.toString().toBoolean()
        // Add more type conversions as needed
        else -> throw IllegalArgumentException("Unsupported type: $targetType")
    }
}

//inline fun <reified T : Any> Map<String, Any?>.toDataClassNested(): T {
//    return toDataClass(T::class)
//}
//
//fun <T : Any> Map<String, Any?>.toDataClass(clazz: KClass<T>): T {
//    val constructor = clazz.primaryConstructor
//        ?: throw IllegalArgumentException("No primary constructor found for ${clazz}")
//
//    val args = constructor.parameters.associateWith { param ->
//        val value = this[param.name]
//        val type = param.type
//
//        when {
//            value == null -> null
//            type.isSubtypeOf(Map::class.createType()) -> {
//                val nestedClazz = type.arguments[1].type?.classifier as? KClass<*>
//                    ?: throw IllegalArgumentException("Unable to determine nested class for parameter ${param.name}")
//                (value as Map<String, Any?>).toDataClass(nestedClazz)
//            }
//            type.isSubclassOf(Any::class) -> {
//                val nestedClazz = type.classifier as? KClass<*>
//                nestedClazz?.let { (value as? Map<String, Any?>)?.toDataClass(it) } ?: value
//            }
//            else -> value
//        }
//    }
//
//    return constructor.callBy(args)
//}
//
//fun <T : Any> KClass<T>.isSubtypeOf(other: KClass<*>): Boolean {
//    return this == other || this.allSuperclasses.contains(other)
//}
