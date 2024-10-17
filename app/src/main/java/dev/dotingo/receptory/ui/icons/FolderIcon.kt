package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val FolderIcon: ImageVector
    get() {
        if (_FolderIcon != null) {
            return _FolderIcon!!
        }
        _FolderIcon = ImageVector.Builder(
            name = "FolderIcon",
            defaultWidth = 20.dp,
            defaultHeight = 18.dp,
            viewportWidth = 20f,
            viewportHeight = 18f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF1F1B13)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(9.336f, 2.237f)
                curveTo(9.045f, 2.081f, 8.72f, 2f, 8.39f, 2f)
                lineTo(5f, 2f)
                curveTo(4.204f, 2f, 3.441f, 2.316f, 2.879f, 2.879f)
                curveTo(2.316f, 3.441f, 2f, 4.204f, 2f, 5f)
                verticalLineTo(13f)
                curveTo(2f, 13.796f, 2.316f, 14.559f, 2.879f, 15.121f)
                curveTo(3.441f, 15.684f, 4.204f, 16f, 5f, 16f)
                horizontalLineTo(15f)
                curveTo(15.796f, 16f, 16.559f, 15.684f, 17.121f, 15.121f)
                curveTo(17.684f, 14.559f, 18f, 13.796f, 18f, 13f)
                verticalLineTo(8f)
                curveTo(18f, 7.204f, 17.684f, 6.441f, 17.121f, 5.879f)
                curveTo(16.559f, 5.316f, 15.796f, 5f, 15f, 5f)
                lineTo(13.068f, 5f)
                curveTo(12.575f, 4.999f, 12.09f, 4.877f, 11.656f, 4.644f)
                curveTo(11.223f, 4.412f, 10.853f, 4.076f, 10.58f, 3.668f)
                lineTo(10.057f, 2.893f)
                curveTo(9.874f, 2.619f, 9.627f, 2.393f, 9.336f, 2.237f)
                close()
                moveTo(8.39f, 0f)
                curveTo(9.05f, -0f, 9.699f, 0.163f, 10.281f, 0.475f)
                curveTo(10.861f, 0.786f, 11.356f, 1.235f, 11.721f, 1.783f)
                lineTo(12.242f, 2.555f)
                curveTo(12.333f, 2.692f, 12.456f, 2.804f, 12.601f, 2.881f)
                curveTo(12.746f, 2.959f, 12.907f, 3f, 13.071f, 3f)
                horizontalLineTo(15f)
                curveTo(16.326f, 3f, 17.598f, 3.527f, 18.535f, 4.464f)
                curveTo(19.473f, 5.402f, 20f, 6.674f, 20f, 8f)
                verticalLineTo(13f)
                curveTo(20f, 14.326f, 19.473f, 15.598f, 18.535f, 16.535f)
                curveTo(17.598f, 17.473f, 16.326f, 18f, 15f, 18f)
                horizontalLineTo(5f)
                curveTo(3.674f, 18f, 2.402f, 17.473f, 1.464f, 16.535f)
                curveTo(0.527f, 15.598f, 0f, 14.326f, 0f, 13f)
                verticalLineTo(5f)
                curveTo(0f, 3.674f, 0.527f, 2.402f, 1.464f, 1.464f)
                curveTo(2.402f, 0.527f, 3.674f, 0f, 5f, 0f)
                horizontalLineTo(8.39f)
                curveTo(8.39f, 0f, 8.39f, 0f, 8.39f, 0f)
                close()
            }
        }.build()

        return _FolderIcon!!
    }

@Suppress("ObjectPropertyName")
private var _FolderIcon: ImageVector? = null
