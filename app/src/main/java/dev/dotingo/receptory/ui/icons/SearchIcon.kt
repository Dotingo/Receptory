package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val SearchIcon: ImageVector
    get() {
        if (_SearchIcon != null) {
            return _SearchIcon!!
        }
        _SearchIcon = ImageVector.Builder(
            name = "SearchIcon",
            defaultWidth = 20.dp,
            defaultHeight = 20.dp,
            viewportWidth = 20f,
            viewportHeight = 20f
        ).apply {
            path(fill = SolidColor(Color(0xFF1F1B13))) {
                moveTo(19.71f, 18.3f)
                lineTo(16f, 14.61f)
                curveTo(17.29f, 13.025f, 17.997f, 11.044f, 18f, 9f)
                curveTo(18f, 6.613f, 17.052f, 4.324f, 15.364f, 2.636f)
                curveTo(13.676f, 0.948f, 11.387f, 0f, 9f, 0f)
                curveTo(6.613f, 0f, 4.324f, 0.948f, 2.636f, 2.636f)
                curveTo(0.948f, 4.324f, 0f, 6.613f, 0f, 9f)
                curveTo(0f, 11.387f, 0.948f, 13.676f, 2.636f, 15.364f)
                curveTo(4.324f, 17.052f, 6.613f, 18f, 9f, 18f)
                curveTo(11.044f, 17.997f, 13.025f, 17.29f, 14.61f, 16f)
                lineTo(18.3f, 19.71f)
                curveTo(18.486f, 19.895f, 18.738f, 19.999f, 19f, 20f)
                curveTo(19.132f, 20.001f, 19.262f, 19.976f, 19.384f, 19.926f)
                curveTo(19.506f, 19.876f, 19.617f, 19.803f, 19.71f, 19.71f)
                curveTo(19.896f, 19.523f, 20.001f, 19.269f, 20.001f, 19.005f)
                curveTo(20.001f, 18.741f, 19.896f, 18.487f, 19.71f, 18.3f)
                close()
                moveTo(9f, 16f)
                curveTo(7.143f, 16f, 5.363f, 15.262f, 4.05f, 13.95f)
                curveTo(2.737f, 12.637f, 2f, 10.856f, 2f, 9f)
                curveTo(2f, 7.143f, 2.737f, 5.363f, 4.05f, 4.05f)
                curveTo(5.363f, 2.737f, 7.143f, 2f, 9f, 2f)
                curveTo(10.856f, 2f, 12.637f, 2.737f, 13.95f, 4.05f)
                curveTo(15.262f, 5.363f, 16f, 7.143f, 16f, 9f)
                curveTo(16f, 10.856f, 15.262f, 12.637f, 13.95f, 13.95f)
                curveTo(12.637f, 15.262f, 10.856f, 16f, 9f, 16f)
                close()
            }
        }.build()

        return _SearchIcon!!
    }

@Suppress("ObjectPropertyName")
private var _SearchIcon: ImageVector? = null
