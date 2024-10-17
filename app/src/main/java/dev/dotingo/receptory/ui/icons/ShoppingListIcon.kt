package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val ShoppingListIcon: ImageVector
    get() {
        if (_ShopListIcon != null) {
            return _ShopListIcon!!
        }
        _ShopListIcon = ImageVector.Builder(
            name = "ReceiptIcon",
            defaultWidth = 18.dp,
            defaultHeight = 20.dp,
            viewportWidth = 18f,
            viewportHeight = 20f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF1F1B13)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(10.49f, 2f)
                curveTo(10.221f, 2f, 9.958f, 2.08f, 9.735f, 2.229f)
                lineTo(9.077f, 2.666f)
                curveTo(9.076f, 2.667f, 9.076f, 2.667f, 9.075f, 2.667f)
                curveTo(8.613f, 2.977f, 8.075f, 3.151f, 7.519f, 3.173f)
                curveTo(6.963f, 3.194f, 6.412f, 3.06f, 5.928f, 2.786f)
                lineTo(5.925f, 2.785f)
                lineTo(5f, 2.258f)
                verticalLineTo(14.006f)
                lineTo(11f, 14.006f)
                curveTo(11.552f, 14.006f, 12f, 14.454f, 12f, 15.006f)
                verticalLineTo(16.004f)
                curveTo(12f, 16.533f, 12.21f, 17.041f, 12.585f, 17.415f)
                curveTo(12.96f, 17.789f, 13.469f, 18f, 14f, 18f)
                curveTo(14f, 18f, 14f, 18f, 14f, 18f)
                curveTo(14.531f, 18f, 15.04f, 17.789f, 15.415f, 17.415f)
                curveTo(15.79f, 17.041f, 16f, 16.533f, 16f, 16.004f)
                verticalLineTo(2.254f)
                lineTo(15.052f, 2.786f)
                curveTo(15.052f, 2.786f, 15.051f, 2.787f, 15.051f, 2.787f)
                curveTo(14.567f, 3.06f, 14.016f, 3.194f, 13.461f, 3.173f)
                curveTo(12.905f, 3.151f, 12.367f, 2.977f, 11.905f, 2.667f)
                curveTo(11.904f, 2.667f, 11.904f, 2.667f, 11.903f, 2.666f)
                lineTo(11.246f, 2.229f)
                curveTo(11.022f, 2.08f, 10.759f, 2f, 10.49f, 2f)
                close()
                moveTo(14f, 20f)
                curveTo(14f, 20f, 14f, 20f, 14f, 20f)
                lineTo(5f, 20f)
                curveTo(3.674f, 20f, 2.403f, 19.474f, 1.465f, 18.538f)
                curveTo(0.527f, 17.601f, 0f, 16.331f, 0f, 15.006f)
                curveTo(0f, 14.741f, 0.105f, 14.486f, 0.293f, 14.299f)
                curveTo(0.48f, 14.111f, 0.735f, 14.006f, 1f, 14.006f)
                lineTo(3f, 14.006f)
                verticalLineTo(1.696f)
                lineTo(3f, 1.681f)
                curveTo(3.004f, 1.393f, 3.083f, 1.111f, 3.228f, 0.862f)
                curveTo(3.372f, 0.613f, 3.579f, 0.406f, 3.827f, 0.259f)
                curveTo(4.075f, 0.113f, 4.356f, 0.033f, 4.644f, 0.027f)
                curveTo(4.932f, 0.021f, 5.216f, 0.089f, 5.47f, 0.224f)
                curveTo(5.478f, 0.229f, 5.487f, 0.233f, 5.495f, 0.238f)
                lineTo(6.912f, 1.045f)
                curveTo(6.912f, 1.045f, 6.913f, 1.045f, 6.913f, 1.046f)
                curveTo(7.075f, 1.137f, 7.258f, 1.181f, 7.443f, 1.174f)
                curveTo(7.629f, 1.167f, 7.809f, 1.108f, 7.963f, 1.005f)
                lineTo(7.966f, 1.003f)
                lineTo(8.625f, 0.564f)
                curveTo(8.626f, 0.564f, 8.625f, 0.564f, 8.625f, 0.564f)
                curveTo(9.178f, 0.196f, 9.827f, 0f, 10.49f, 0f)
                curveTo(11.153f, 0f, 11.802f, 0.196f, 12.354f, 0.564f)
                lineTo(13.017f, 1.005f)
                curveTo(13.171f, 1.108f, 13.351f, 1.167f, 13.537f, 1.174f)
                curveTo(13.722f, 1.181f, 13.906f, 1.136f, 14.068f, 1.045f)
                lineTo(14.07f, 1.044f)
                lineTo(15.51f, 0.235f)
                lineTo(15.53f, 0.224f)
                curveTo(15.784f, 0.089f, 16.068f, 0.021f, 16.356f, 0.027f)
                curveTo(16.644f, 0.033f, 16.925f, 0.113f, 17.173f, 0.259f)
                curveTo(17.421f, 0.406f, 17.628f, 0.613f, 17.772f, 0.862f)
                curveTo(17.917f, 1.111f, 17.996f, 1.393f, 18f, 1.681f)
                lineTo(18f, 1.696f)
                verticalLineTo(16.004f)
                curveTo(18f, 17.065f, 17.578f, 18.081f, 16.828f, 18.83f)
                curveTo(16.078f, 19.579f, 15.06f, 20f, 14f, 20f)
                close()
                moveTo(3.987f, 16.006f)
                lineTo(2.172f, 16.006f)
                curveTo(2.32f, 16.422f, 2.559f, 16.804f, 2.878f, 17.122f)
                curveTo(3.441f, 17.684f, 4.204f, 18f, 5f, 18f)
                lineTo(10.535f, 18f)
                curveTo(10.188f, 17.399f, 10f, 16.712f, 10f, 16.006f)
                lineTo(4.013f, 16.006f)
                curveTo(4.008f, 16.006f, 4.004f, 16.006f, 4f, 16.006f)
                curveTo(3.996f, 16.006f, 3.992f, 16.006f, 3.987f, 16.006f)
                close()
                moveTo(7f, 7.018f)
                curveTo(7f, 6.466f, 7.448f, 6.018f, 8f, 6.018f)
                horizontalLineTo(13f)
                curveTo(13.552f, 6.018f, 14f, 6.466f, 14f, 7.018f)
                curveTo(14f, 7.57f, 13.552f, 8.018f, 13f, 8.018f)
                horizontalLineTo(8f)
                curveTo(7.448f, 8.018f, 7f, 7.57f, 7f, 7.018f)
                close()
                moveTo(7f, 11.012f)
                curveTo(7f, 10.46f, 7.448f, 10.012f, 8f, 10.012f)
                horizontalLineTo(11f)
                curveTo(11.552f, 10.012f, 12f, 10.46f, 12f, 11.012f)
                curveTo(12f, 11.564f, 11.552f, 12.012f, 11f, 12.012f)
                horizontalLineTo(8f)
                curveTo(7.448f, 12.012f, 7f, 11.564f, 7f, 11.012f)
                close()
            }
        }.build()

        return _ShopListIcon!!
    }

@Suppress("ObjectPropertyName")
private var _ShopListIcon: ImageVector? = null
