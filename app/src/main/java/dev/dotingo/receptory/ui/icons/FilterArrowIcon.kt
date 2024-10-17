package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val FilterArrowIcon: ImageVector
    get() {
        if (_FilterArrowIcon != null) {
            return _FilterArrowIcon!!
        }
        _FilterArrowIcon = ImageVector.Builder(
            name = "FilterArrow",
            defaultWidth = 20.dp,
            defaultHeight = 20.dp,
            viewportWidth = 20f,
            viewportHeight = 20f
        ).apply {
            path(fill = SolidColor(Color(0xFF303037))) {
                moveTo(5.242f, 18.087f)
                lineTo(1.91f, 14.755f)
                curveTo(1.833f, 14.677f, 1.771f, 14.584f, 1.73f, 14.483f)
                curveTo(1.689f, 14.381f, 1.668f, 14.273f, 1.668f, 14.163f)
                curveTo(1.669f, 13.944f, 1.756f, 13.735f, 1.91f, 13.58f)
                curveTo(1.987f, 13.502f, 2.079f, 13.44f, 2.181f, 13.398f)
                curveTo(2.282f, 13.355f, 2.391f, 13.333f, 2.501f, 13.333f)
                curveTo(2.611f, 13.333f, 2.72f, 13.355f, 2.822f, 13.398f)
                curveTo(2.923f, 13.44f, 3.015f, 13.502f, 3.093f, 13.58f)
                lineTo(5.001f, 15.488f)
                lineTo(5.001f, 2.5f)
                curveTo(5.001f, 2.279f, 5.088f, 2.067f, 5.245f, 1.911f)
                curveTo(5.401f, 1.754f, 5.613f, 1.667f, 5.834f, 1.667f)
                curveTo(6.055f, 1.667f, 6.266f, 1.754f, 6.423f, 1.911f)
                curveTo(6.579f, 2.067f, 6.667f, 2.279f, 6.667f, 2.5f)
                lineTo(6.667f, 15.488f)
                lineTo(8.574f, 13.58f)
                curveTo(8.652f, 13.502f, 8.743f, 13.441f, 8.844f, 13.398f)
                curveTo(8.945f, 13.356f, 9.054f, 13.334f, 9.163f, 13.334f)
                curveTo(9.384f, 13.333f, 9.596f, 13.42f, 9.753f, 13.576f)
                curveTo(9.831f, 13.653f, 9.893f, 13.745f, 9.935f, 13.845f)
                curveTo(9.977f, 13.946f, 9.999f, 14.055f, 10f, 14.164f)
                curveTo(10f, 14.385f, 9.913f, 14.598f, 9.757f, 14.755f)
                lineTo(6.425f, 18.087f)
                curveTo(6.348f, 18.165f, 6.255f, 18.227f, 6.154f, 18.269f)
                curveTo(6.052f, 18.312f, 5.944f, 18.333f, 5.834f, 18.333f)
                curveTo(5.724f, 18.333f, 5.615f, 18.312f, 5.513f, 18.269f)
                curveTo(5.412f, 18.227f, 5.32f, 18.165f, 5.242f, 18.087f)
                close()
            }
            path(fill = SolidColor(Color(0xFF303037))) {
                moveTo(14.165f, 18.329f)
                curveTo(14.055f, 18.329f, 13.947f, 18.307f, 13.846f, 18.265f)
                curveTo(13.745f, 18.223f, 13.653f, 18.162f, 13.575f, 18.084f)
                curveTo(13.498f, 18.007f, 13.437f, 17.915f, 13.395f, 17.814f)
                curveTo(13.353f, 17.713f, 13.332f, 17.605f, 13.332f, 17.495f)
                lineTo(13.332f, 4.516f)
                lineTo(11.424f, 6.424f)
                curveTo(11.346f, 6.502f, 11.254f, 6.564f, 11.153f, 6.606f)
                curveTo(11.051f, 6.648f, 10.942f, 6.67f, 10.832f, 6.67f)
                curveTo(10.722f, 6.67f, 10.613f, 6.648f, 10.512f, 6.606f)
                curveTo(10.41f, 6.564f, 10.318f, 6.502f, 10.241f, 6.424f)
                curveTo(10.164f, 6.346f, 10.102f, 6.253f, 10.061f, 6.152f)
                curveTo(10.02f, 6.05f, 9.999f, 5.942f, 9.999f, 5.832f)
                curveTo(9.999f, 5.723f, 10.02f, 5.614f, 10.061f, 5.512f)
                curveTo(10.102f, 5.411f, 10.164f, 5.319f, 10.241f, 5.241f)
                lineTo(13.573f, 1.908f)
                curveTo(13.651f, 1.831f, 13.743f, 1.77f, 13.845f, 1.729f)
                curveTo(13.946f, 1.687f, 14.055f, 1.666f, 14.165f, 1.667f)
                curveTo(14.506f, 1.667f, 14.506f, 1.667f, 18.088f, 5.241f)
                curveTo(18.245f, 5.398f, 18.333f, 5.61f, 18.333f, 5.832f)
                curveTo(18.333f, 6.054f, 18.245f, 6.267f, 18.088f, 6.424f)
                curveTo(17.932f, 6.581f, 17.719f, 6.669f, 17.497f, 6.669f)
                curveTo(17.275f, 6.669f, 17.062f, 6.581f, 16.906f, 6.424f)
                lineTo(14.998f, 4.508f)
                lineTo(14.998f, 17.495f)
                curveTo(14.998f, 17.716f, 14.91f, 17.928f, 14.754f, 18.084f)
                curveTo(14.597f, 18.241f, 14.386f, 18.329f, 14.165f, 18.329f)
                close()
            }
        }.build()

        return _FilterArrowIcon!!
    }

@Suppress("ObjectPropertyName")
private var _FilterArrowIcon: ImageVector? = null
