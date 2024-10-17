package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val UserIcon: ImageVector
    get() {
        if (_UserIcon != null) {
            return _UserIcon!!
        }
        _UserIcon = ImageVector.Builder(
            name = "UserIcon",
            defaultWidth = 14.dp,
            defaultHeight = 20.dp,
            viewportWidth = 14f,
            viewportHeight = 20f
        ).apply {
            path(fill = SolidColor(Color(0xFF231A04))) {
                moveTo(7f, 20f)
                curveTo(10.866f, 20f, 14f, 17.761f, 14f, 15f)
                curveTo(14f, 12.239f, 10.866f, 10f, 7f, 10f)
                curveTo(3.134f, 10f, 0f, 12.239f, 0f, 15f)
                curveTo(0f, 17.761f, 3.134f, 20f, 7f, 20f)
                close()
            }
            path(fill = SolidColor(Color(0xFF231A04))) {
                moveTo(7f, 9f)
                curveTo(9.485f, 9f, 11.5f, 6.985f, 11.5f, 4.5f)
                curveTo(11.5f, 2.015f, 9.485f, 0f, 7f, 0f)
                curveTo(4.515f, 0f, 2.5f, 2.015f, 2.5f, 4.5f)
                curveTo(2.5f, 6.985f, 4.515f, 9f, 7f, 9f)
                close()
            }
        }.build()

        return _UserIcon!!
    }

@Suppress("ObjectPropertyName")
private var _UserIcon: ImageVector? = null