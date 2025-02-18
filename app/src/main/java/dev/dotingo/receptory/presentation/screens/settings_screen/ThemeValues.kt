package dev.dotingo.receptory.presentation.screens.settings_screen

enum class ThemeValues(val title: String) {
    SYSTEM_DEFAULT("Системная"),
    DARK_MODE("Темная"),
    LIGHT_MODE("Светлая")
}

val themes = listOf(
    ThemeValues.SYSTEM_DEFAULT.title,
    ThemeValues.DARK_MODE.title,
    ThemeValues.LIGHT_MODE.title
)