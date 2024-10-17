package dev.dotingo.receptory.ui.icons.arrows

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val DownArrowIcon: ImageVector
    get() {
        if (_DownArrowIcon != null) {
            return _DownArrowIcon!!
        }
        _DownArrowIcon = ImageVector.Builder(
            name = "ArrowDown",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF303037))) {
                moveTo(19.604f, 10.322f)
                lineTo(13.608f, 16.352f)
                curveTo(13.194f, 16.767f, 12.634f, 17f, 12.049f, 17f)
                curveTo(11.465f, 17f, 10.905f, 16.767f, 10.491f, 16.352f)
                lineTo(4.395f, 10.302f)
                curveTo(4.206f, 10.115f, 4.077f, 9.875f, 4.025f, 9.613f)
                curveTo(3.974f, 9.351f, 4.002f, 9.08f, 4.106f, 8.834f)
                curveTo(4.207f, 8.587f, 4.378f, 8.376f, 4.599f, 8.228f)
                curveTo(4.82f, 8.079f, 5.079f, 8f, 5.345f, 8f)
                horizontalLineTo(18.644f)
                curveTo(18.912f, 8f, 19.174f, 8.08f, 19.397f, 8.231f)
                curveTo(19.619f, 8.381f, 19.792f, 8.595f, 19.893f, 8.844f)
                curveTo(19.998f, 9.091f, 20.027f, 9.364f, 19.975f, 9.628f)
                curveTo(19.923f, 9.891f, 19.794f, 10.133f, 19.604f, 10.322f)
                close()
            }
        }.build()

        return _DownArrowIcon!!
    }

@Suppress("ObjectPropertyName")
private var _DownArrowIcon: ImageVector? = null

