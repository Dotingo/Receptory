package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val EmailIcon: ImageVector
    get() {
        if (_EmailIcon != null) {
            return _EmailIcon!!
        }
        _EmailIcon = ImageVector.Builder(
            name = "EmailIcon",
            defaultWidth = 20.dp,
            defaultHeight = 18.dp,
            viewportWidth = 20f,
            viewportHeight = 18f
        ).apply {
            path(fill = SolidColor(Color(0xFF231A04))) {
                moveTo(14f, 0f)
                horizontalLineTo(6f)
                curveTo(4.409f, 0f, 2.883f, 0.632f, 1.757f, 1.757f)
                curveTo(0.632f, 2.883f, 0f, 4.409f, 0f, 6f)
                verticalLineTo(12f)
                curveTo(0f, 13.591f, 0.632f, 15.117f, 1.757f, 16.243f)
                curveTo(2.883f, 17.368f, 4.409f, 18f, 6f, 18f)
                horizontalLineTo(14f)
                curveTo(15.591f, 18f, 17.117f, 17.368f, 18.243f, 16.243f)
                curveTo(19.368f, 15.117f, 20f, 13.591f, 20f, 12f)
                verticalLineTo(6f)
                curveTo(20f, 4.409f, 19.368f, 2.883f, 18.243f, 1.757f)
                curveTo(17.117f, 0.632f, 15.591f, 0f, 14f, 0f)
                close()
                moveTo(14.78f, 6.63f)
                lineTo(13.38f, 8.38f)
                curveTo(12.974f, 8.887f, 12.46f, 9.295f, 11.875f, 9.576f)
                curveTo(11.29f, 9.856f, 10.649f, 10.001f, 10f, 10f)
                curveTo(9.351f, 10.003f, 8.709f, 9.859f, 8.124f, 9.579f)
                curveTo(7.538f, 9.298f, 7.024f, 8.888f, 6.62f, 8.38f)
                lineTo(5.21f, 6.63f)
                curveTo(5.127f, 6.527f, 5.066f, 6.409f, 5.03f, 6.282f)
                curveTo(4.993f, 6.155f, 4.982f, 6.023f, 4.997f, 5.892f)
                curveTo(5.011f, 5.761f, 5.052f, 5.634f, 5.116f, 5.519f)
                curveTo(5.18f, 5.403f, 5.267f, 5.302f, 5.37f, 5.22f)
                curveTo(5.473f, 5.137f, 5.591f, 5.076f, 5.718f, 5.04f)
                curveTo(5.845f, 5.003f, 5.977f, 4.992f, 6.108f, 5.007f)
                curveTo(6.239f, 5.021f, 6.366f, 5.062f, 6.481f, 5.126f)
                curveTo(6.597f, 5.19f, 6.698f, 5.277f, 6.78f, 5.38f)
                lineTo(8.18f, 7.13f)
                curveTo(8.396f, 7.405f, 8.673f, 7.626f, 8.989f, 7.777f)
                curveTo(9.304f, 7.928f, 9.65f, 8.004f, 10f, 8f)
                curveTo(10.348f, 8.003f, 10.692f, 7.926f, 11.006f, 7.775f)
                curveTo(11.32f, 7.624f, 11.595f, 7.404f, 11.81f, 7.13f)
                lineTo(13.21f, 5.38f)
                curveTo(13.292f, 5.277f, 13.393f, 5.19f, 13.509f, 5.126f)
                curveTo(13.624f, 5.062f, 13.751f, 5.021f, 13.882f, 5.007f)
                curveTo(14.013f, 4.992f, 14.146f, 5.003f, 14.272f, 5.04f)
                curveTo(14.399f, 5.076f, 14.517f, 5.137f, 14.62f, 5.22f)
                curveTo(14.724f, 5.302f, 14.81f, 5.403f, 14.874f, 5.519f)
                curveTo(14.938f, 5.634f, 14.979f, 5.761f, 14.993f, 5.892f)
                curveTo(15.008f, 6.023f, 14.997f, 6.155f, 14.96f, 6.282f)
                curveTo(14.924f, 6.409f, 14.863f, 6.527f, 14.78f, 6.63f)
                close()
            }
        }.build()

        return _EmailIcon!!
    }

@Suppress("ObjectPropertyName")
private var _EmailIcon: ImageVector? = null
