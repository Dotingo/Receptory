package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val PasswordIcon: ImageVector
    get() {
        if (_PasswordIcon != null) {
            return _PasswordIcon!!
        }
        _PasswordIcon = ImageVector.Builder(
            name = "PasswordIcon",
            defaultWidth = 18.dp,
            defaultHeight = 20.dp,
            viewportWidth = 18f,
            viewportHeight = 20f
        ).apply {
            path(fill = SolidColor(Color(0xFF231A04))) {
                moveTo(14f, 6.1f)
                verticalLineTo(5f)
                curveTo(14f, 3.674f, 13.473f, 2.402f, 12.536f, 1.464f)
                curveTo(11.598f, 0.527f, 10.326f, 0f, 9f, 0f)
                curveTo(7.674f, 0f, 6.402f, 0.527f, 5.464f, 1.464f)
                curveTo(4.527f, 2.402f, 4f, 3.674f, 4f, 5f)
                verticalLineTo(6.1f)
                curveTo(2.871f, 6.331f, 1.856f, 6.944f, 1.127f, 7.837f)
                curveTo(0.398f, 8.73f, -0f, 9.847f, 0f, 11f)
                verticalLineTo(15f)
                curveTo(0f, 16.326f, 0.527f, 17.598f, 1.464f, 18.535f)
                curveTo(2.402f, 19.473f, 3.674f, 20f, 5f, 20f)
                horizontalLineTo(13f)
                curveTo(14.326f, 20f, 15.598f, 19.473f, 16.536f, 18.535f)
                curveTo(17.473f, 17.598f, 18f, 16.326f, 18f, 15f)
                verticalLineTo(11f)
                curveTo(18f, 9.847f, 17.602f, 8.73f, 16.873f, 7.837f)
                curveTo(16.144f, 6.944f, 15.129f, 6.331f, 14f, 6.1f)
                close()
                moveTo(10f, 14f)
                curveTo(10f, 14.265f, 9.895f, 14.52f, 9.707f, 14.707f)
                curveTo(9.52f, 14.895f, 9.265f, 15f, 9f, 15f)
                curveTo(8.735f, 15f, 8.48f, 14.895f, 8.293f, 14.707f)
                curveTo(8.105f, 14.52f, 8f, 14.265f, 8f, 14f)
                verticalLineTo(12f)
                curveTo(8f, 11.735f, 8.105f, 11.48f, 8.293f, 11.293f)
                curveTo(8.48f, 11.105f, 8.735f, 11f, 9f, 11f)
                curveTo(9.265f, 11f, 9.52f, 11.105f, 9.707f, 11.293f)
                curveTo(9.895f, 11.48f, 10f, 11.735f, 10f, 12f)
                verticalLineTo(14f)
                close()
                moveTo(12f, 6f)
                horizontalLineTo(6f)
                verticalLineTo(5f)
                curveTo(6f, 4.204f, 6.316f, 3.441f, 6.879f, 2.879f)
                curveTo(7.441f, 2.316f, 8.204f, 2f, 9f, 2f)
                curveTo(9.796f, 2f, 10.559f, 2.316f, 11.121f, 2.879f)
                curveTo(11.684f, 3.441f, 12f, 4.204f, 12f, 5f)
                verticalLineTo(6f)
                close()
            }
        }.build()

        return _PasswordIcon!!
    }

@Suppress("ObjectPropertyName")
private var _PasswordIcon: ImageVector? = null
