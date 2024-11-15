package dev.dotingo.receptory.ui.icons.arrows

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val RefreshIcon: ImageVector
    get() {
        if (_RefreshIcon != null) {
            return _RefreshIcon!!
        }
        _RefreshIcon = ImageVector.Builder(
            name = "Refresh",
            defaultWidth = 15.dp,
            defaultHeight = 15.dp,
            viewportWidth = 15f,
            viewportHeight = 15f
        ).apply {
            group {
                path(fill = SolidColor(Color(0xFFE6C26C))) {
                    moveTo(12.328f, 11.417f)
                    curveTo(11.532f, 12.407f, 10.452f, 13.131f, 9.233f, 13.493f)
                    curveTo(8.015f, 13.854f, 6.714f, 13.835f, 5.507f, 13.438f)
                    curveTo(4.299f, 13.041f, 3.241f, 12.286f, 2.475f, 11.272f)
                    curveTo(1.709f, 10.259f, 1.27f, 9.036f, 1.218f, 7.767f)
                    lineTo(1.031f, 7.955f)
                    curveTo(0.911f, 8.057f, 0.758f, 8.11f, 0.6f, 8.104f)
                    curveTo(0.443f, 8.098f, 0.294f, 8.033f, 0.183f, 7.922f)
                    curveTo(0.072f, 7.811f, 0.007f, 7.662f, 0f, 7.505f)
                    curveTo(-0.006f, 7.348f, 0.048f, 7.194f, 0.15f, 7.075f)
                    lineTo(1.399f, 5.827f)
                    curveTo(1.516f, 5.711f, 1.674f, 5.646f, 1.839f, 5.646f)
                    curveTo(2.004f, 5.646f, 2.163f, 5.711f, 2.28f, 5.827f)
                    lineTo(3.529f, 7.075f)
                    curveTo(3.645f, 7.192f, 3.71f, 7.35f, 3.71f, 7.515f)
                    curveTo(3.71f, 7.68f, 3.645f, 7.838f, 3.529f, 7.955f)
                    curveTo(3.412f, 8.071f, 3.253f, 8.136f, 3.088f, 8.136f)
                    curveTo(2.923f, 8.136f, 2.765f, 8.071f, 2.648f, 7.955f)
                    lineTo(2.467f, 7.774f)
                    curveTo(2.519f, 8.785f, 2.878f, 9.756f, 3.496f, 10.559f)
                    curveTo(4.114f, 11.361f, 4.962f, 11.957f, 5.927f, 12.267f)
                    curveTo(6.891f, 12.577f, 7.928f, 12.587f, 8.898f, 12.294f)
                    curveTo(9.869f, 12.002f, 10.727f, 11.422f, 11.36f, 10.631f)
                    curveTo(11.411f, 10.567f, 11.475f, 10.514f, 11.547f, 10.474f)
                    curveTo(11.619f, 10.435f, 11.699f, 10.411f, 11.78f, 10.402f)
                    curveTo(11.862f, 10.393f, 11.945f, 10.401f, 12.024f, 10.424f)
                    curveTo(12.103f, 10.447f, 12.176f, 10.486f, 12.24f, 10.537f)
                    curveTo(12.304f, 10.589f, 12.357f, 10.653f, 12.396f, 10.725f)
                    curveTo(12.436f, 10.797f, 12.461f, 10.876f, 12.469f, 10.958f)
                    curveTo(12.478f, 11.039f, 12.47f, 11.122f, 12.447f, 11.201f)
                    curveTo(12.424f, 11.28f, 12.385f, 11.353f, 12.334f, 11.417f)
                    horizontalLineTo(12.328f)
                    close()
                }
                path(fill = SolidColor(Color(0xFFE6C26C))) {
                    moveTo(14.763f, 7.955f)
                    lineTo(13.514f, 9.202f)
                    curveTo(13.456f, 9.26f, 13.387f, 9.306f, 13.31f, 9.337f)
                    curveTo(13.234f, 9.368f, 13.153f, 9.384f, 13.071f, 9.383f)
                    curveTo(12.988f, 9.384f, 12.907f, 9.368f, 12.831f, 9.337f)
                    curveTo(12.755f, 9.306f, 12.686f, 9.26f, 12.627f, 9.202f)
                    lineTo(11.378f, 7.955f)
                    curveTo(11.276f, 7.835f, 11.223f, 7.682f, 11.229f, 7.525f)
                    curveTo(11.235f, 7.368f, 11.3f, 7.219f, 11.411f, 7.108f)
                    curveTo(11.522f, 6.997f, 11.671f, 6.932f, 11.829f, 6.925f)
                    curveTo(11.986f, 6.919f, 12.139f, 6.973f, 12.259f, 7.075f)
                    lineTo(12.434f, 7.25f)
                    curveTo(12.381f, 6.239f, 12.023f, 5.267f, 11.405f, 4.465f)
                    curveTo(10.787f, 3.662f, 9.939f, 3.066f, 8.974f, 2.756f)
                    curveTo(8.009f, 2.446f, 6.973f, 2.437f, 6.003f, 2.729f)
                    curveTo(5.032f, 3.021f, 4.174f, 3.602f, 3.541f, 4.393f)
                    curveTo(3.438f, 4.521f, 3.288f, 4.604f, 3.125f, 4.622f)
                    curveTo(2.961f, 4.641f, 2.796f, 4.594f, 2.667f, 4.492f)
                    curveTo(2.603f, 4.441f, 2.549f, 4.377f, 2.51f, 4.305f)
                    curveTo(2.47f, 4.233f, 2.446f, 4.154f, 2.437f, 4.072f)
                    curveTo(2.428f, 3.99f, 2.436f, 3.908f, 2.459f, 3.829f)
                    curveTo(2.483f, 3.75f, 2.521f, 3.677f, 2.573f, 3.613f)
                    curveTo(3.367f, 2.613f, 4.449f, 1.88f, 5.672f, 1.513f)
                    curveTo(6.896f, 1.146f, 8.203f, 1.163f, 9.417f, 1.562f)
                    curveTo(10.63f, 1.96f, 11.692f, 2.721f, 12.46f, 3.742f)
                    curveTo(13.227f, 4.762f, 13.663f, 5.993f, 13.708f, 7.268f)
                    lineTo(13.901f, 7.075f)
                    curveTo(13.957f, 7.01f, 14.026f, 6.957f, 14.104f, 6.919f)
                    curveTo(14.181f, 6.882f, 14.266f, 6.861f, 14.351f, 6.857f)
                    curveTo(14.438f, 6.854f, 14.523f, 6.869f, 14.603f, 6.9f)
                    curveTo(14.684f, 6.931f, 14.756f, 6.979f, 14.817f, 7.04f)
                    curveTo(14.878f, 7.1f, 14.926f, 7.173f, 14.957f, 7.253f)
                    curveTo(14.988f, 7.333f, 15.003f, 7.419f, 15f, 7.505f)
                    curveTo(14.996f, 7.591f, 14.975f, 7.675f, 14.938f, 7.752f)
                    curveTo(14.9f, 7.83f, 14.847f, 7.899f, 14.782f, 7.955f)
                    horizontalLineTo(14.763f)
                    close()
                }
            }
        }.build()

        return _RefreshIcon!!
    }

@Suppress("ObjectPropertyName")
private var _RefreshIcon: ImageVector? = null
