package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val TimerIcon: ImageVector
    get() {
        if (_TimerIcon != null) {
            return _TimerIcon!!
        }
        _TimerIcon = ImageVector.Builder(
            name = "Timer",
            defaultWidth = 10.dp,
            defaultHeight = 12.dp,
            viewportWidth = 10f,
            viewportHeight = 12f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF3E2E00)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(5f, 3.083f)
                curveTo(2.906f, 3.083f, 1.208f, 4.781f, 1.208f, 6.875f)
                curveTo(1.208f, 8.969f, 2.906f, 10.667f, 5f, 10.667f)
                curveTo(7.094f, 10.667f, 8.792f, 8.969f, 8.792f, 6.875f)
                curveTo(8.792f, 4.781f, 7.094f, 3.083f, 5f, 3.083f)
                close()
                moveTo(0.042f, 6.875f)
                curveTo(0.042f, 4.137f, 2.262f, 1.917f, 5f, 1.917f)
                curveTo(7.738f, 1.917f, 9.958f, 4.137f, 9.958f, 6.875f)
                curveTo(9.958f, 9.613f, 7.738f, 11.833f, 5f, 11.833f)
                curveTo(2.262f, 11.833f, 0.042f, 9.613f, 0.042f, 6.875f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3E2E00)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(5f, 4.25f)
                curveTo(5.322f, 4.25f, 5.583f, 4.511f, 5.583f, 4.833f)
                verticalLineTo(6.583f)
                curveTo(5.583f, 6.905f, 5.322f, 7.167f, 5f, 7.167f)
                curveTo(4.678f, 7.167f, 4.417f, 6.905f, 4.417f, 6.583f)
                verticalLineTo(4.833f)
                curveTo(4.417f, 4.511f, 4.678f, 4.25f, 5f, 4.25f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF3E2E00)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(2.667f, 0.75f)
                curveTo(2.667f, 0.428f, 2.928f, 0.167f, 3.25f, 0.167f)
                horizontalLineTo(6.75f)
                curveTo(7.072f, 0.167f, 7.333f, 0.428f, 7.333f, 0.75f)
                curveTo(7.333f, 1.072f, 7.072f, 1.333f, 6.75f, 1.333f)
                horizontalLineTo(3.25f)
                curveTo(2.928f, 1.333f, 2.667f, 1.072f, 2.667f, 0.75f)
                close()
            }
        }.build()

        return _TimerIcon!!
    }

@Suppress("ObjectPropertyName")
private var _TimerIcon: ImageVector? = null
