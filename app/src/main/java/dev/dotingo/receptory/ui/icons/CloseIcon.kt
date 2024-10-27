package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val CloseIcon: ImageVector
    get() {
        if (_CloseIcon != null) {
            return _CloseIcon!!
        }
        _CloseIcon = ImageVector.Builder(
            name = "Close",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFFEAE1D4))) {
                moveTo(19.71f, 18.295f)
                curveTo(19.896f, 18.482f, 20.001f, 18.736f, 20.001f, 19f)
                curveTo(20.001f, 19.264f, 19.896f, 19.517f, 19.71f, 19.704f)
                curveTo(19.617f, 19.799f, 19.507f, 19.874f, 19.385f, 19.925f)
                curveTo(19.263f, 19.977f, 19.132f, 20.004f, 19f, 20.004f)
                curveTo(18.868f, 20.004f, 18.737f, 19.977f, 18.615f, 19.925f)
                curveTo(18.493f, 19.874f, 18.383f, 19.799f, 18.29f, 19.704f)
                lineTo(12f, 13.417f)
                lineTo(5.71f, 19.704f)
                curveTo(5.617f, 19.799f, 5.507f, 19.874f, 5.385f, 19.925f)
                curveTo(5.263f, 19.977f, 5.132f, 20.004f, 5f, 20.004f)
                curveTo(4.868f, 20.004f, 4.737f, 19.977f, 4.615f, 19.925f)
                curveTo(4.493f, 19.874f, 4.383f, 19.799f, 4.29f, 19.704f)
                curveTo(4.104f, 19.517f, 3.999f, 19.264f, 3.999f, 19f)
                curveTo(3.999f, 18.736f, 4.104f, 18.482f, 4.29f, 18.295f)
                lineTo(10.59f, 12.007f)
                lineTo(4.29f, 5.709f)
                curveTo(4.104f, 5.522f, 3.999f, 5.269f, 3.999f, 5.005f)
                curveTo(3.999f, 4.741f, 4.104f, 4.487f, 4.29f, 4.3f)
                curveTo(4.383f, 4.206f, 4.494f, 4.132f, 4.615f, 4.081f)
                curveTo(4.737f, 4.03f, 4.868f, 4.004f, 5f, 4.004f)
                curveTo(5.132f, 4.004f, 5.263f, 4.03f, 5.385f, 4.081f)
                curveTo(5.506f, 4.132f, 5.617f, 4.206f, 5.71f, 4.3f)
                lineTo(12f, 10.588f)
                lineTo(18.29f, 4.3f)
                curveTo(18.383f, 4.206f, 18.494f, 4.132f, 18.615f, 4.081f)
                curveTo(18.737f, 4.03f, 18.868f, 4.004f, 19f, 4.004f)
                curveTo(19.132f, 4.004f, 19.263f, 4.03f, 19.385f, 4.081f)
                curveTo(19.507f, 4.132f, 19.617f, 4.206f, 19.71f, 4.3f)
                curveTo(19.896f, 4.487f, 20.001f, 4.741f, 20.001f, 5.005f)
                curveTo(20.001f, 5.269f, 19.896f, 5.522f, 19.71f, 5.709f)
                lineTo(13.42f, 12.007f)
                lineTo(19.71f, 18.295f)
                close()
            }
        }.build()

        return _CloseIcon!!
    }

@Suppress("ObjectPropertyName")
private var _CloseIcon: ImageVector? = null
