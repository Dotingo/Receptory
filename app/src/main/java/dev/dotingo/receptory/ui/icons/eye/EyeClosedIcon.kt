package dev.dotingo.receptory.ui.icons.eye

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val EyeClosedIcon: ImageVector
    get() {
        if (_EyeClosedIcon != null) {
            return _EyeClosedIcon!!
        }
        _EyeClosedIcon = ImageVector.Builder(
            name = "EyeClosedIcon",
            defaultWidth = 20.dp,
            defaultHeight = 18.dp,
            viewportWidth = 20f,
            viewportHeight = 18f
        ).apply {
            path(fill = SolidColor(Color(0xFF231A04))) {
                moveTo(19.977f, 8.781f)
                curveTo(20.008f, 8.922f, 20.008f, 9.069f, 19.977f, 9.211f)
                curveTo(18.83f, 14.391f, 14.739f, 18.001f, 10f, 18.001f)
                curveTo(8.871f, 18.001f, 7.751f, 17.791f, 6.698f, 17.381f)
                curveTo(6.543f, 17.322f, 6.406f, 17.225f, 6.298f, 17.099f)
                curveTo(6.19f, 16.973f, 6.115f, 16.823f, 6.079f, 16.661f)
                curveTo(6.044f, 16.497f, 6.05f, 16.327f, 6.097f, 16.166f)
                curveTo(6.144f, 16.006f, 6.23f, 15.859f, 6.349f, 15.741f)
                lineTo(9.611f, 12.471f)
                horizontalLineTo(10f)
                curveTo(10.921f, 12.471f, 11.805f, 12.106f, 12.459f, 11.456f)
                curveTo(13.113f, 10.807f, 13.484f, 9.924f, 13.492f, 9.001f)
                curveTo(13.492f, 8.871f, 13.492f, 8.741f, 13.492f, 8.611f)
                lineTo(16.984f, 5.051f)
                curveTo(17.091f, 4.943f, 17.222f, 4.861f, 17.367f, 4.811f)
                curveTo(17.511f, 4.762f, 17.665f, 4.746f, 17.816f, 4.765f)
                curveTo(17.967f, 4.785f, 18.112f, 4.838f, 18.239f, 4.923f)
                curveTo(18.367f, 5.007f, 18.473f, 5.119f, 18.55f, 5.251f)
                curveTo(19.208f, 6.345f, 19.69f, 7.536f, 19.977f, 8.781f)
                close()
            }
            path(fill = SolidColor(Color(0xFF231A04))) {
                moveTo(18.69f, 0.291f)
                curveTo(18.503f, 0.105f, 18.25f, 0f, 17.986f, 0f)
                curveTo(17.723f, 0f, 17.47f, 0.105f, 17.283f, 0.291f)
                lineTo(15.657f, 1.921f)
                curveTo(14.032f, 0.677f, 12.045f, 0.002f, 10f, 0.001f)
                curveTo(5.271f, 0.001f, 1.181f, 3.611f, 0.023f, 8.791f)
                curveTo(-0.008f, 8.932f, -0.008f, 9.079f, 0.023f, 9.221f)
                curveTo(0.471f, 11.275f, 1.444f, 13.177f, 2.847f, 14.741f)
                lineTo(1.32f, 16.291f)
                curveTo(1.227f, 16.384f, 1.153f, 16.494f, 1.102f, 16.616f)
                curveTo(1.051f, 16.738f, 1.025f, 16.869f, 1.025f, 17.001f)
                curveTo(1.025f, 17.133f, 1.051f, 17.264f, 1.102f, 17.385f)
                curveTo(1.153f, 17.507f, 1.227f, 17.618f, 1.32f, 17.711f)
                curveTo(1.506f, 17.896f, 1.757f, 18f, 2.019f, 18.001f)
                curveTo(2.15f, 18.002f, 2.28f, 17.976f, 2.402f, 17.927f)
                curveTo(2.523f, 17.877f, 2.634f, 17.803f, 2.727f, 17.711f)
                lineTo(18.69f, 1.711f)
                curveTo(18.783f, 1.618f, 18.857f, 1.507f, 18.908f, 1.385f)
                curveTo(18.959f, 1.264f, 18.985f, 1.133f, 18.985f, 1.001f)
                curveTo(18.985f, 0.869f, 18.959f, 0.738f, 18.908f, 0.616f)
                curveTo(18.857f, 0.494f, 18.783f, 0.384f, 18.69f, 0.291f)
                close()
                moveTo(6.927f, 10.671f)
                curveTo(6.573f, 10.007f, 6.442f, 9.246f, 6.552f, 8.501f)
                curveTo(6.662f, 7.756f, 7.008f, 7.067f, 7.54f, 6.535f)
                curveTo(8.071f, 6.002f, 8.759f, 5.655f, 9.502f, 5.545f)
                curveTo(10.245f, 5.434f, 11.004f, 5.566f, 11.666f, 5.921f)
                lineTo(6.927f, 10.671f)
                close()
            }
        }.build()

        return _EyeClosedIcon!!
    }

@Suppress("ObjectPropertyName")
private var _EyeClosedIcon: ImageVector? = null