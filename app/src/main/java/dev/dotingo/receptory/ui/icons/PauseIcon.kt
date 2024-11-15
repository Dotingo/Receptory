package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val PauseIcon: ImageVector
    get() {
        if (_PauseIcon != null) {
            return _PauseIcon!!
        }
        _PauseIcon = ImageVector.Builder(
            name = "PauseIcon",
            defaultWidth = 22.dp,
            defaultHeight = 22.dp,
            viewportWidth = 22f,
            viewportHeight = 22f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFDF96))) {
                moveTo(16.958f, 2.75f)
                horizontalLineTo(14.208f)
                curveTo(12.943f, 2.75f, 11.917f, 3.776f, 11.917f, 5.042f)
                verticalLineTo(16.958f)
                curveTo(11.917f, 18.224f, 12.943f, 19.25f, 14.208f, 19.25f)
                horizontalLineTo(16.958f)
                curveTo(18.224f, 19.25f, 19.25f, 18.224f, 19.25f, 16.958f)
                verticalLineTo(5.042f)
                curveTo(19.25f, 3.776f, 18.224f, 2.75f, 16.958f, 2.75f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFDF96))) {
                moveTo(7.792f, 2.75f)
                horizontalLineTo(5.042f)
                curveTo(3.776f, 2.75f, 2.75f, 3.776f, 2.75f, 5.042f)
                verticalLineTo(16.958f)
                curveTo(2.75f, 18.224f, 3.776f, 19.25f, 5.042f, 19.25f)
                horizontalLineTo(7.792f)
                curveTo(9.057f, 19.25f, 10.083f, 18.224f, 10.083f, 16.958f)
                verticalLineTo(5.042f)
                curveTo(10.083f, 3.776f, 9.057f, 2.75f, 7.792f, 2.75f)
                close()
            }
        }.build()

        return _PauseIcon!!
    }

@Suppress("ObjectPropertyName")
private var _PauseIcon: ImageVector? = null
