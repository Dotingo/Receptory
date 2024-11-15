package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val PlayIcon: ImageVector
    get() {
        if (_PlayIcon != null) {
            return _PlayIcon!!
        }
        _PlayIcon = ImageVector.Builder(
            name = "PlayIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFDF96))) {
                moveTo(19f, 12f)
                curveTo(19.001f, 12.627f, 18.846f, 13.245f, 18.547f, 13.797f)
                curveTo(18.248f, 14.349f, 17.816f, 14.818f, 17.29f, 15.16f)
                curveTo(16.16f, 15.89f, 14.6f, 16.91f, 13.89f, 17.35f)
                curveTo(13.18f, 17.79f, 11.15f, 19f, 10.18f, 19.54f)
                curveTo(9.674f, 19.834f, 9.101f, 19.992f, 8.517f, 19.998f)
                curveTo(7.932f, 20.005f, 7.356f, 19.861f, 6.844f, 19.579f)
                curveTo(6.331f, 19.297f, 5.901f, 18.887f, 5.593f, 18.39f)
                curveTo(5.286f, 17.893f, 5.113f, 17.324f, 5.09f, 16.74f)
                curveTo(5.05f, 15.67f, 5f, 14f, 5f, 12f)
                curveTo(5f, 10.44f, 5f, 8.85f, 5.08f, 7.27f)
                curveTo(5.103f, 6.685f, 5.277f, 6.115f, 5.584f, 5.617f)
                curveTo(5.892f, 5.118f, 6.324f, 4.708f, 6.837f, 4.425f)
                curveTo(7.35f, 4.142f, 7.927f, 3.997f, 8.513f, 4.003f)
                curveTo(9.099f, 4.009f, 9.673f, 4.167f, 10.18f, 4.46f)
                curveTo(11.18f, 5.04f, 13.18f, 6.23f, 14.02f, 6.74f)
                curveTo(14.86f, 7.25f, 16.2f, 8.13f, 17.29f, 8.84f)
                curveTo(17.816f, 9.182f, 18.248f, 9.651f, 18.547f, 10.203f)
                curveTo(18.846f, 10.754f, 19.001f, 11.372f, 19f, 12f)
                close()
            }
        }.build()

        return _PlayIcon!!
    }

@Suppress("ObjectPropertyName")
private var _PlayIcon: ImageVector? = null
