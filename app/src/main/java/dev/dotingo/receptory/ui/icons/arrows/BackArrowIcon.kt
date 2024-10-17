package dev.dotingo.receptory.ui.icons.arrows

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val BackArrowIcon: ImageVector
    get() {
        if (_BackArrowIcon != null) {
            return _BackArrowIcon!!
        }
        _BackArrowIcon = ImageVector.Builder(
            name = "BackArrowIcon",
            defaultWidth = 7.dp,
            defaultHeight = 12.dp,
            viewportWidth = 7f,
            viewportHeight = 12f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF1F1B13)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(6.772f, 0.22f)
                curveTo(7.076f, 0.513f, 7.076f, 0.987f, 6.772f, 1.28f)
                lineTo(1.878f, 6f)
                lineTo(6.772f, 10.72f)
                curveTo(7.076f, 11.013f, 7.076f, 11.487f, 6.772f, 11.78f)
                curveTo(6.468f, 12.073f, 5.976f, 12.073f, 5.672f, 11.78f)
                lineTo(0.228f, 6.53f)
                curveTo(-0.076f, 6.237f, -0.076f, 5.763f, 0.228f, 5.47f)
                lineTo(5.672f, 0.22f)
                curveTo(5.976f, -0.073f, 6.468f, -0.073f, 6.772f, 0.22f)
                close()
            }
        }.build()

        return _BackArrowIcon!!
    }

@Suppress("ObjectPropertyName")
private var _BackArrowIcon: ImageVector? = null

