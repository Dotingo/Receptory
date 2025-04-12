package dev.dotingo.receptory.presentation.screens.settings_screen

enum class ThemeValues(val title: String) {
    DARK_MODE("Темная"),
    LIGHT_MODE("Светлая")
}

val themes = listOf(
    ThemeValues.DARK_MODE.title,
    ThemeValues.LIGHT_MODE.title
)
