package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val KcalIcon: ImageVector
    get() {
        if (_KcalIcon != null) {
            return _KcalIcon!!
        }
        _KcalIcon = ImageVector.Builder(
            name = "Kcal",
            defaultWidth = 14.dp,
            defaultHeight = 14.dp,
            viewportWidth = 14f,
            viewportHeight = 14f
        ).apply {
            path(fill = SolidColor(Color(0xFF1F1B13))) {
                moveTo(12.466f, 3.908f)
                curveTo(12.454f, 3.874f, 12.436f, 3.842f, 12.413f, 3.815f)
                curveTo(12.16f, 3.39f, 11.802f, 3.037f, 11.375f, 2.788f)
                lineTo(8.972f, 1.4f)
                curveTo(8.371f, 1.056f, 7.692f, 0.875f, 7f, 0.875f)
                curveTo(6.308f, 0.875f, 5.628f, 1.056f, 5.028f, 1.4f)
                lineTo(2.625f, 2.788f)
                curveTo(2.197f, 3.037f, 1.84f, 3.39f, 1.587f, 3.815f)
                curveTo(1.564f, 3.842f, 1.546f, 3.874f, 1.534f, 3.908f)
                curveTo(1.292f, 4.337f, 1.166f, 4.821f, 1.167f, 5.314f)
                verticalLineTo(8.68f)
                curveTo(1.167f, 9.192f, 1.301f, 9.695f, 1.557f, 10.138f)
                curveTo(1.813f, 10.581f, 2.181f, 10.95f, 2.625f, 11.206f)
                lineTo(5.022f, 12.594f)
                curveTo(5.625f, 12.938f, 6.306f, 13.119f, 7f, 13.119f)
                curveTo(7.694f, 13.119f, 8.375f, 12.938f, 8.977f, 12.594f)
                lineTo(11.375f, 11.206f)
                curveTo(11.818f, 10.95f, 12.186f, 10.581f, 12.442f, 10.138f)
                curveTo(12.698f, 9.695f, 12.833f, 9.192f, 12.833f, 8.68f)
                verticalLineTo(5.314f)
                curveTo(12.834f, 4.821f, 12.707f, 4.337f, 12.466f, 3.908f)
                close()
                moveTo(6.417f, 11.888f)
                curveTo(6.131f, 11.83f, 5.857f, 11.726f, 5.606f, 11.579f)
                lineTo(3.208f, 10.196f)
                curveTo(2.942f, 10.043f, 2.721f, 9.822f, 2.567f, 9.555f)
                curveTo(2.414f, 9.289f, 2.333f, 8.987f, 2.333f, 8.68f)
                verticalLineTo(5.314f)
                curveTo(2.333f, 5.208f, 2.345f, 5.102f, 2.368f, 4.999f)
                lineTo(6.417f, 7.332f)
                verticalLineTo(11.888f)
                close()
                moveTo(2.946f, 3.984f)
                curveTo(3.027f, 3.913f, 3.115f, 3.851f, 3.208f, 3.797f)
                lineTo(5.612f, 2.409f)
                curveTo(6.034f, 2.167f, 6.513f, 2.04f, 7f, 2.041f)
                curveTo(7.487f, 2.042f, 7.965f, 2.168f, 8.388f, 2.409f)
                lineTo(10.792f, 3.797f)
                curveTo(10.884f, 3.849f, 10.97f, 3.912f, 11.048f, 3.984f)
                lineTo(7f, 6.317f)
                lineTo(2.946f, 3.984f)
                close()
                moveTo(11.696f, 8.68f)
                curveTo(11.696f, 8.987f, 11.615f, 9.289f, 11.462f, 9.555f)
                curveTo(11.308f, 9.822f, 11.087f, 10.043f, 10.821f, 10.196f)
                lineTo(8.423f, 11.579f)
                curveTo(8.171f, 11.723f, 7.897f, 11.828f, 7.612f, 11.888f)
                verticalLineTo(7.332f)
                lineTo(11.661f, 4.999f)
                curveTo(11.684f, 5.102f, 11.696f, 5.208f, 11.696f, 5.314f)
                verticalLineTo(8.68f)
                close()
            }
        }.build()

        return _KcalIcon!!
    }

@Suppress("ObjectPropertyName")
private var _KcalIcon: ImageVector? = null


