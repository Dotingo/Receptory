package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val EditIcon: ImageVector
    get() {
        if (_EditIcon != null) {
            return _EditIcon!!
        }
        _EditIcon = ImageVector.Builder(
            name = "EditIcon",
            defaultWidth = 12.dp,
            defaultHeight = 12.dp,
            viewportWidth = 12f,
            viewportHeight = 12f
        ).apply {
            path(fill = SolidColor(Color(0xFF1F1B13))) {
                moveTo(11.4f, 1.564f)
                lineTo(10.416f, 0.598f)
                curveTo(10.227f, 0.408f, 10.003f, 0.258f, 9.755f, 0.155f)
                curveTo(9.508f, 0.053f, 9.243f, 0f, 8.976f, 0f)
                curveTo(8.708f, 0f, 8.444f, 0.053f, 8.196f, 0.155f)
                curveTo(7.949f, 0.258f, 7.725f, 0.408f, 7.536f, 0.598f)
                lineTo(0.858f, 7.277f)
                curveTo(0.582f, 7.556f, 0.364f, 7.887f, 0.217f, 8.25f)
                curveTo(0.07f, 8.614f, -0.004f, 9.003f, 0f, 9.395f)
                verticalLineTo(10.8f)
                curveTo(0f, 11.118f, 0.127f, 11.423f, 0.352f, 11.648f)
                curveTo(0.577f, 11.873f, 0.882f, 12f, 1.2f, 12f)
                horizontalLineTo(2.61f)
                curveTo(3.004f, 12.001f, 3.394f, 11.924f, 3.758f, 11.772f)
                curveTo(4.122f, 11.621f, 4.451f, 11.398f, 4.728f, 11.118f)
                lineTo(11.4f, 4.444f)
                curveTo(11.592f, 4.256f, 11.744f, 4.031f, 11.847f, 3.782f)
                curveTo(11.95f, 3.534f, 12.002f, 3.267f, 12f, 2.998f)
                curveTo(12.001f, 2.731f, 11.948f, 2.467f, 11.845f, 2.22f)
                curveTo(11.742f, 1.974f, 11.591f, 1.751f, 11.4f, 1.564f)
                close()
                moveTo(3.858f, 10.278f)
                curveTo(3.52f, 10.613f, 3.062f, 10.801f, 2.586f, 10.8f)
                horizontalLineTo(1.2f)
                verticalLineTo(9.395f)
                curveTo(1.201f, 8.918f, 1.39f, 8.46f, 1.728f, 8.123f)
                lineTo(6.9f, 2.95f)
                lineTo(9.054f, 5.105f)
                lineTo(3.858f, 10.278f)
                close()
                moveTo(10.536f, 3.598f)
                lineTo(9.882f, 4.258f)
                lineTo(7.728f, 2.104f)
                lineTo(8.4f, 1.444f)
                curveTo(8.56f, 1.286f, 8.775f, 1.197f, 9f, 1.198f)
                curveTo(9.111f, 1.197f, 9.222f, 1.218f, 9.325f, 1.261f)
                curveTo(9.428f, 1.303f, 9.521f, 1.365f, 9.6f, 1.444f)
                lineTo(10.536f, 2.398f)
                curveTo(10.618f, 2.475f, 10.683f, 2.568f, 10.728f, 2.671f)
                curveTo(10.774f, 2.774f, 10.798f, 2.886f, 10.8f, 2.998f)
                curveTo(10.795f, 3.225f, 10.7f, 3.441f, 10.536f, 3.598f)
                close()
            }
        }.build()

        return _EditIcon!!
    }

@Suppress("ObjectPropertyName")
private var _EditIcon: ImageVector? = null
