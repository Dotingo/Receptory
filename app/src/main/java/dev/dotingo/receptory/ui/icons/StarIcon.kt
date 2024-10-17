package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val StarIcon: ImageVector
    get() {
        if (_StarIcon != null) {
            return _StarIcon!!
        }
        _StarIcon = ImageVector.Builder(
            name = "VariantsBold",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF303037))) {
                moveTo(14.087f, 3.319f)
                lineTo(15.476f, 6.129f)
                curveTo(15.645f, 6.472f, 15.895f, 6.769f, 16.205f, 6.993f)
                curveTo(16.515f, 7.218f, 16.874f, 7.364f, 17.253f, 7.418f)
                lineTo(20.341f, 7.862f)
                curveTo(20.789f, 7.918f, 21.211f, 8.101f, 21.559f, 8.389f)
                curveTo(21.906f, 8.677f, 22.164f, 9.058f, 22.302f, 9.488f)
                curveTo(22.439f, 9.917f, 22.451f, 10.377f, 22.336f, 10.814f)
                curveTo(22.221f, 11.25f, 21.984f, 11.644f, 21.652f, 11.95f)
                lineTo(19.43f, 14.172f)
                curveTo(19.156f, 14.438f, 18.951f, 14.768f, 18.833f, 15.132f)
                curveTo(18.715f, 15.496f, 18.688f, 15.883f, 18.753f, 16.26f)
                lineTo(19.286f, 19.337f)
                curveTo(19.34f, 19.767f, 19.276f, 20.203f, 19.099f, 20.599f)
                curveTo(18.922f, 20.994f, 18.64f, 21.334f, 18.284f, 21.58f)
                curveTo(17.928f, 21.827f, 17.51f, 21.97f, 17.078f, 21.996f)
                curveTo(16.645f, 22.021f, 16.214f, 21.928f, 15.831f, 21.725f)
                lineTo(13.065f, 20.27f)
                curveTo(12.723f, 20.089f, 12.342f, 19.994f, 11.955f, 19.994f)
                curveTo(11.567f, 19.994f, 11.186f, 20.089f, 10.844f, 20.27f)
                lineTo(8.078f, 21.725f)
                curveTo(7.688f, 21.931f, 7.249f, 22.024f, 6.809f, 21.993f)
                curveTo(6.37f, 21.961f, 5.948f, 21.807f, 5.591f, 21.548f)
                curveTo(5.235f, 21.289f, 4.958f, 20.935f, 4.793f, 20.526f)
                curveTo(4.628f, 20.118f, 4.581f, 19.671f, 4.657f, 19.237f)
                lineTo(5.179f, 16.16f)
                curveTo(5.248f, 15.786f, 5.226f, 15.4f, 5.114f, 15.036f)
                curveTo(5.001f, 14.673f, 4.803f, 14.342f, 4.535f, 14.071f)
                lineTo(2.313f, 11.85f)
                curveTo(1.998f, 11.542f, 1.775f, 11.153f, 1.669f, 10.726f)
                curveTo(1.564f, 10.299f, 1.579f, 9.85f, 1.714f, 9.431f)
                curveTo(1.849f, 9.012f, 2.098f, 8.639f, 2.434f, 8.354f)
                curveTo(2.769f, 8.069f, 3.177f, 7.884f, 3.613f, 7.818f)
                lineTo(6.712f, 7.373f)
                curveTo(7.09f, 7.317f, 7.449f, 7.171f, 7.758f, 6.947f)
                curveTo(8.067f, 6.722f, 8.318f, 6.427f, 8.489f, 6.085f)
                lineTo(9.866f, 3.275f)
                curveTo(10.068f, 2.887f, 10.373f, 2.562f, 10.748f, 2.337f)
                curveTo(11.123f, 2.112f, 11.553f, 1.996f, 11.99f, 2f)
                curveTo(12.428f, 2.005f, 12.855f, 2.13f, 13.225f, 2.363f)
                curveTo(13.595f, 2.596f, 13.894f, 2.927f, 14.087f, 3.319f)
                close()
            }
        }.build()

        return _StarIcon!!
    }

@Suppress("ObjectPropertyName")
private var _StarIcon: ImageVector? = null
