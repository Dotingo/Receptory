package dev.dotingo.receptory.ui.icons.arrows

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val FilledArrowDownIcon: ImageVector
    get() {
        if (_FilledArrowDownIcon != null) {
            return _FilledArrowDownIcon!!
        }
        _FilledArrowDownIcon = ImageVector.Builder(
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

        return _FilledArrowDownIcon!!
    }

@Suppress("ObjectPropertyName")
private var _FilledArrowDownIcon: ImageVector? = null


val OutlinedArrowDownIcon: ImageVector
    get() {
        if (_OutlinedArrowDownIcon != null) {
            return _OutlinedArrowDownIcon!!
        }
        _OutlinedArrowDownIcon = ImageVector.Builder(
            name = "OutlinedArrowDownIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF1F1B13)),
                strokeLineWidth = 1f
            ) {
                moveTo(19.252f, 9.966f)
                lineTo(19.249f, 9.969f)
                lineTo(13.254f, 15.999f)
                curveTo(13.254f, 15.999f, 13.254f, 15.999f, 13.254f, 15.999f)
                curveTo(12.934f, 16.32f, 12.5f, 16.5f, 12.049f, 16.5f)
                curveTo(11.598f, 16.5f, 11.165f, 16.32f, 10.845f, 15.999f)
                lineTo(10.843f, 15.997f)
                lineTo(4.748f, 9.947f)
                lineTo(4.748f, 9.947f)
                lineTo(4.747f, 9.946f)
                curveTo(4.629f, 9.829f, 4.548f, 9.68f, 4.516f, 9.516f)
                curveTo(4.483f, 9.352f, 4.501f, 9.183f, 4.566f, 9.029f)
                lineTo(4.566f, 9.029f)
                lineTo(4.568f, 9.024f)
                curveTo(4.632f, 8.868f, 4.74f, 8.735f, 4.878f, 8.642f)
                curveTo(5.016f, 8.549f, 5.179f, 8.5f, 5.344f, 8.5f)
                horizontalLineTo(5.345f)
                horizontalLineTo(18.644f)
                curveTo(18.812f, 8.5f, 18.977f, 8.55f, 19.117f, 8.645f)
                curveTo(19.257f, 8.74f, 19.366f, 8.874f, 19.43f, 9.032f)
                lineTo(19.43f, 9.032f)
                lineTo(19.433f, 9.04f)
                curveTo(19.499f, 9.195f, 19.517f, 9.366f, 19.484f, 9.532f)
                curveTo(19.452f, 9.697f, 19.371f, 9.849f, 19.252f, 9.966f)
                close()
            }
        }.build()

        return _OutlinedArrowDownIcon!!
    }

@Suppress("ObjectPropertyName")
private var _OutlinedArrowDownIcon: ImageVector? = null
