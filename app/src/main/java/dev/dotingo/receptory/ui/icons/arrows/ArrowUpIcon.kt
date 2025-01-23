package dev.dotingo.receptory.ui.icons.arrows

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val FilledArrowUpIcon: ImageVector
    get() {
        if (_FilledArrowUpIcon != null) {
            return _FilledArrowUpIcon!!
        }
        _FilledArrowUpIcon = ImageVector.Builder(
            name = "FilledArrowUpIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF1F1B13))) {
                moveTo(4.397f, 14.678f)
                lineTo(10.392f, 8.648f)
                curveTo(10.806f, 8.233f, 11.366f, 8f, 11.951f, 8f)
                curveTo(12.535f, 8f, 13.095f, 8.233f, 13.509f, 8.648f)
                lineTo(19.605f, 14.698f)
                curveTo(19.794f, 14.885f, 19.923f, 15.125f, 19.975f, 15.387f)
                curveTo(20.026f, 15.649f, 19.998f, 15.92f, 19.894f, 16.166f)
                curveTo(19.793f, 16.413f, 19.622f, 16.624f, 19.401f, 16.772f)
                curveTo(19.18f, 16.921f, 18.921f, 17f, 18.655f, 17f)
                lineTo(5.356f, 17f)
                curveTo(5.088f, 17f, 4.826f, 16.92f, 4.603f, 16.769f)
                curveTo(4.381f, 16.619f, 4.208f, 16.405f, 4.107f, 16.156f)
                curveTo(4.002f, 15.909f, 3.973f, 15.636f, 4.025f, 15.372f)
                curveTo(4.077f, 15.108f, 4.206f, 14.867f, 4.397f, 14.678f)
                close()
            }
        }.build()

        return _FilledArrowUpIcon!!
    }

@Suppress("ObjectPropertyName")
private var _FilledArrowUpIcon: ImageVector? = null


val OutlinedArrowUpIcon: ImageVector
    get() {
        if (_OutlinedArrowUpIcon != null) {
            return _OutlinedArrowUpIcon!!
        }
        _OutlinedArrowUpIcon = ImageVector.Builder(
            name = "OutlinedArrowUpIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF1F1B13)),
                strokeLineWidth = 1f
            ) {
                moveTo(4.748f, 15.033f)
                lineTo(4.751f, 15.031f)
                lineTo(10.746f, 9.001f)
                curveTo(10.746f, 9.001f, 10.746f, 9.001f, 10.746f, 9.001f)
                curveTo(11.066f, 8.68f, 11.5f, 8.5f, 11.951f, 8.5f)
                curveTo(12.402f, 8.5f, 12.835f, 8.68f, 13.155f, 9.001f)
                lineTo(13.157f, 9.003f)
                lineTo(19.252f, 15.053f)
                lineTo(19.252f, 15.053f)
                lineTo(19.253f, 15.054f)
                curveTo(19.371f, 15.171f, 19.452f, 15.32f, 19.484f, 15.484f)
                curveTo(19.517f, 15.648f, 19.499f, 15.817f, 19.434f, 15.971f)
                lineTo(19.434f, 15.971f)
                lineTo(19.432f, 15.976f)
                curveTo(19.368f, 16.132f, 19.26f, 16.265f, 19.122f, 16.358f)
                curveTo(18.984f, 16.451f, 18.821f, 16.5f, 18.656f, 16.5f)
                lineTo(18.655f, 16.5f)
                lineTo(5.356f, 16.5f)
                lineTo(5.356f, 16.5f)
                curveTo(5.188f, 16.5f, 5.023f, 16.45f, 4.883f, 16.355f)
                curveTo(4.743f, 16.26f, 4.634f, 16.126f, 4.57f, 15.968f)
                lineTo(4.57f, 15.968f)
                lineTo(4.567f, 15.96f)
                curveTo(4.501f, 15.805f, 4.483f, 15.634f, 4.516f, 15.468f)
                curveTo(4.548f, 15.303f, 4.629f, 15.151f, 4.748f, 15.033f)
                close()
            }
        }.build()

        return _OutlinedArrowUpIcon!!
    }

@Suppress("ObjectPropertyName")
private var _OutlinedArrowUpIcon: ImageVector? = null
