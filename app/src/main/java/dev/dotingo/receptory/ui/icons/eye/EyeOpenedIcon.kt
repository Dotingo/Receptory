package dev.dotingo.receptory.ui.icons.eye

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val EyeOpenedIcon: ImageVector
    get() {
        if (_EyeOpenedIcon != null) {
            return _EyeOpenedIcon!!
        }
        _EyeOpenedIcon = ImageVector.Builder(
            name = "EyeIcon",
            defaultWidth = 20.dp,
            defaultHeight = 18.dp,
            viewportWidth = 20f,
            viewportHeight = 18f
        ).apply {
            path(fill = SolidColor(Color(0xFF231A04))) {
                moveTo(10f, 0f)
                curveTo(5.271f, 0f, 1.171f, 3.62f, 0.023f, 8.79f)
                curveTo(-0.008f, 8.932f, -0.008f, 9.078f, 0.023f, 9.22f)
                curveTo(1.171f, 14.39f, 5.271f, 18f, 10f, 18f)
                curveTo(14.729f, 18f, 18.829f, 14.39f, 19.977f, 9.22f)
                curveTo(20.008f, 9.078f, 20.008f, 8.932f, 19.977f, 8.79f)
                curveTo(18.829f, 3.62f, 14.739f, 0f, 10f, 0f)
                close()
                moveTo(10f, 12f)
                curveTo(9.408f, 12f, 8.829f, 11.824f, 8.337f, 11.494f)
                curveTo(7.845f, 11.165f, 7.461f, 10.696f, 7.235f, 10.148f)
                curveTo(7.008f, 9.6f, 6.949f, 8.997f, 7.065f, 8.415f)
                curveTo(7.18f, 7.833f, 7.465f, 7.298f, 7.884f, 6.879f)
                curveTo(8.302f, 6.459f, 8.836f, 6.173f, 9.416f, 6.058f)
                curveTo(9.997f, 5.942f, 10.599f, 6.001f, 11.145f, 6.228f)
                curveTo(11.692f, 6.455f, 12.16f, 6.84f, 12.489f, 7.333f)
                curveTo(12.818f, 7.827f, 12.993f, 8.407f, 12.993f, 9f)
                curveTo(12.993f, 9.796f, 12.678f, 10.559f, 12.116f, 11.121f)
                curveTo(11.555f, 11.684f, 10.794f, 12f, 10f, 12f)
                close()
            }
        }.build()

        return _EyeOpenedIcon!!
    }

@Suppress("ObjectPropertyName")
private var _EyeOpenedIcon: ImageVector? = null
