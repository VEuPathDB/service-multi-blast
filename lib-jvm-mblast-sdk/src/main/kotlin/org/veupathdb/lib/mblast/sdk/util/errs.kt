package org.veupathdb.lib.mblast.sdk.util

import kotlin.reflect.KClass


fun NonStringEnumException(cls: KClass<*>) = IllegalArgumentException("JSON form of ${cls.simpleName} was not a string value.")
fun NonIntEnumException(cls: KClass<*>) = IllegalArgumentException("JSON form of ${cls.simpleName} was not an int value.")

fun UnrecognizedEnumException(cls: KClass<*>, v: Any) = IllegalArgumentException("Unrecognized ${cls.simpleName} value: $v")