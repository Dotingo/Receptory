package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val PlusIcon: ImageVector
    get() {
        if (_PlusIcon != null) {
            return _PlusIcon!!
        }
        _PlusIcon = ImageVector.Builder(
            name = "PlusIcon",
            defaultWidth = 17.dp,
            defaultHeight = 16.dp,
            viewportWidth = 17f,
            viewportHeight = 16f
        ).apply {
            path(fill = SolidColor(Color(0xFF1F1B13))) {
                moveTo(16.667f, 8f)
                curveTo(16.667f, 8.265f, 16.557f, 8.52f, 16.362f, 8.707f)
                curveTo(16.166f, 8.895f, 15.901f, 9f, 15.625f, 9f)
                horizontalLineTo(9.375f)
                verticalLineTo(15f)
                curveTo(9.375f, 15.265f, 9.265f, 15.52f, 9.07f, 15.707f)
                curveTo(8.875f, 15.895f, 8.61f, 16f, 8.333f, 16f)
                curveTo(8.057f, 16f, 7.792f, 15.895f, 7.597f, 15.707f)
                curveTo(7.401f, 15.52f, 7.292f, 15.265f, 7.292f, 15f)
                verticalLineTo(9f)
                horizontalLineTo(1.042f)
                curveTo(0.765f, 9f, 0.5f, 8.895f, 0.305f, 8.707f)
                curveTo(0.11f, 8.52f, 0f, 8.265f, 0f, 8f)
                curveTo(0f, 7.735f, 0.11f, 7.48f, 0.305f, 7.293f)
                curveTo(0.5f, 7.105f, 0.765f, 7f, 1.042f, 7f)
                horizontalLineTo(7.292f)
                verticalLineTo(1f)
                curveTo(7.292f, 0.735f, 7.401f, 0.48f, 7.597f, 0.293f)
                curveTo(7.792f, 0.105f, 8.057f, 0f, 8.333f, 0f)
                curveTo(8.61f, 0f, 8.875f, 0.105f, 9.07f, 0.293f)
                curveTo(9.265f, 0.48f, 9.375f, 0.735f, 9.375f, 1f)
                verticalLineTo(7f)
                horizontalLineTo(15.625f)
                curveTo(15.901f, 7f, 16.166f, 7.105f, 16.362f, 7.293f)
                curveTo(16.557f, 7.48f, 16.667f, 7.735f, 16.667f, 8f)
                close()
            }
        }.build()

        return _PlusIcon!!
    }

@Suppress("ObjectPropertyName")
private var _PlusIcon: ImageVector? = null

