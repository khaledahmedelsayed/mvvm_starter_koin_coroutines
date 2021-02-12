package com.khaledahmedelsayed.mvvmwithstatestarter.common.extensions

fun String.removeCharacterOccurrences(char: Char) = filterNot { it == char }

fun Any?.toStringOrNull() = if (toString() == "null" || toString().isBlank()) null else toString()
