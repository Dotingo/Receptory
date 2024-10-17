package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val GoogleLogo: ImageVector
    get() {
        if (_GoogleLogo != null) {
            return _GoogleLogo!!
        }
        _GoogleLogo = ImageVector.Builder(
            name = "GoogleLogo",
            defaultWidth = 20.dp,
            defaultHeight = 20.dp,
            viewportWidth = 20f,
            viewportHeight = 20f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFC107))) {
                moveTo(19.684f, 8.066f)
                horizontalLineTo(18.889f)
                verticalLineTo(8.025f)
                horizontalLineTo(10f)
                verticalLineTo(11.975f)
                horizontalLineTo(15.582f)
                curveTo(14.767f, 14.275f, 12.579f, 15.926f, 10f, 15.926f)
                curveTo(6.727f, 15.926f, 4.074f, 13.273f, 4.074f, 10f)
                curveTo(4.074f, 6.727f, 6.727f, 4.074f, 10f, 4.074f)
                curveTo(11.511f, 4.074f, 12.885f, 4.644f, 13.931f, 5.575f)
                lineTo(16.725f, 2.781f)
                curveTo(14.961f, 1.137f, 12.602f, 0.123f, 10f, 0.123f)
                curveTo(4.546f, 0.123f, 0.124f, 4.546f, 0.124f, 10f)
                curveTo(0.124f, 15.454f, 4.546f, 19.877f, 10f, 19.877f)
                curveTo(15.454f, 19.877f, 19.877f, 15.454f, 19.877f, 10f)
                curveTo(19.877f, 9.338f, 19.809f, 8.691f, 19.684f, 8.066f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFF3D00))) {
                moveTo(1.262f, 5.403f)
                lineTo(4.507f, 7.783f)
                curveTo(5.385f, 5.609f, 7.512f, 4.074f, 10f, 4.074f)
                curveTo(11.511f, 4.074f, 12.885f, 4.644f, 13.931f, 5.575f)
                lineTo(16.725f, 2.781f)
                curveTo(14.961f, 1.137f, 12.601f, 0.123f, 10f, 0.123f)
                curveTo(6.206f, 0.123f, 2.917f, 2.265f, 1.262f, 5.403f)
                close()
            }
            path(fill = SolidColor(Color(0xFF4CAF50))) {
                moveTo(10f, 19.877f)
                curveTo(12.551f, 19.877f, 14.869f, 18.9f, 16.622f, 17.313f)
                lineTo(13.565f, 14.726f)
                curveTo(12.573f, 15.477f, 11.341f, 15.926f, 10f, 15.926f)
                curveTo(7.431f, 15.926f, 5.25f, 14.288f, 4.428f, 12.002f)
                lineTo(1.208f, 14.483f)
                curveTo(2.842f, 17.682f, 6.162f, 19.877f, 10f, 19.877f)
                close()
            }
            path(fill = SolidColor(Color(0xFF1976D2))) {
                moveTo(19.684f, 8.066f)
                horizontalLineTo(18.889f)
                verticalLineTo(8.025f)
                horizontalLineTo(10f)
                verticalLineTo(11.975f)
                horizontalLineTo(15.582f)
                curveTo(15.191f, 13.08f, 14.48f, 14.033f, 13.564f, 14.726f)
                lineTo(13.565f, 14.726f)
                lineTo(16.622f, 17.312f)
                curveTo(16.405f, 17.509f, 19.876f, 14.938f, 19.876f, 10f)
                curveTo(19.876f, 9.338f, 19.808f, 8.691f, 19.684f, 8.066f)
                close()
            }
        }.build()

        return _GoogleLogo!!
    }

@Suppress("ObjectPropertyName")
private var _GoogleLogo: ImageVector? = null
