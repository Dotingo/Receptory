package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val FinishIcon: ImageVector
    get() {
        if (_FinishIcon != null) {
            return _FinishIcon!!
        }
        _FinishIcon = ImageVector.Builder(
            name = "FinishIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF303037))) {
                moveTo(21f, 9f)
                verticalLineTo(15f)
                curveTo(21f, 16.591f, 20.368f, 18.117f, 19.243f, 19.243f)
                curveTo(18.117f, 20.368f, 16.591f, 21f, 15f, 21f)
                horizontalLineTo(9f)
                curveTo(7.409f, 21f, 5.883f, 20.368f, 4.757f, 19.243f)
                curveTo(3.632f, 18.117f, 3f, 16.591f, 3f, 15f)
                verticalLineTo(9f)
                curveTo(3f, 7.409f, 3.632f, 5.883f, 4.757f, 4.757f)
                curveTo(5.883f, 3.632f, 7.409f, 3f, 9f, 3f)
                horizontalLineTo(15f)
                curveTo(16.591f, 3f, 18.117f, 3.632f, 19.243f, 4.757f)
                curveTo(20.368f, 5.883f, 21f, 7.409f, 21f, 9f)
                close()
            }
        }.build()

        return _FinishIcon!!
    }

@Suppress("ObjectPropertyName")
private var _FinishIcon: ImageVector? = null
