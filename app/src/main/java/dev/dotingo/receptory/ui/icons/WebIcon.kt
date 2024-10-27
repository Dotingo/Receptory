package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val WebIcon: ImageVector
    get() {
        if (_WebIcon != null) {
            return _WebIcon!!
        }
        _WebIcon = ImageVector.Builder(
            name = "Web",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(fill = SolidColor(Color(0xFFEAE1D4))) {
                moveTo(8f, 0f)
                curveTo(6.418f, 0f, 4.871f, 0.469f, 3.555f, 1.348f)
                curveTo(2.24f, 2.227f, 1.214f, 3.477f, 0.609f, 4.939f)
                curveTo(0.003f, 6.4f, -0.155f, 8.009f, 0.154f, 9.561f)
                curveTo(0.462f, 11.113f, 1.224f, 12.538f, 2.343f, 13.657f)
                curveTo(3.462f, 14.776f, 4.887f, 15.538f, 6.439f, 15.846f)
                curveTo(7.991f, 16.155f, 9.6f, 15.997f, 11.061f, 15.391f)
                curveTo(12.523f, 14.785f, 13.773f, 13.76f, 14.652f, 12.445f)
                curveTo(15.531f, 11.129f, 16f, 9.582f, 16f, 8f)
                curveTo(16f, 5.878f, 15.157f, 3.843f, 13.657f, 2.343f)
                curveTo(12.157f, 0.843f, 10.122f, 0f, 8f, 0f)
                close()
                moveTo(14.352f, 7.2f)
                horizontalLineTo(11.64f)
                curveTo(11.474f, 5.3f, 10.848f, 3.468f, 9.816f, 1.864f)
                curveTo(11.009f, 2.216f, 12.073f, 2.909f, 12.879f, 3.856f)
                curveTo(13.684f, 4.804f, 14.196f, 5.966f, 14.352f, 7.2f)
                close()
                moveTo(10.04f, 7.2f)
                horizontalLineTo(5.992f)
                curveTo(6.175f, 5.331f, 6.87f, 3.548f, 8f, 2.048f)
                curveTo(9.138f, 3.547f, 9.843f, 5.329f, 10.04f, 7.2f)
                close()
                moveTo(5.992f, 8.8f)
                horizontalLineTo(10.056f)
                curveTo(9.861f, 10.672f, 9.155f, 12.454f, 8.016f, 13.952f)
                curveTo(6.88f, 12.453f, 6.18f, 10.671f, 5.992f, 8.8f)
                close()
                moveTo(6.2f, 1.864f)
                curveTo(5.17f, 3.468f, 4.55f, 5.3f, 4.392f, 7.2f)
                horizontalLineTo(1.648f)
                curveTo(1.804f, 5.966f, 2.316f, 4.804f, 3.121f, 3.856f)
                curveTo(3.927f, 2.909f, 4.991f, 2.216f, 6.184f, 1.864f)
                horizontalLineTo(6.2f)
                close()
                moveTo(1.648f, 8.8f)
                horizontalLineTo(4.392f)
                curveTo(4.553f, 10.698f, 5.17f, 12.528f, 6.192f, 14.136f)
                curveTo(4.998f, 13.785f, 3.932f, 13.093f, 3.125f, 12.145f)
                curveTo(2.318f, 11.198f, 1.804f, 10.035f, 1.648f, 8.8f)
                close()
                moveTo(9.824f, 14.136f)
                curveTo(10.853f, 12.531f, 11.476f, 10.7f, 11.64f, 8.8f)
                horizontalLineTo(14.352f)
                curveTo(14.197f, 10.033f, 13.686f, 11.194f, 12.882f, 12.142f)
                curveTo(12.078f, 13.089f, 11.015f, 13.782f, 9.824f, 14.136f)
                close()
            }
        }.build()

        return _WebIcon!!
    }

@Suppress("ObjectPropertyName")
private var _WebIcon: ImageVector? = null
