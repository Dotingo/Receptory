package dev.dotingo.receptory.ui.icons.favorite

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val FavoriteBoldIcon: ImageVector
    get() {
        if (_FavoriteBoldIcon != null) {
            return _FavoriteBoldIcon!!
        }
        _FavoriteBoldIcon = ImageVector.Builder(
            name = "FavoriteBold",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF303037))) {
                moveTo(13.19f, 20.75f)
                curveTo(12.814f, 20.912f, 12.409f, 20.996f, 12f, 20.996f)
                curveTo(11.591f, 20.996f, 11.186f, 20.912f, 10.81f, 20.75f)
                curveTo(7.71f, 19.41f, 2f, 15.15f, 2f, 8.58f)
                curveTo(1.999f, 7.849f, 2.141f, 7.124f, 2.42f, 6.448f)
                curveTo(2.699f, 5.771f, 3.108f, 5.157f, 3.624f, 4.638f)
                curveTo(4.141f, 4.12f, 4.754f, 3.709f, 5.429f, 3.428f)
                curveTo(6.104f, 3.147f, 6.829f, 3.001f, 7.56f, 3f)
                curveTo(8.424f, 3f, 9.275f, 3.203f, 10.047f, 3.592f)
                curveTo(10.818f, 3.981f, 11.487f, 4.545f, 12f, 5.24f)
                curveTo(12.698f, 4.303f, 13.674f, 3.611f, 14.789f, 3.261f)
                curveTo(15.903f, 2.911f, 17.1f, 2.922f, 18.208f, 3.292f)
                curveTo(19.316f, 3.663f, 20.279f, 4.373f, 20.96f, 5.322f)
                curveTo(21.641f, 6.272f, 22.005f, 7.412f, 22f, 8.58f)
                curveTo(22f, 15.15f, 16.29f, 19.41f, 13.19f, 20.75f)
                close()
            }
        }.build()

        return _FavoriteBoldIcon!!
    }

@Suppress("ObjectPropertyName")
private var _FavoriteBoldIcon: ImageVector? = null
