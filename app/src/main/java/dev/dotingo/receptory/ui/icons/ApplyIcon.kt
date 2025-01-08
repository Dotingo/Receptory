package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val ApplyIcon: ImageVector
    get() {
        if (_ApplyIcon != null) {
            return _ApplyIcon!!
        }
        _ApplyIcon = ImageVector.Builder(
            name = "ApplyIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF303037)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(19.707f, 6.293f)
                curveTo(20.098f, 6.683f, 20.098f, 7.317f, 19.707f, 7.707f)
                lineTo(9.707f, 17.707f)
                curveTo(9.317f, 18.098f, 8.683f, 18.098f, 8.293f, 17.707f)
                lineTo(4.293f, 13.707f)
                curveTo(3.902f, 13.317f, 3.902f, 12.683f, 4.293f, 12.293f)
                curveTo(4.683f, 11.902f, 5.317f, 11.902f, 5.707f, 12.293f)
                lineTo(9f, 15.586f)
                lineTo(18.293f, 6.293f)
                curveTo(18.683f, 5.902f, 19.317f, 5.902f, 19.707f, 6.293f)
                close()
            }
        }.build()

        return _ApplyIcon!!
    }

@Suppress("ObjectPropertyName")
private var _ApplyIcon: ImageVector? = null
