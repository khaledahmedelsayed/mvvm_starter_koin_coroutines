package com.khaledahmedelsayed.mvvmwithstatestarter.common.extensions

import androidx.fragment.app.Fragment
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

// Using reflection to get class reference of a generic type
fun <T> Fragment.getGenericTypeClass(argumentNumber: Int): Class<T> { // Java style
    @Suppress("UNCHECKED_CAST")
    return (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[argumentNumber] as Class<T>
}

fun <T : Any> Fragment.getGenericTypeKClass(argumentNumber: Int): KClass<T> { // Kotlin style
    val className = this.javaClass.kotlin.supertypes[0].arguments[argumentNumber].type.toString()
    @Suppress("UNCHECKED_CAST")
    return Class.forName(className).kotlin as KClass<T>
}
