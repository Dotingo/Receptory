package dev.dotingo.receptory.ui.icons.favorite

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val FavoriteOutlinedIcon: ImageVector
    get() {
        if (_FavoriteOutlinedIcon != null) {
            return _FavoriteOutlinedIcon!!
        }
        _FavoriteOutlinedIcon = ImageVector.Builder(
            name = "FavoriteOutlined",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF303037)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(9.193f, 5.182f)
                curveTo(8.391f, 4.932f, 7.532f, 4.94f, 6.735f, 5.205f)
                curveTo(5.939f, 5.471f, 5.246f, 5.98f, 4.755f, 6.661f)
                curveTo(4.264f, 7.342f, 4f, 8.16f, 4f, 9f)
                curveTo(4f, 11.828f, 5.299f, 14.09f, 6.956f, 15.786f)
                curveTo(8.614f, 17.482f, 10.574f, 18.549f, 11.748f, 18.968f)
                curveTo(11.913f, 19.014f, 12.087f, 19.014f, 12.252f, 18.968f)
                curveTo(13.426f, 18.549f, 15.386f, 17.482f, 17.044f, 15.786f)
                curveTo(18.701f, 14.09f, 20f, 11.828f, 20f, 9f)
                curveTo(20f, 8.16f, 19.736f, 7.342f, 19.245f, 6.661f)
                curveTo(18.754f, 5.98f, 18.061f, 5.471f, 17.265f, 5.205f)
                curveTo(16.469f, 4.94f, 15.609f, 4.932f, 14.807f, 5.182f)
                curveTo(14.006f, 5.432f, 13.304f, 5.928f, 12.8f, 6.6f)
                curveTo(12.611f, 6.852f, 12.315f, 7f, 12f, 7f)
                curveTo(11.685f, 7f, 11.389f, 6.852f, 11.2f, 6.6f)
                curveTo(10.696f, 5.928f, 9.994f, 5.432f, 9.193f, 5.182f)
                close()
                moveTo(6.103f, 3.308f)
                curveTo(7.297f, 2.91f, 8.587f, 2.897f, 9.789f, 3.273f)
                curveTo(10.611f, 3.53f, 11.365f, 3.959f, 12f, 4.528f)
                curveTo(12.635f, 3.959f, 13.389f, 3.53f, 14.211f, 3.273f)
                curveTo(15.413f, 2.897f, 16.703f, 2.91f, 17.897f, 3.308f)
                curveTo(19.092f, 3.706f, 20.131f, 4.47f, 20.868f, 5.492f)
                curveTo(21.604f, 6.513f, 22f, 7.741f, 22f, 9f)
                curveTo(22f, 12.492f, 20.384f, 15.23f, 18.474f, 17.184f)
                curveTo(16.576f, 19.126f, 14.332f, 20.356f, 12.892f, 20.863f)
                curveTo(12.878f, 20.868f, 12.863f, 20.873f, 12.849f, 20.877f)
                curveTo(12.295f, 21.044f, 11.705f, 21.044f, 11.151f, 20.877f)
                curveTo(11.137f, 20.873f, 11.122f, 20.868f, 11.108f, 20.863f)
                curveTo(9.668f, 20.356f, 7.424f, 19.126f, 5.526f, 17.184f)
                curveTo(3.616f, 15.23f, 2f, 12.492f, 2f, 9f)
                curveTo(2f, 7.741f, 2.396f, 6.513f, 3.133f, 5.492f)
                curveTo(3.869f, 4.47f, 4.908f, 3.706f, 6.103f, 3.308f)
                close()
            }
        }.build()

        return _FavoriteOutlinedIcon!!
    }

@Suppress("ObjectPropertyName")
private var _FavoriteOutlinedIcon: ImageVector? = null
