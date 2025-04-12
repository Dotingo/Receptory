package dev.dotingo.receptory.utils

fun StringBuilder.appendIfNotBlank(label: String, value: String) {
    if (value.isNotBlank()) append("$label$value\n")
}