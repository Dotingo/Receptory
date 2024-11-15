package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val ClearInputIcon: ImageVector
    get() {
        if (_ClearInputIcon != null) {
            return _ClearInputIcon!!
        }
        _ClearInputIcon = ImageVector.Builder(
            name = "ClearInputIcon",
            defaultWidth = 35.dp,
            defaultHeight = 26.dp,
            viewportWidth = 35f,
            viewportHeight = 26f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFDCBE)),
                strokeLineWidth = 2f
            ) {
                moveTo(16.322f, 1f)
                horizontalLineTo(30f)
                curveTo(32.209f, 1f, 34f, 2.791f, 34f, 5f)
                verticalLineTo(21f)
                curveTo(34f, 23.209f, 32.209f, 25f, 30f, 25f)
                horizontalLineTo(16.322f)
                curveTo(14.77f, 25f, 13.279f, 24.399f, 12.161f, 23.322f)
                lineTo(5.182f, 16.602f)
                curveTo(3.139f, 14.635f, 3.139f, 11.365f, 5.182f, 9.398f)
                lineTo(12.161f, 2.678f)
                curveTo(13.279f, 1.601f, 14.77f, 1f, 16.322f, 1f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFDCBE))) {
                moveTo(28.746f, 18.504f)
                curveTo(28.909f, 18.668f, 29f, 18.89f, 29f, 19.121f)
                curveTo(29f, 19.352f, 28.909f, 19.574f, 28.746f, 19.738f)
                curveTo(28.665f, 19.82f, 28.568f, 19.886f, 28.461f, 19.931f)
                curveTo(28.355f, 19.976f, 28.24f, 20f, 28.124f, 20f)
                curveTo(28.009f, 20f, 27.894f, 19.976f, 27.788f, 19.931f)
                curveTo(27.681f, 19.886f, 27.584f, 19.82f, 27.503f, 19.738f)
                lineTo(22f, 14.236f)
                lineTo(16.497f, 19.738f)
                curveTo(16.416f, 19.82f, 16.319f, 19.886f, 16.212f, 19.931f)
                curveTo(16.106f, 19.976f, 15.991f, 20f, 15.876f, 20f)
                curveTo(15.76f, 20f, 15.645f, 19.976f, 15.539f, 19.931f)
                curveTo(15.432f, 19.886f, 15.335f, 19.82f, 15.254f, 19.738f)
                curveTo(15.092f, 19.574f, 15f, 19.352f, 15f, 19.121f)
                curveTo(15f, 18.89f, 15.092f, 18.668f, 15.254f, 18.504f)
                lineTo(20.766f, 13.002f)
                lineTo(15.254f, 7.492f)
                curveTo(15.092f, 7.328f, 15f, 7.106f, 15f, 6.875f)
                curveTo(15f, 6.644f, 15.092f, 6.423f, 15.254f, 6.259f)
                curveTo(15.336f, 6.177f, 15.432f, 6.112f, 15.539f, 6.067f)
                curveTo(15.646f, 6.023f, 15.76f, 6f, 15.876f, 6f)
                curveTo(15.991f, 6f, 16.105f, 6.023f, 16.212f, 6.067f)
                curveTo(16.319f, 6.112f, 16.416f, 6.177f, 16.497f, 6.259f)
                lineTo(22f, 11.76f)
                lineTo(27.503f, 6.259f)
                curveTo(27.584f, 6.177f, 27.681f, 6.112f, 27.788f, 6.067f)
                curveTo(27.895f, 6.023f, 28.009f, 6f, 28.124f, 6f)
                curveTo(28.24f, 6f, 28.354f, 6.023f, 28.461f, 6.067f)
                curveTo(28.567f, 6.112f, 28.664f, 6.177f, 28.746f, 6.259f)
                curveTo(28.909f, 6.423f, 29f, 6.644f, 29f, 6.875f)
                curveTo(29f, 7.106f, 28.909f, 7.328f, 28.746f, 7.492f)
                lineTo(23.242f, 13.002f)
                lineTo(28.746f, 18.504f)
                close()
            }
        }.build()

        return _ClearInputIcon!!
    }

@Suppress("ObjectPropertyName")
private var _ClearInputIcon: ImageVector? = null
