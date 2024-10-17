package dev.dotingo.receptory.ui.icons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val AppIcon: ImageVector
    get() {
        if (_AppIcon != null) {
            return _AppIcon!!
        }
        _AppIcon = ImageVector.Builder(
            name = "AppIcon",
            defaultWidth = 262.dp,
            defaultHeight = 236.dp,
            viewportWidth = 262f,
            viewportHeight = 236f
        ).apply {
            path(fill = SolidColor(Color(0xFF88C057))) {
                moveTo(100.84f, 48.31f)
                curveTo(102.01f, 48.27f, 103.22f, 47.94f, 104.36f, 47.06f)
                curveTo(106.33f, 45.53f, 107.33f, 42.98f, 106.74f, 40.56f)
                curveTo(106.07f, 37.86f, 103.75f, 36.02f, 101.13f, 35.82f)
                curveTo(100.31f, 33.42f, 97.76f, 32.03f, 95.27f, 32.7f)
                curveTo(95.27f, 32.7f, 95.26f, 32.7f, 95.25f, 32.71f)
                curveTo(95.2f, 32.32f, 95.07f, 31.94f, 94.84f, 31.56f)
                curveTo(94.19f, 30.51f, 92.95f, 29.9f, 91.72f, 30.07f)
                curveTo(90.72f, 30.22f, 89.92f, 30.81f, 89.46f, 31.59f)
                curveTo(88.72f, 31.22f, 87.85f, 31.09f, 86.98f, 31.32f)
                curveTo(85.13f, 31.82f, 84.03f, 33.73f, 84.52f, 35.58f)
                curveTo(84.53f, 35.59f, 84.53f, 35.59f, 84.53f, 35.6f)
                curveTo(82.32f, 36.37f, 81.05f, 38.73f, 81.66f, 41.03f)
                curveTo(81.76f, 41.38f, 81.89f, 41.71f, 82.06f, 42.01f)
                curveTo(79.41f, 42.84f, 77.85f, 45.62f, 78.58f, 48.33f)
                curveTo(78.83f, 49.25f, 79.31f, 50.04f, 79.93f, 50.67f)
                curveTo(79.29f, 51.57f, 79.04f, 52.74f, 79.35f, 53.89f)
                curveTo(79.78f, 55.5f, 81.18f, 56.58f, 82.75f, 56.71f)
                curveTo(84.32f, 63.33f, 87.7f, 66.57f, 93.64f, 70.58f)
                curveTo(93.74f, 70.65f, 93.85f, 70.72f, 93.96f, 70.79f)
                curveTo(93.98f, 70.81f, 94.01f, 70.82f, 94.03f, 70.84f)
                curveTo(96.48f, 72.45f, 100.3f, 69.69f, 98.67f, 67.25f)
                curveTo(89.94f, 54.23f, 105.56f, 52.27f, 105.56f, 52.27f)
                curveTo(105.56f, 52.27f, 104.82f, 47.82f, 100.89f, 48.65f)
                curveTo(100.86f, 48.54f, 100.84f, 48.43f, 100.81f, 48.32f)
                curveTo(100.82f, 48.32f, 100.83f, 48.31f, 100.84f, 48.31f)
                close()
            }
            path(fill = SolidColor(Color(0xFF659C35))) {
                moveTo(94.03f, 70.84f)
                curveTo(94.01f, 70.82f, 93.98f, 70.81f, 93.96f, 70.79f)
                curveTo(93.85f, 70.72f, 93.75f, 70.65f, 93.64f, 70.58f)
                curveTo(87.7f, 66.57f, 84.32f, 63.33f, 82.75f, 56.71f)
                curveTo(81.18f, 56.58f, 79.78f, 55.5f, 79.35f, 53.89f)
                curveTo(79.04f, 52.74f, 79.29f, 51.57f, 79.94f, 50.67f)
                curveTo(79.31f, 50.04f, 78.83f, 49.25f, 78.58f, 48.33f)
                curveTo(77.86f, 45.62f, 79.41f, 42.84f, 82.06f, 42.01f)
                curveTo(81.9f, 41.71f, 81.76f, 41.38f, 81.66f, 41.03f)
                curveTo(81.18f, 39.2f, 81.88f, 37.34f, 83.3f, 36.26f)
                curveTo(83.1f, 35.95f, 82.87f, 35.63f, 82.6f, 35.33f)
                curveTo(81.26f, 33.83f, 79.42f, 33.15f, 78.07f, 33.76f)
                curveTo(76.96f, 34.26f, 76.4f, 35.42f, 76.39f, 36.79f)
                curveTo(75.24f, 36.4f, 74.09f, 36.43f, 73.21f, 37.04f)
                curveTo(71.31f, 38.34f, 71.46f, 41.53f, 73.4f, 44f)
                curveTo(73.41f, 44f, 73.42f, 44.01f, 73.42f, 44.02f)
                curveTo(71.44f, 45.71f, 71.7f, 49.1f, 73.92f, 51.62f)
                curveTo(74.25f, 51.99f, 74.59f, 52.31f, 74.96f, 52.6f)
                curveTo(72.73f, 54.31f, 72.91f, 57.63f, 75.23f, 59.96f)
                curveTo(75.97f, 60.71f, 76.83f, 61.25f, 77.73f, 61.6f)
                curveTo(77.6f, 62.54f, 77.96f, 63.56f, 78.78f, 64.38f)
                curveTo(79.89f, 65.47f, 81.51f, 65.86f, 82.84f, 65.49f)
                curveTo(86.41f, 69.46f, 89.76f, 70.47f, 94.6f, 71.15f)
                curveTo(94.65f, 71.16f, 94.7f, 71.16f, 94.76f, 71.17f)
                curveTo(94.51f, 71.09f, 94.26f, 70.99f, 94.03f, 70.84f)
                close()
            }
            path(fill = SolidColor(Color(0xFF659C35))) {
                moveTo(90.05f, 49.15f)
                curveTo(90.06f, 49.05f, 90.06f, 48.95f, 90.03f, 48.86f)
                curveTo(90.2f, 47.43f, 90.52f, 46.19f, 90.92f, 45.12f)
                curveTo(93.94f, 43.6f, 96.62f, 43.95f, 96.68f, 43.96f)
                curveTo(96.78f, 43.98f, 96.89f, 43.97f, 96.98f, 43.94f)
                curveTo(97.25f, 43.87f, 97.47f, 43.64f, 97.52f, 43.34f)
                curveTo(97.58f, 42.94f, 97.3f, 42.56f, 96.9f, 42.5f)
                curveTo(96.79f, 42.48f, 94.6f, 42.17f, 91.83f, 43.13f)
                curveTo(92.82f, 41.32f, 93.86f, 40.35f, 94.02f, 40.21f)
                curveTo(94.92f, 39.43f, 96.6f, 39.67f, 97.18f, 39.83f)
                curveTo(97.31f, 39.86f, 97.44f, 39.86f, 97.56f, 39.83f)
                curveTo(97.81f, 39.76f, 98.01f, 39.57f, 98.09f, 39.31f)
                curveTo(98.19f, 38.92f, 97.97f, 38.51f, 97.57f, 38.4f)
                curveTo(97.48f, 38.38f, 95.63f, 37.88f, 94.04f, 38.51f)
                curveTo(94.02f, 37.89f, 94.19f, 37.28f, 94.29f, 37.03f)
                curveTo(94.45f, 36.65f, 94.27f, 36.22f, 93.89f, 36.07f)
                curveTo(93.52f, 35.91f, 93.08f, 36.09f, 92.93f, 36.47f)
                curveTo(92.9f, 36.54f, 92.3f, 38.03f, 92.68f, 39.45f)
                curveTo(91.82f, 40.37f, 89.65f, 43.01f, 88.81f, 47.38f)
                curveTo(88.77f, 47.62f, 88.73f, 47.87f, 88.69f, 48.12f)
                curveTo(86.39f, 47.31f, 86.08f, 44.86f, 86.07f, 44.74f)
                curveTo(86.02f, 44.33f, 85.66f, 44.04f, 85.26f, 44.08f)
                curveTo(84.85f, 44.12f, 84.56f, 44.49f, 84.6f, 44.89f)
                curveTo(84.74f, 46.24f, 85.75f, 48.83f, 88.54f, 49.62f)
                curveTo(88.42f, 51.32f, 88.52f, 53.29f, 88.98f, 55.52f)
                curveTo(88.99f, 55.59f, 89f, 55.66f, 89.03f, 55.72f)
                curveTo(89.37f, 57.28f, 89.9f, 58.97f, 90.64f, 60.78f)
                curveTo(89.61f, 60.52f, 89f, 59.91f, 88.96f, 59.86f)
                curveTo(88.68f, 59.57f, 88.22f, 59.55f, 87.92f, 59.83f)
                curveTo(87.62f, 60.1f, 87.6f, 60.57f, 87.88f, 60.87f)
                curveTo(87.94f, 60.93f, 89.21f, 62.28f, 91.35f, 62.36f)
                curveTo(92.62f, 65.04f, 94.38f, 67.96f, 96.81f, 71.12f)
                curveTo(97.29f, 70.96f, 97.73f, 70.7f, 98.1f, 70.38f)
                curveTo(98.09f, 70.37f, 98.09f, 70.37f, 98.09f, 70.36f)
                curveTo(95.54f, 67.08f, 93.73f, 64.07f, 92.46f, 61.33f)
                curveTo(92.46f, 61.31f, 92.45f, 61.29f, 92.44f, 61.28f)
                curveTo(91.49f, 59.22f, 90.85f, 57.31f, 90.46f, 55.55f)
                curveTo(91f, 53.58f, 93.91f, 52.73f, 93.94f, 52.72f)
                curveTo(94.33f, 52.61f, 94.56f, 52.2f, 94.45f, 51.81f)
                curveTo(94.34f, 51.42f, 93.94f, 51.19f, 93.55f, 51.3f)
                curveTo(93.55f, 51.3f, 93.54f, 51.3f, 93.54f, 51.3f)
                curveTo(93.43f, 51.33f, 91.41f, 51.92f, 90.06f, 53.37f)
                curveTo(89.88f, 51.9f, 89.88f, 50.56f, 89.98f, 49.34f)
                curveTo(90.01f, 49.28f, 90.03f, 49.22f, 90.05f, 49.15f)
                close()
            }
            path(fill = SolidColor(Color(0xFFD3D3D3))) {
                moveTo(237.45f, 97.19f)
                lineTo(236.08f, 97.11f)
                curveTo(236.08f, 97.11f, 231.24f, 111.24f, 221.95f, 115.66f)
                curveTo(213.74f, 119.56f, 214.29f, 128.55f, 218.72f, 131.1f)
                lineTo(220.76f, 132.28f)
                curveTo(220.89f, 132.35f, 221.04f, 132.4f, 221.16f, 132.49f)
                curveTo(226.26f, 136.52f, 238.6f, 128.09f, 235.35f, 115.14f)
                curveTo(233.99f, 108.93f, 235.18f, 105.53f, 237.19f, 98.11f)
                lineTo(237.45f, 97.19f)
                close()
            }
            path(fill = SolidColor(Color(0xFFE6E6E6))) {
                moveTo(221.95f, 115.66f)
                curveTo(228.72f, 112.44f, 233.13f, 104.05f, 235.02f, 99.75f)
                curveTo(229.85f, 103.57f, 228.55f, 105.41f, 222.99f, 106.18f)
                curveTo(216.85f, 106.83f, 212.7f, 110.64f, 210.65f, 114.76f)
                curveTo(210.51f, 114.97f, 205.82f, 123.75f, 212.65f, 127.54f)
                curveTo(213.81f, 128.18f, 215.08f, 128.5f, 216.36f, 128.5f)
                curveTo(214.49f, 124.73f, 215.6f, 118.67f, 221.95f, 115.66f)
                close()
            }
            path(fill = SolidColor(Color(0xFFE6E6E6))) {
                moveTo(238.81f, 115.31f)
                curveTo(237.12f, 111.16f, 235.65f, 108.67f, 235.49f, 104.57f)
                curveTo(234.67f, 108.17f, 234.46f, 111.05f, 235.35f, 115.14f)
                curveTo(238.14f, 126.24f, 229.47f, 134.02f, 223.72f, 133.47f)
                curveTo(224.22f, 134.04f, 224.81f, 134.54f, 225.49f, 134.95f)
                curveTo(232.18f, 138.97f, 237.45f, 130.52f, 237.56f, 130.29f)
                curveTo(240.09f, 126.46f, 241.32f, 120.96f, 238.81f, 115.31f)
                close()
            }
            path(fill = SolidColor(Color(0xFFD3D3D3))) {
                moveTo(210.61f, 114.73f)
                curveTo(212.66f, 110.62f, 216.82f, 106.81f, 222.96f, 106.16f)
                curveTo(225.73f, 105.78f, 227.44f, 105.12f, 229.11f, 104.09f)
                curveTo(226.09f, 104.34f, 222.68f, 103.82f, 219.64f, 104.24f)
                curveTo(213.49f, 104.89f, 207.94f, 107.9f, 205.89f, 112.01f)
                curveTo(205.75f, 112.22f, 200.66f, 120.62f, 209.29f, 125.6f)
                curveTo(209.81f, 125.9f, 210.35f, 126.11f, 210.91f, 126.26f)
                curveTo(206.67f, 122.08f, 210.48f, 114.92f, 210.61f, 114.73f)
                close()
            }
            path(fill = SolidColor(Color(0xFF88C057))) {
                moveTo(63.11f, 58.68f)
                curveTo(60.57f, 58.91f, 54.86f, 59.27f, 52.88f, 63.04f)
                curveTo(52.69f, 63.4f, 52.19f, 63.45f, 51.94f, 63.13f)
                lineTo(49.76f, 60.31f)
                curveTo(49.26f, 59.66f, 48.4f, 59.47f, 47.86f, 59.89f)
                lineTo(46.39f, 61.03f)
                curveTo(45.85f, 61.45f, 45.82f, 62.33f, 46.33f, 62.97f)
                lineTo(48.64f, 65.95f)
                curveTo(48.86f, 66.23f, 48.77f, 66.65f, 48.45f, 66.8f)
                curveTo(47.01f, 67.51f, 43.88f, 71.97f, 42.23f, 76.21f)
                curveTo(42f, 76.79f, 42.35f, 76.66f, 42.77f, 76.2f)
                curveTo(44f, 74.82f, 45.74f, 71.41f, 47.41f, 70.93f)
                curveTo(47.85f, 70.8f, 48.25f, 71.22f, 48.1f, 71.65f)
                curveTo(47.66f, 72.94f, 47.04f, 75.6f, 47.88f, 78.96f)
                curveTo(48.03f, 79.53f, 48.85f, 79.51f, 48.98f, 78.93f)
                curveTo(49.48f, 76.69f, 50.58f, 73.34f, 52.73f, 72.07f)
                curveTo(52.98f, 71.93f, 53.3f, 71.98f, 53.48f, 72.21f)
                curveTo(54.48f, 73.54f, 57.95f, 77.98f, 60.34f, 78.83f)
                curveTo(60.72f, 78.96f, 61.11f, 78.68f, 61.1f, 78.28f)
                curveTo(61.05f, 76.8f, 60.6f, 73.12f, 57.49f, 69.24f)
                curveTo(57.29f, 68.99f, 57.33f, 68.62f, 57.6f, 68.43f)
                curveTo(58.61f, 67.69f, 61.15f, 66.17f, 64.4f, 66.54f)
                curveTo(64.93f, 66.6f, 65.23f, 65.95f, 64.85f, 65.58f)
                curveTo(63.82f, 64.54f, 61.84f, 63.29f, 58.51f, 63.72f)
                curveTo(58f, 63.79f, 57.66f, 63.22f, 57.97f, 62.82f)
                curveTo(58.61f, 62.01f, 60.43f, 60.79f, 62.73f, 59.13f)
                curveTo(63.2f, 58.79f, 63.68f, 58.63f, 63.11f, 58.68f)
                close()
            }
            path(fill = SolidColor(Color(0xFFD13834))) {
                moveTo(63.05f, 58.7f)
                curveTo(62.96f, 58.7f, 62.87f, 58.73f, 62.78f, 58.78f)
                curveTo(62.49f, 58.95f, 62.21f, 59.13f, 61.91f, 59.3f)
                curveTo(61.81f, 59.36f, 61.71f, 59.43f, 61.61f, 59.49f)
                curveTo(59.66f, 60.96f, 58.58f, 62.06f, 57.98f, 62.81f)
                curveTo(57.66f, 63.21f, 57.99f, 63.78f, 58.51f, 63.72f)
                curveTo(61.84f, 63.29f, 63.82f, 64.54f, 64.85f, 65.57f)
                curveTo(65.23f, 65.95f, 64.93f, 66.6f, 64.4f, 66.53f)
                curveTo(61.15f, 66.16f, 58.61f, 67.69f, 57.6f, 68.42f)
                curveTo(57.33f, 68.61f, 57.29f, 68.99f, 57.49f, 69.24f)
                curveTo(60.6f, 73.12f, 61.05f, 76.79f, 61.1f, 78.28f)
                curveTo(61.11f, 78.68f, 60.72f, 78.96f, 60.34f, 78.83f)
                curveTo(57.95f, 77.98f, 54.48f, 73.54f, 53.48f, 72.21f)
                curveTo(53.3f, 71.98f, 52.98f, 71.92f, 52.73f, 72.07f)
                curveTo(50.58f, 73.34f, 49.48f, 76.69f, 48.98f, 78.93f)
                curveTo(48.85f, 79.5f, 48.03f, 79.53f, 47.88f, 78.96f)
                curveTo(47.04f, 75.61f, 47.66f, 72.95f, 48.1f, 71.65f)
                curveTo(48.25f, 71.22f, 47.85f, 70.8f, 47.41f, 70.93f)
                curveTo(47.09f, 71.02f, 46.77f, 71.16f, 46.45f, 71.33f)
                curveTo(46.39f, 71.36f, 46.33f, 71.4f, 46.29f, 71.45f)
                curveTo(44.85f, 72.91f, 43.51f, 74.44f, 42.3f, 76.04f)
                curveTo(42.25f, 76.11f, 42.21f, 76.19f, 42.19f, 76.27f)
                curveTo(40.99f, 81.7f, 42.01f, 87.39f, 45.5f, 91.89f)
                curveTo(52.08f, 100.38f, 64.95f, 101.41f, 74.25f, 94.19f)
                curveTo(83.55f, 86.98f, 85.76f, 74.26f, 79.18f, 65.77f)
                curveTo(75.31f, 60.78f, 69.27f, 58.37f, 63.05f, 58.7f)
                close()
            }
            path(fill = SolidColor(Color(0xFFED7161))) {
                moveTo(52.24f, 93.2f)
                curveTo(52.01f, 93.38f, 51.7f, 93.44f, 51.42f, 93.32f)
                curveTo(50.27f, 92.87f, 49.23f, 92.2f, 48.33f, 91.33f)
                curveTo(47.99f, 91.01f, 47.98f, 90.48f, 48.3f, 90.14f)
                curveTo(48.62f, 89.8f, 49.15f, 89.8f, 49.49f, 90.12f)
                curveTo(50.23f, 90.83f, 51.11f, 91.4f, 52.03f, 91.76f)
                curveTo(52.46f, 91.93f, 52.67f, 92.42f, 52.5f, 92.85f)
                curveTo(52.45f, 93f, 52.35f, 93.12f, 52.24f, 93.2f)
                close()
            }
            path(fill = SolidColor(Color(0xFFED7161))) {
                moveTo(47.36f, 88.56f)
                curveTo(47.34f, 88.57f, 47.33f, 88.58f, 47.31f, 88.59f)
                curveTo(46.93f, 88.85f, 46.41f, 88.74f, 46.15f, 88.36f)
                curveTo(45.53f, 87.43f, 45.05f, 86.44f, 44.72f, 85.41f)
                curveTo(44.58f, 84.97f, 44.82f, 84.5f, 45.26f, 84.35f)
                curveTo(45.7f, 84.21f, 46.17f, 84.46f, 46.31f, 84.9f)
                curveTo(46.6f, 85.78f, 47.01f, 86.63f, 47.54f, 87.43f)
                curveTo(47.79f, 87.8f, 47.7f, 88.29f, 47.36f, 88.56f)
                close()
            }
            path(fill = SolidColor(Color(0xFF9CE0B8))) {
                moveTo(38.56f, 116.27f)
                lineTo(35.17f, 116.05f)
                lineTo(34.44f, 119.65f)
                lineTo(43.02f, 120.21f)
                lineTo(38.56f, 116.27f)
                close()
            }
            path(fill = SolidColor(Color(0xFF54CE8E))) {
                moveTo(34.99f, 116.04f)
                lineTo(34.24f, 119.64f)
                lineTo(36.19f, 119.76f)
                lineTo(36.76f, 116.15f)
                lineTo(34.99f, 116.04f)
                close()
            }
            path(fill = SolidColor(Color(0xFF9CE0B8))) {
                moveTo(44.9f, 114.99f)
                lineTo(43.17f, 107.43f)
                lineTo(46.33f, 104.91f)
                lineTo(49.6f, 119.22f)
                lineTo(44.9f, 114.99f)
                close()
            }
            path(fill = SolidColor(Color(0xFF54CE8E))) {
                moveTo(43.17f, 107.43f)
                lineTo(43.54f, 109.05f)
                curveTo(44.44f, 108.95f, 45.68f, 108.53f, 46.83f, 107.11f)
                lineTo(46.33f, 104.91f)
                lineTo(43.17f, 107.43f)
                close()
            }
            path(fill = SolidColor(Color(0xFF9CE0B8))) {
                moveTo(37.43f, 109.17f)
                lineTo(54.68f, 122.58f)
                lineTo(51.44f, 126.75f)
                lineTo(34.59f, 113.66f)
                lineTo(37.43f, 109.17f)
                close()
            }
            path(fill = SolidColor(Color(0xFF54CE8E))) {
                moveTo(36.51f, 108.45f)
                lineTo(33.26f, 112.63f)
                lineTo(36.65f, 115.26f)
                lineTo(39.9f, 111.09f)
                lineTo(36.51f, 108.45f)
                close()
            }
            path(fill = SolidColor(Color(0xFF7CD8A4))) {
                moveTo(32.85f, 92.41f)
                curveTo(27.57f, 89.72f, 20.95f, 91.06f, 17.18f, 95.91f)
                curveTo(13.39f, 100.78f, 13.74f, 107.55f, 17.69f, 112.01f)
                curveTo(15.68f, 115.97f, 16.77f, 120.96f, 20.56f, 123.67f)
                curveTo(24.45f, 126.44f, 29.89f, 125.75f, 32.96f, 122.09f)
                curveTo(34.32f, 120.47f, 35.03f, 118.54f, 35.12f, 116.6f)
                curveTo(35.2f, 114.71f, 35.91f, 112.91f, 37.07f, 111.42f)
                curveTo(37.08f, 111.41f, 37.08f, 111.4f, 37.09f, 111.39f)
                curveTo(37.15f, 111.31f, 37.21f, 111.23f, 37.27f, 111.15f)
                curveTo(38.39f, 109.64f, 39.94f, 108.5f, 41.75f, 107.97f)
                curveTo(43.63f, 107.42f, 45.36f, 106.26f, 46.61f, 104.54f)
                curveTo(49.45f, 100.63f, 48.75f, 95.11f, 45.02f, 92.04f)
                curveTo(41.38f, 89.03f, 36.16f, 89.3f, 32.85f, 92.41f)
                close()
            }
            path(fill = SolidColor(Color(0xFF54CE8E))) {
                moveTo(51.46f, 126.77f)
                lineTo(43.62f, 120.68f)
                curveTo(43.29f, 120.42f, 43.23f, 119.94f, 43.49f, 119.6f)
                curveTo(43.74f, 119.27f, 44.23f, 119.21f, 44.56f, 119.47f)
                lineTo(52.4f, 125.56f)
                lineTo(51.46f, 126.77f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFC582D))) {
                moveTo(87f, 127f)
                curveTo(87f, 135.29f, 77.6f, 142f, 66f, 142f)
                curveTo(54.4f, 142f, 45f, 135.29f, 45f, 127f)
                curveTo(45f, 118.72f, 54.4f, 112f, 66f, 112f)
                curveTo(77.6f, 112f, 87f, 118.72f, 87f, 127f)
                close()
            }
            path(fill = SolidColor(Color(0xFFD13834))) {
                moveTo(46f, 43f)
                curveTo(46f, 45.76f, 43.76f, 48f, 41f, 48f)
                curveTo(38.24f, 48f, 36f, 45.76f, 36f, 43f)
                curveTo(36f, 40.24f, 38.24f, 38f, 41f, 38f)
                curveTo(43.76f, 38f, 46f, 40.24f, 46f, 43f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFC582D))) {
                moveTo(26f, 73f)
                curveTo(26f, 76.31f, 23.31f, 79f, 20f, 79f)
                curveTo(16.69f, 79f, 14f, 76.31f, 14f, 73f)
                curveTo(14f, 69.69f, 16.69f, 67f, 20f, 67f)
                curveTo(23.31f, 67f, 26f, 69.69f, 26f, 73f)
                close()
            }
            path(fill = SolidColor(Color(0xFF88C057))) {
                moveTo(104f, 16f)
                curveTo(104f, 18.76f, 101.76f, 21f, 99f, 21f)
                curveTo(96.24f, 21f, 94f, 18.76f, 94f, 16f)
                curveTo(94f, 13.24f, 96.24f, 11f, 99f, 11f)
                curveTo(101.76f, 11f, 104f, 13.24f, 104f, 16f)
                close()
            }
            path(fill = SolidColor(Color(0xFF7CD8A4))) {
                moveTo(10f, 128f)
                curveTo(10f, 130.76f, 7.76f, 133f, 5f, 133f)
                curveTo(2.24f, 133f, 0f, 130.76f, 0f, 128f)
                curveTo(0f, 125.24f, 2.24f, 123f, 5f, 123f)
                curveTo(7.76f, 123f, 10f, 125.24f, 10f, 128f)
                close()
            }
            path(fill = SolidColor(Color(0xFFE17858))) {
                moveTo(258f, 95f)
                curveTo(258f, 97.76f, 255.76f, 100f, 253f, 100f)
                curveTo(250.24f, 100f, 248f, 97.76f, 248f, 95f)
                curveTo(248f, 92.24f, 250.24f, 90f, 253f, 90f)
                curveTo(255.76f, 90f, 258f, 92.24f, 258f, 95f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFCDCDCD)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(137.52f, 13.95f)
                curveTo(137.28f, 17.75f, 138.44f, 22.09f, 140.98f, 25.81f)
                curveTo(144.15f, 30.46f, 146.36f, 34.46f, 146.13f, 38.9f)
                curveTo(145.91f, 43.29f, 143.34f, 47.38f, 139.34f, 52.37f)
                curveTo(138.31f, 53.67f, 136.42f, 53.88f, 135.13f, 52.84f)
                curveTo(133.83f, 51.81f, 133.62f, 49.92f, 134.66f, 48.63f)
                curveTo(138.66f, 43.62f, 140.02f, 40.87f, 140.14f, 38.6f)
                curveTo(140.26f, 36.37f, 139.22f, 33.87f, 136.02f, 29.19f)
                curveTo(132.78f, 24.45f, 131.2f, 18.79f, 131.53f, 13.58f)
                curveTo(131.86f, 8.35f, 134.16f, 3.31f, 138.96f, 0.43f)
                curveTo(140.38f, -0.42f, 142.22f, 0.04f, 143.07f, 1.46f)
                curveTo(143.93f, 2.88f, 143.46f, 4.72f, 142.04f, 5.57f)
                curveTo(139.34f, 7.2f, 137.76f, 10.18f, 137.52f, 13.95f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFCDCDCD)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(159.03f, 14.45f)
                curveTo(158.79f, 18.25f, 159.96f, 22.59f, 162.49f, 26.31f)
                curveTo(165.66f, 30.96f, 167.88f, 34.96f, 167.65f, 39.4f)
                curveTo(167.42f, 43.79f, 164.85f, 47.88f, 160.86f, 52.87f)
                curveTo(159.82f, 54.17f, 157.93f, 54.38f, 156.64f, 53.34f)
                curveTo(155.35f, 52.31f, 155.14f, 50.42f, 156.17f, 49.13f)
                curveTo(160.18f, 44.12f, 161.54f, 41.37f, 161.65f, 39.1f)
                curveTo(161.77f, 36.87f, 160.73f, 34.37f, 157.54f, 29.69f)
                curveTo(154.3f, 24.95f, 152.71f, 19.29f, 153.04f, 14.08f)
                curveTo(153.37f, 8.85f, 155.67f, 3.81f, 160.47f, 0.93f)
                curveTo(161.89f, 0.08f, 163.73f, 0.54f, 164.59f, 1.96f)
                curveTo(165.44f, 3.38f, 164.98f, 5.22f, 163.56f, 6.07f)
                curveTo(160.85f, 7.7f, 159.27f, 10.68f, 159.03f, 14.45f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFCDCDCD)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(116.03f, 14.45f)
                curveTo(115.79f, 18.25f, 116.96f, 22.59f, 119.49f, 26.31f)
                curveTo(122.66f, 30.96f, 124.88f, 34.96f, 124.65f, 39.4f)
                curveTo(124.42f, 43.79f, 121.85f, 47.88f, 117.86f, 52.87f)
                curveTo(116.82f, 54.17f, 114.93f, 54.38f, 113.64f, 53.34f)
                curveTo(112.35f, 52.31f, 112.14f, 50.42f, 113.17f, 49.13f)
                curveTo(117.18f, 44.12f, 118.54f, 41.37f, 118.65f, 39.1f)
                curveTo(118.77f, 36.87f, 117.73f, 34.37f, 114.54f, 29.69f)
                curveTo(111.3f, 24.95f, 109.71f, 19.29f, 110.04f, 14.08f)
                curveTo(110.37f, 8.85f, 112.67f, 3.81f, 117.47f, 0.93f)
                curveTo(118.89f, 0.08f, 120.73f, 0.54f, 121.59f, 1.96f)
                curveTo(122.44f, 3.38f, 121.98f, 5.22f, 120.56f, 6.07f)
                curveTo(117.85f, 7.7f, 116.27f, 10.68f, 116.03f, 14.45f)
                close()
            }
            path(fill = SolidColor(Color(0xFFE17858))) {
                moveTo(231.43f, 75.55f)
                curveTo(231.4f, 71.27f, 229.21f, 67.17f, 225.42f, 64.29f)
                lineTo(220.87f, 60.83f)
                curveTo(217.08f, 57.94f, 212.54f, 56.93f, 208.42f, 58.04f)
                curveTo(204.29f, 59.15f, 200.87f, 62.3f, 199.04f, 66.7f)
                lineTo(178.37f, 116.25f)
                curveTo(177.81f, 117.61f, 178.23f, 119.18f, 179.4f, 120.07f)
                curveTo(180.57f, 120.96f, 182.2f, 120.95f, 183.35f, 120.04f)
                lineTo(225.6f, 86.9f)
                curveTo(229.34f, 83.96f, 231.47f, 79.82f, 231.43f, 75.55f)
                close()
            }
            path(fill = SolidColor(Color(0xFFDD512A))) {
                moveTo(231.43f, 75.55f)
                curveTo(231.4f, 71.27f, 229.21f, 67.17f, 225.42f, 64.29f)
                lineTo(223.15f, 62.56f)
                lineTo(179.4f, 120.07f)
                curveTo(180.57f, 120.96f, 182.2f, 120.95f, 183.35f, 120.04f)
                lineTo(225.6f, 86.89f)
                curveTo(229.34f, 83.96f, 231.47f, 79.82f, 231.43f, 75.55f)
                close()
            }
            path(fill = SolidColor(Color(0xFFE17858))) {
                moveTo(225.18f, 82.07f)
                lineTo(217.44f, 76.18f)
                curveTo(216.01f, 75.1f, 213.99f, 75.38f, 212.91f, 76.8f)
                curveTo(211.82f, 78.22f, 212.1f, 80.25f, 213.52f, 81.33f)
                lineTo(221.26f, 87.22f)
                curveTo(222.69f, 88.3f, 224.71f, 88.03f, 225.8f, 86.6f)
                curveTo(226.88f, 85.18f, 226.6f, 83.15f, 225.18f, 82.07f)
                close()
            }
            path(fill = SolidColor(Color(0xFFDD512A))) {
                moveTo(203.25f, 83.36f)
                lineTo(198.11f, 79.45f)
                curveTo(196.69f, 78.37f, 194.66f, 78.65f, 193.58f, 80.07f)
                curveTo(192.5f, 81.49f, 192.77f, 83.52f, 194.19f, 84.6f)
                lineTo(199.33f, 88.51f)
                curveTo(199.33f, 88.51f, 199.33f, 88.51f, 199.33f, 88.51f)
                curveTo(200.75f, 89.59f, 202.78f, 89.31f, 203.86f, 87.89f)
                curveTo(204.94f, 86.47f, 204.67f, 84.44f, 203.25f, 83.36f)
                close()
            }
            path(fill = SolidColor(Color(0xFFA8DA64))) {
                moveTo(242.09f, 59.89f)
                curveTo(241.66f, 58.16f, 239.9f, 57.1f, 238.17f, 57.54f)
                lineTo(229.34f, 59.75f)
                lineTo(235.89f, 51.15f)
                curveTo(236.97f, 49.73f, 236.69f, 47.7f, 235.27f, 46.62f)
                curveTo(233.85f, 45.54f, 231.82f, 45.81f, 230.74f, 47.24f)
                lineTo(224.2f, 55.84f)
                lineTo(223.98f, 46.74f)
                curveTo(223.93f, 44.96f, 222.45f, 43.54f, 220.66f, 43.59f)
                curveTo(218.88f, 43.63f, 217.47f, 45.11f, 217.51f, 46.9f)
                lineTo(217.96f, 65.21f)
                curveTo(217.96f, 65.22f, 217.96f, 65.22f, 217.96f, 65.23f)
                curveTo(217.96f, 65.24f, 217.96f, 65.25f, 217.96f, 65.26f)
                curveTo(217.96f, 65.31f, 217.97f, 65.37f, 217.97f, 65.42f)
                curveTo(217.98f, 65.47f, 217.98f, 65.52f, 217.99f, 65.57f)
                curveTo(217.99f, 65.58f, 217.99f, 65.59f, 217.99f, 65.59f)
                curveTo(217.99f, 65.63f, 218f, 65.66f, 218.01f, 65.7f)
                curveTo(218.02f, 65.77f, 218.04f, 65.83f, 218.05f, 65.9f)
                curveTo(218.05f, 65.9f, 218.05f, 65.91f, 218.05f, 65.92f)
                curveTo(218.05f, 65.92f, 218.06f, 65.92f, 218.06f, 65.93f)
                curveTo(218.07f, 65.96f, 218.08f, 66f, 218.09f, 66.03f)
                curveTo(218.09f, 66.05f, 218.1f, 66.06f, 218.1f, 66.08f)
                curveTo(218.11f, 66.11f, 218.12f, 66.15f, 218.13f, 66.18f)
                curveTo(218.14f, 66.2f, 218.15f, 66.22f, 218.16f, 66.25f)
                curveTo(218.17f, 66.28f, 218.19f, 66.32f, 218.2f, 66.36f)
                curveTo(218.21f, 66.39f, 218.23f, 66.42f, 218.24f, 66.45f)
                curveTo(218.25f, 66.47f, 218.26f, 66.49f, 218.27f, 66.51f)
                curveTo(218.28f, 66.53f, 218.29f, 66.55f, 218.29f, 66.57f)
                curveTo(218.31f, 66.6f, 218.32f, 66.62f, 218.34f, 66.65f)
                curveTo(218.35f, 66.68f, 218.36f, 66.7f, 218.37f, 66.72f)
                curveTo(218.39f, 66.75f, 218.41f, 66.78f, 218.43f, 66.81f)
                curveTo(218.46f, 66.86f, 218.5f, 66.92f, 218.53f, 66.97f)
                curveTo(218.55f, 67f, 218.58f, 67.03f, 218.6f, 67.06f)
                curveTo(218.63f, 67.11f, 218.67f, 67.15f, 218.7f, 67.2f)
                curveTo(218.71f, 67.21f, 218.72f, 67.22f, 218.73f, 67.23f)
                curveTo(218.75f, 67.25f, 218.77f, 67.27f, 218.79f, 67.29f)
                curveTo(218.83f, 67.34f, 218.88f, 67.39f, 218.93f, 67.44f)
                curveTo(218.94f, 67.45f, 218.96f, 67.47f, 218.97f, 67.48f)
                curveTo(218.98f, 67.49f, 218.99f, 67.5f, 219f, 67.51f)
                curveTo(219.07f, 67.58f, 219.15f, 67.64f, 219.23f, 67.7f)
                curveTo(219.23f, 67.7f, 219.23f, 67.7f, 219.23f, 67.71f)
                curveTo(219.23f, 67.71f, 219.23f, 67.71f, 219.23f, 67.71f)
                curveTo(219.32f, 67.77f, 219.4f, 67.82f, 219.49f, 67.88f)
                curveTo(219.5f, 67.88f, 219.51f, 67.89f, 219.52f, 67.9f)
                curveTo(219.54f, 67.91f, 219.55f, 67.92f, 219.57f, 67.93f)
                curveTo(219.63f, 67.96f, 219.69f, 67.99f, 219.75f, 68.02f)
                curveTo(219.77f, 68.04f, 219.8f, 68.05f, 219.82f, 68.06f)
                curveTo(219.84f, 68.07f, 219.85f, 68.07f, 219.87f, 68.08f)
                curveTo(219.92f, 68.1f, 219.97f, 68.12f, 220.02f, 68.14f)
                curveTo(220.06f, 68.16f, 220.09f, 68.17f, 220.13f, 68.18f)
                curveTo(220.19f, 68.21f, 220.25f, 68.22f, 220.31f, 68.24f)
                curveTo(220.35f, 68.25f, 220.38f, 68.26f, 220.41f, 68.27f)
                curveTo(220.44f, 68.28f, 220.46f, 68.28f, 220.49f, 68.29f)
                curveTo(220.52f, 68.29f, 220.55f, 68.3f, 220.58f, 68.3f)
                curveTo(220.6f, 68.31f, 220.62f, 68.31f, 220.64f, 68.32f)
                curveTo(220.66f, 68.32f, 220.68f, 68.32f, 220.7f, 68.33f)
                curveTo(220.74f, 68.33f, 220.77f, 68.34f, 220.8f, 68.34f)
                curveTo(220.84f, 68.34f, 220.88f, 68.35f, 220.93f, 68.35f)
                curveTo(220.95f, 68.35f, 220.97f, 68.36f, 220.99f, 68.36f)
                curveTo(221.03f, 68.36f, 221.07f, 68.36f, 221.1f, 68.36f)
                curveTo(221.12f, 68.36f, 221.13f, 68.36f, 221.15f, 68.36f)
                curveTo(221.18f, 68.36f, 221.22f, 68.36f, 221.26f, 68.36f)
                curveTo(221.26f, 68.36f, 221.27f, 68.36f, 221.27f, 68.36f)
                curveTo(221.28f, 68.36f, 221.28f, 68.36f, 221.29f, 68.36f)
                curveTo(221.36f, 68.36f, 221.42f, 68.35f, 221.49f, 68.35f)
                curveTo(221.53f, 68.34f, 221.57f, 68.34f, 221.6f, 68.34f)
                curveTo(221.61f, 68.34f, 221.61f, 68.34f, 221.62f, 68.34f)
                curveTo(221.67f, 68.33f, 221.72f, 68.32f, 221.77f, 68.31f)
                curveTo(221.82f, 68.3f, 221.88f, 68.29f, 221.93f, 68.28f)
                curveTo(221.94f, 68.28f, 221.95f, 68.27f, 221.95f, 68.27f)
                curveTo(221.96f, 68.27f, 221.96f, 68.27f, 221.96f, 68.27f)
                curveTo(221.96f, 68.27f, 221.97f, 68.27f, 221.98f, 68.27f)
                lineTo(239.74f, 63.81f)
                curveTo(241.48f, 63.38f, 242.53f, 61.62f, 242.09f, 59.89f)
                close()
            }
            path(fill = SolidColor(Color(0xFF78B86D))) {
                moveTo(242.09f, 59.89f)
                curveTo(241.66f, 58.16f, 239.9f, 57.1f, 238.17f, 57.54f)
                lineTo(229.34f, 59.75f)
                lineTo(235.89f, 51.15f)
                curveTo(236.97f, 49.73f, 236.69f, 47.7f, 235.27f, 46.62f)
                lineTo(219.23f, 67.7f)
                curveTo(219.23f, 67.7f, 219.23f, 67.7f, 219.24f, 67.71f)
                curveTo(219.32f, 67.77f, 219.4f, 67.82f, 219.49f, 67.88f)
                curveTo(219.5f, 67.88f, 219.51f, 67.89f, 219.52f, 67.9f)
                curveTo(219.54f, 67.91f, 219.55f, 67.92f, 219.57f, 67.92f)
                curveTo(219.63f, 67.96f, 219.69f, 67.99f, 219.75f, 68.02f)
                curveTo(219.77f, 68.03f, 219.8f, 68.05f, 219.82f, 68.06f)
                curveTo(219.84f, 68.07f, 219.85f, 68.07f, 219.87f, 68.08f)
                curveTo(219.92f, 68.1f, 219.97f, 68.12f, 220.02f, 68.14f)
                curveTo(220.06f, 68.16f, 220.09f, 68.17f, 220.13f, 68.18f)
                curveTo(220.19f, 68.2f, 220.25f, 68.22f, 220.32f, 68.24f)
                curveTo(220.35f, 68.25f, 220.38f, 68.26f, 220.41f, 68.27f)
                curveTo(220.44f, 68.27f, 220.46f, 68.28f, 220.49f, 68.28f)
                curveTo(220.52f, 68.29f, 220.55f, 68.3f, 220.58f, 68.3f)
                curveTo(220.6f, 68.31f, 220.62f, 68.31f, 220.64f, 68.31f)
                curveTo(220.66f, 68.32f, 220.68f, 68.32f, 220.7f, 68.33f)
                curveTo(220.74f, 68.33f, 220.77f, 68.33f, 220.8f, 68.34f)
                curveTo(220.84f, 68.34f, 220.88f, 68.35f, 220.93f, 68.35f)
                curveTo(220.95f, 68.35f, 220.97f, 68.36f, 220.99f, 68.36f)
                curveTo(221.03f, 68.36f, 221.07f, 68.36f, 221.1f, 68.36f)
                curveTo(221.12f, 68.36f, 221.13f, 68.36f, 221.15f, 68.36f)
                curveTo(221.19f, 68.36f, 221.22f, 68.36f, 221.26f, 68.36f)
                curveTo(221.26f, 68.36f, 221.27f, 68.36f, 221.27f, 68.36f)
                curveTo(221.28f, 68.36f, 221.28f, 68.36f, 221.29f, 68.36f)
                curveTo(221.36f, 68.36f, 221.43f, 68.35f, 221.49f, 68.35f)
                curveTo(221.53f, 68.34f, 221.57f, 68.34f, 221.6f, 68.34f)
                curveTo(221.61f, 68.34f, 221.61f, 68.34f, 221.62f, 68.33f)
                curveTo(221.67f, 68.33f, 221.72f, 68.32f, 221.77f, 68.31f)
                curveTo(221.82f, 68.3f, 221.88f, 68.29f, 221.93f, 68.28f)
                curveTo(221.94f, 68.28f, 221.95f, 68.27f, 221.96f, 68.27f)
                curveTo(221.96f, 68.27f, 221.96f, 68.27f, 221.96f, 68.27f)
                curveTo(221.96f, 68.27f, 221.97f, 68.27f, 221.98f, 68.27f)
                lineTo(239.74f, 63.81f)
                curveTo(241.48f, 63.38f, 242.53f, 61.62f, 242.09f, 59.89f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF242C4C)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(137.04f, 96f)
                curveTo(162.17f, 96f, 182.54f, 89.28f, 182.54f, 81f)
                horizontalLineTo(186.54f)
                verticalLineTo(160f)
                horizontalLineTo(87.54f)
                verticalLineTo(81f)
                horizontalLineTo(91.54f)
                curveTo(91.54f, 89.28f, 111.91f, 96f, 137.04f, 96f)
                close()
            }
            path(
                fill = Brush.radialGradient(
                    colorStops = arrayOf(
                        0.78f to Color(0xFFFFD633),
                        1f to Color(0xFFFF7703)
                    ),
                    center = Offset(137.04f, 81f),
                    radius = 45.5f
                )
            ) {
                moveTo(182.54f, 81f)
                curveTo(182.54f, 89.28f, 162.17f, 96f, 137.04f, 96f)
                curveTo(111.91f, 96f, 91.54f, 89.28f, 91.54f, 81f)
                curveTo(91.54f, 72.72f, 111.91f, 66f, 137.04f, 66f)
                curveTo(162.17f, 66f, 182.54f, 72.72f, 182.54f, 81f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF2C3554)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(170.47f, 95.41f)
                curveTo(161.73f, 98.29f, 149.91f, 100f, 137.04f, 100f)
                curveTo(124.17f, 100f, 112.35f, 98.29f, 103.62f, 95.41f)
                curveTo(99.27f, 93.97f, 95.46f, 92.18f, 92.66f, 89.99f)
                curveTo(89.91f, 87.85f, 87.54f, 84.84f, 87.54f, 81f)
                curveTo(87.54f, 77.16f, 89.91f, 74.15f, 92.66f, 72.01f)
                curveTo(95.46f, 69.82f, 99.27f, 68.03f, 103.62f, 66.6f)
                curveTo(112.35f, 63.71f, 124.17f, 62f, 137.04f, 62f)
                curveTo(149.91f, 62f, 161.73f, 63.71f, 170.47f, 66.6f)
                curveTo(174.82f, 68.03f, 178.62f, 69.82f, 181.43f, 72.01f)
                curveTo(184.18f, 74.15f, 186.54f, 77.16f, 186.54f, 81f)
                curveTo(186.54f, 84.84f, 184.18f, 87.85f, 181.43f, 89.99f)
                curveTo(178.62f, 92.18f, 174.82f, 93.97f, 170.47f, 95.41f)
                close()
                moveTo(137.04f, 96f)
                curveTo(162.17f, 96f, 182.54f, 89.28f, 182.54f, 81f)
                curveTo(182.54f, 72.72f, 162.17f, 66f, 137.04f, 66f)
                curveTo(111.91f, 66f, 91.54f, 72.72f, 91.54f, 81f)
                curveTo(91.54f, 89.28f, 111.91f, 96f, 137.04f, 96f)
                close()
            }
            path(fill = SolidColor(Color(0xFFEFF0E1))) {
                moveTo(146.24f, 89.22f)
                curveTo(139.48f, 84.39f, 138.32f, 74.43f, 143.65f, 66.97f)
                curveTo(148.98f, 59.51f, 158.78f, 57.37f, 165.54f, 62.2f)
                curveTo(172.3f, 67.03f, 173.46f, 76.99f, 168.13f, 84.45f)
                curveTo(162.79f, 91.91f, 152.99f, 94.05f, 146.24f, 89.22f)
                close()
            }
            path(fill = SolidColor(Color(0xFFEFF0E1))) {
                moveTo(201.38f, 13.11f)
                curveTo(203.35f, 14.51f, 203.8f, 17.25f, 202.4f, 19.22f)
                lineTo(168.89f, 66.13f)
                lineTo(161.75f, 61.03f)
                lineTo(195.26f, 14.13f)
                curveTo(196.67f, 12.15f, 199.41f, 11.7f, 201.38f, 13.11f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFCFDF2))) {
                moveTo(149.4f, 85.32f)
                curveTo(144.76f, 82.01f, 144.01f, 75.09f, 147.73f, 69.88f)
                curveTo(151.45f, 64.67f, 158.24f, 63.14f, 162.88f, 66.46f)
                curveTo(167.53f, 69.78f, 168.28f, 76.69f, 164.56f, 81.9f)
                curveTo(160.84f, 87.11f, 154.05f, 88.64f, 149.4f, 85.32f)
                close()
            }
            path(
                fill = Brush.radialGradient(
                    colorStops = arrayOf(
                        0.78f to Color(0xFFFFD633),
                        1f to Color(0xFFFF7703)
                    ),
                    center = Offset(137.5f, 81f),
                    radius = 45.5f
                )
            ) {
                moveTo(183f, 81f)
                curveTo(183f, 89.28f, 162.63f, 96f, 137.5f, 96f)
                curveTo(112.37f, 96f, 92f, 89.28f, 92f, 81f)
                curveTo(92f, 72.72f, 112.37f, 66f, 137.5f, 66f)
                curveTo(162.63f, 66f, 183f, 72.72f, 183f, 81f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF0F0E40)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(196.02f, 91f)
                horizontalLineTo(186.54f)
                verticalLineTo(95.52f)
                horizontalLineTo(193.31f)
                curveTo(194.21f, 95.52f, 195.07f, 95.89f, 195.69f, 96.53f)
                curveTo(197.64f, 98.54f, 197.79f, 101.69f, 196.02f, 103.86f)
                lineTo(195.02f, 105.1f)
                curveTo(194.82f, 105.34f, 194.55f, 105.52f, 194.24f, 105.6f)
                lineTo(186.54f, 107.5f)
                verticalLineTo(112f)
                lineTo(196.12f, 110.08f)
                curveTo(197.99f, 109.71f, 199.65f, 108.62f, 200.74f, 107.05f)
                lineTo(201.43f, 106.05f)
                curveTo(204.08f, 102.21f, 203.92f, 97.1f, 201.03f, 93.43f)
                curveTo(199.82f, 91.9f, 197.97f, 91f, 196.02f, 91f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF242C4C)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(195.87f, 89f)
                horizontalLineTo(186.54f)
                verticalLineTo(92f)
                horizontalLineTo(192.88f)
                curveTo(194.21f, 92f, 195.47f, 92.61f, 196.3f, 93.66f)
                curveTo(198.24f, 96.11f, 198.38f, 99.53f, 196.67f, 102.14f)
                lineTo(196.56f, 102.31f)
                curveTo(195.55f, 103.85f, 194.01f, 104.96f, 192.24f, 105.44f)
                lineTo(186.54f, 107f)
                verticalLineTo(112f)
                lineTo(194.26f, 110.81f)
                curveTo(197.61f, 110.3f, 200.56f, 108.33f, 202.32f, 105.43f)
                curveTo(204.88f, 101.24f, 204.63f, 95.91f, 201.72f, 91.96f)
                curveTo(200.35f, 90.1f, 198.18f, 89f, 195.87f, 89f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF0F0E40)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(77.66f, 91f)
                horizontalLineTo(87.54f)
                verticalLineTo(95.52f)
                horizontalLineTo(80.52f)
                curveTo(79.64f, 95.52f, 78.8f, 95.87f, 78.18f, 96.49f)
                curveTo(76.17f, 98.51f, 76.02f, 101.72f, 77.84f, 103.91f)
                lineTo(78.84f, 105.11f)
                curveTo(79.04f, 105.35f, 79.31f, 105.52f, 79.61f, 105.59f)
                lineTo(87.54f, 107.5f)
                verticalLineTo(112f)
                lineTo(77.57f, 110.06f)
                curveTo(75.73f, 109.7f, 74.1f, 108.65f, 73.01f, 107.12f)
                lineTo(72.32f, 106.14f)
                curveTo(69.57f, 102.27f, 69.73f, 97.05f, 72.72f, 93.36f)
                curveTo(73.93f, 91.87f, 75.74f, 91f, 77.66f, 91f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF242C4C)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(78.22f, 89f)
                horizontalLineTo(87.54f)
                verticalLineTo(112f)
                lineTo(79.83f, 110.81f)
                curveTo(76.48f, 110.3f, 73.52f, 108.33f, 71.76f, 105.43f)
                curveTo(69.21f, 101.24f, 69.45f, 95.91f, 72.36f, 91.96f)
                curveTo(73.73f, 90.1f, 75.91f, 89f, 78.22f, 89f)
                close()
                moveTo(80.75f, 92f)
                horizontalLineTo(87.08f)
                verticalLineTo(107f)
                lineTo(81.38f, 105.44f)
                curveTo(79.61f, 104.96f, 78.08f, 103.85f, 77.07f, 102.31f)
                lineTo(76.96f, 102.14f)
                curveTo(75.24f, 99.53f, 75.39f, 96.11f, 77.33f, 93.66f)
                curveTo(78.15f, 92.61f, 79.41f, 92f, 80.75f, 92f)
                close()
            }
            path(fill = SolidColor(Color(0xFF9A7E3F))) {
                moveTo(36.33f, 139.89f)
                curveTo(37.63f, 136.35f, 41f, 134f, 44.77f, 134f)
                horizontalLineTo(106.5f)
                lineTo(137f, 157.68f)
                lineTo(167f, 134f)
                horizontalLineTo(230.09f)
                curveTo(233.92f, 134f, 237.34f, 136.43f, 238.59f, 140.06f)
                lineTo(261.42f, 206.19f)
                curveTo(263.66f, 212.68f, 258.84f, 219.45f, 251.97f, 219.45f)
                horizontalLineTo(161.16f)
                curveTo(159.81f, 219.45f, 158.55f, 220.13f, 157.82f, 221.26f)
                lineTo(155.3f, 225.09f)
                curveTo(145.95f, 239.33f, 124.89f, 238.81f, 116.25f, 224.13f)
                lineTo(114.66f, 221.42f)
                curveTo(113.94f, 220.2f, 112.63f, 219.45f, 111.21f, 219.45f)
                horizontalLineTo(21.34f)
                curveTo(14.39f, 219.45f, 9.55f, 212.52f, 11.96f, 205.99f)
                lineTo(36.33f, 139.89f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color(0xFFFFDF97),
                        0.96f to Color(0xFFB79C5F)
                    ),
                    start = Offset(136.5f, 132f),
                    end = Offset(136.5f, 250.5f)
                )
            ) {
                moveTo(36.3f, 137.81f)
                curveTo(37.62f, 134.31f, 40.97f, 132f, 44.71f, 132f)
                horizontalLineTo(101f)
                lineTo(137f, 158f)
                lineTo(172f, 132f)
                horizontalLineTo(230.15f)
                curveTo(233.96f, 132f, 237.35f, 134.4f, 238.63f, 137.99f)
                lineTo(261.26f, 201.65f)
                curveTo(263.57f, 208.16f, 258.74f, 215f, 251.83f, 215f)
                horizontalLineTo(161.12f)
                curveTo(159.8f, 215f, 158.55f, 215.66f, 157.81f, 216.76f)
                lineTo(155.12f, 220.74f)
                curveTo(145.72f, 234.64f, 125.08f, 234.13f, 116.39f, 219.77f)
                lineTo(114.67f, 216.93f)
                curveTo(113.94f, 215.73f, 112.64f, 215f, 111.25f, 215f)
                horizontalLineTo(21.49f)
                curveTo(14.49f, 215f, 9.66f, 208f, 12.14f, 201.45f)
                lineTo(36.3f, 137.81f)
                close()
            }
            path(fill = SolidColor(Color(0xFFBFADA1))) {
                moveTo(47.46f, 145.64f)
                curveTo(48.83f, 142.24f, 52.14f, 140f, 55.81f, 140f)
                horizontalLineTo(117f)
                lineTo(137f, 158.5f)
                lineTo(157.5f, 140f)
                horizontalLineTo(217.8f)
                curveTo(221.55f, 140f, 224.9f, 142.32f, 226.23f, 145.83f)
                lineTo(248.98f, 206.3f)
                curveTo(249.47f, 207.6f, 248.51f, 209f, 247.11f, 209f)
                horizontalLineTo(155.81f)
                horizontalLineTo(136f)
                horizontalLineTo(115.75f)
                horizontalLineTo(24.96f)
                curveTo(23.54f, 209f, 22.58f, 207.57f, 23.1f, 206.26f)
                lineTo(47.46f, 145.64f)
                close()
            }
            path(fill = SolidColor(Color(0xFFB79D66))) {
                moveTo(146f, 215.64f)
                curveTo(153f, 211.5f, 146.52f, 226.5f, 136f, 226.5f)
                curveTo(127f, 226.5f, 120f, 212f, 126f, 215.64f)
                curveTo(126f, 209.91f, 130.48f, 208f, 136f, 208f)
                curveTo(141.52f, 208f, 146f, 209.91f, 146f, 215.64f)
                close()
            }
            path(fill = SolidColor(Color(0xFFC7C1B8))) {
                moveTo(128f, 166.5f)
                lineTo(124.63f, 162.86f)
                curveTo(118.3f, 156.01f, 110.74f, 150.38f, 102.36f, 146.26f)
                curveTo(93.99f, 142.15f, 84.77f, 140f, 75.44f, 140f)
                horizontalLineTo(56.11f)
                curveTo(50.6f, 140f, 45.69f, 143.47f, 43.86f, 148.66f)
                lineTo(23.55f, 206f)
                curveTo(23.3f, 206.71f, 23.89f, 207.43f, 24.63f, 207.33f)
                lineTo(64.28f, 201.76f)
                curveTo(74.3f, 200.36f, 84.51f, 201.47f, 93.99f, 204.99f)
                lineTo(108.81f, 210.49f)
                lineTo(126f, 215.5f)
                curveTo(126f, 214.18f, 126.31f, 212.89f, 126.89f, 211.71f)
                lineTo(127f, 211.5f)
                lineTo(128f, 166.5f)
                close()
            }
            path(fill = SolidColor(Color(0xFFC7C1B8))) {
                moveTo(144f, 166.5f)
                lineTo(147.37f, 162.86f)
                curveTo(153.7f, 156.01f, 161.26f, 150.38f, 169.64f, 146.26f)
                curveTo(178.01f, 142.15f, 187.23f, 140f, 196.56f, 140f)
                horizontalLineTo(215.89f)
                curveTo(221.4f, 140f, 226.31f, 143.47f, 228.15f, 148.66f)
                lineTo(248.45f, 206f)
                curveTo(248.7f, 206.71f, 248.11f, 207.43f, 247.37f, 207.33f)
                lineTo(207.72f, 201.76f)
                curveTo(197.7f, 200.36f, 187.49f, 201.47f, 178.01f, 204.99f)
                lineTo(163.19f, 210.49f)
                lineTo(146f, 215.5f)
                curveTo(146f, 214.18f, 145.69f, 212.89f, 145.11f, 211.71f)
                lineTo(145f, 211.5f)
                lineTo(144f, 166.5f)
                close()
            }
            path(fill = SolidColor(Color(0xFFDAD4CA))) {
                moveTo(136f, 162.67f)
                lineTo(131.84f, 158.44f)
                curveTo(125.41f, 151.92f, 117.86f, 146.62f, 109.55f, 142.78f)
                lineTo(107.05f, 141.62f)
                curveTo(99.03f, 137.92f, 90.31f, 136f, 81.47f, 136f)
                horizontalLineTo(58.39f)
                curveTo(52.85f, 136f, 47.92f, 139.51f, 46.1f, 144.75f)
                lineTo(26.52f, 201.29f)
                curveTo(26.28f, 201.99f, 26.85f, 202.7f, 27.58f, 202.61f)
                lineTo(70.15f, 197.64f)
                curveTo(79.32f, 196.57f, 88.61f, 197.6f, 97.33f, 200.63f)
                lineTo(107.97f, 204.35f)
                curveTo(112.94f, 206.08f, 117.76f, 208.23f, 122.38f, 210.75f)
                lineTo(126.5f, 213f)
                curveTo(126.83f, 212.02f, 127.44f, 211.17f, 128.26f, 210.55f)
                lineTo(129f, 210f)
                lineTo(132f, 209f)
                lineTo(136f, 162.67f)
                close()
            }
            path(fill = SolidColor(Color(0xFFDAD4CA))) {
                moveTo(136f, 162.67f)
                lineTo(140.16f, 158.44f)
                curveTo(146.59f, 151.92f, 154.14f, 146.62f, 162.45f, 142.78f)
                lineTo(164.95f, 141.62f)
                curveTo(172.97f, 137.92f, 181.7f, 136f, 190.53f, 136f)
                horizontalLineTo(213.61f)
                curveTo(219.15f, 136f, 224.09f, 139.51f, 225.9f, 144.75f)
                lineTo(245.48f, 201.29f)
                curveTo(245.72f, 201.99f, 245.15f, 202.7f, 244.42f, 202.61f)
                lineTo(201.85f, 197.64f)
                curveTo(192.68f, 196.57f, 183.39f, 197.6f, 174.67f, 200.63f)
                lineTo(164.03f, 204.35f)
                curveTo(159.06f, 206.08f, 154.24f, 208.23f, 149.62f, 210.75f)
                lineTo(145.5f, 213f)
                curveTo(145.17f, 212.02f, 144.56f, 211.17f, 143.74f, 210.55f)
                lineTo(143f, 210f)
                lineTo(140f, 209f)
                lineTo(136f, 162.67f)
                close()
            }
            path(fill = SolidColor(Color(0xFFE5E7DB))) {
                moveTo(136f, 160.13f)
                lineTo(131.93f, 156.05f)
                curveTo(125.49f, 149.62f, 117.94f, 144.4f, 109.65f, 140.63f)
                lineTo(107.1f, 139.47f)
                curveTo(99.17f, 135.86f, 90.57f, 134f, 81.86f, 134f)
                horizontalLineTo(59.04f)
                curveTo(53.38f, 134f, 48.37f, 137.66f, 46.65f, 143.05f)
                lineTo(30f, 195.27f)
                lineTo(30.67f, 196.29f)
                curveTo(30.87f, 196.6f, 31.22f, 196.77f, 31.58f, 196.74f)
                lineTo(73.77f, 193.58f)
                curveTo(80.76f, 193.06f, 87.78f, 193.74f, 94.54f, 195.6f)
                lineTo(100.67f, 197.29f)
                lineTo(115.54f, 203.51f)
                lineTo(128.5f, 210.5f)
                curveTo(129.49f, 209.84f, 130.59f, 209.35f, 131.75f, 209.06f)
                lineTo(132f, 209f)
                lineTo(136f, 160.13f)
                close()
            }
            path(fill = SolidColor(Color(0xFFE5E7DB))) {
                moveTo(136f, 160.13f)
                lineTo(140.07f, 156.05f)
                curveTo(146.51f, 149.62f, 154.06f, 144.4f, 162.35f, 140.63f)
                lineTo(164.9f, 139.47f)
                curveTo(172.83f, 135.86f, 181.43f, 134f, 190.14f, 134f)
                horizontalLineTo(212.96f)
                curveTo(218.62f, 134f, 223.63f, 137.66f, 225.35f, 143.05f)
                lineTo(242f, 195.27f)
                lineTo(241.33f, 196.29f)
                curveTo(241.13f, 196.6f, 240.78f, 196.77f, 240.42f, 196.74f)
                lineTo(198.23f, 193.58f)
                curveTo(191.24f, 193.06f, 184.22f, 193.74f, 177.46f, 195.6f)
                lineTo(171.33f, 197.29f)
                lineTo(156.46f, 203.51f)
                lineTo(143.5f, 210.5f)
                curveTo(142.51f, 209.84f, 141.41f, 209.35f, 140.25f, 209.06f)
                lineTo(140f, 209f)
                lineTo(136f, 160.13f)
                close()
            }
            path(fill = SolidColor(Color(0xFFEFF1E6))) {
                moveTo(136f, 155.18f)
                lineTo(132.31f, 151.37f)
                curveTo(125.91f, 144.74f, 118.36f, 139.34f, 110.02f, 135.42f)
                lineTo(108.71f, 134.8f)
                curveTo(100.59f, 130.98f, 91.72f, 129f, 82.74f, 129f)
                horizontalLineTo(61.6f)
                curveTo(56.03f, 129f, 51.08f, 132.55f, 49.29f, 137.82f)
                lineTo(30.51f, 193.08f)
                curveTo(30.27f, 193.77f, 30.84f, 194.48f, 31.58f, 194.39f)
                lineTo(71.79f, 189.61f)
                curveTo(81.12f, 188.5f, 90.58f, 189.56f, 99.43f, 192.7f)
                lineTo(105.39f, 194.82f)
                curveTo(112.56f, 197.37f, 119.4f, 200.77f, 125.77f, 204.93f)
                lineTo(132f, 209f)
                lineTo(136f, 208.5f)
                verticalLineTo(155.18f)
                close()
            }
            path(fill = SolidColor(Color(0xFFEFF1E7))) {
                moveTo(137f, 155.18f)
                lineTo(140.69f, 151.37f)
                curveTo(147.09f, 144.74f, 154.65f, 139.34f, 162.98f, 135.42f)
                lineTo(164.29f, 134.8f)
                curveTo(172.41f, 130.98f, 181.28f, 129f, 190.26f, 129f)
                horizontalLineTo(211.4f)
                curveTo(216.97f, 129f, 221.91f, 132.55f, 223.71f, 137.82f)
                lineTo(242.49f, 193.08f)
                curveTo(242.73f, 193.77f, 242.15f, 194.48f, 241.42f, 194.39f)
                lineTo(201.21f, 189.61f)
                curveTo(191.88f, 188.5f, 182.42f, 189.56f, 173.57f, 192.7f)
                lineTo(167.61f, 194.82f)
                curveTo(160.44f, 197.37f, 153.6f, 200.77f, 147.23f, 204.93f)
                lineTo(141f, 209f)
                lineTo(137f, 208.5f)
                verticalLineTo(155.18f)
                close()
            }
            path(fill = SolidColor(Color(0xFFE9EAE2))) {
                moveTo(136f, 152.18f)
                lineTo(139.69f, 148.37f)
                curveTo(146.09f, 141.74f, 153.65f, 136.34f, 161.98f, 132.42f)
                lineTo(163.29f, 131.8f)
                curveTo(171.41f, 127.98f, 180.28f, 126f, 189.26f, 126f)
                horizontalLineTo(210.4f)
                curveTo(215.97f, 126f, 220.91f, 129.55f, 222.71f, 134.82f)
                lineTo(241.49f, 190.08f)
                curveTo(241.73f, 190.77f, 241.15f, 191.48f, 240.42f, 191.39f)
                lineTo(200.21f, 186.61f)
                curveTo(190.88f, 185.5f, 181.42f, 186.56f, 172.57f, 189.7f)
                lineTo(167.51f, 191.5f)
                curveTo(159.75f, 194.26f, 152.37f, 198.01f, 145.55f, 202.66f)
                lineTo(137f, 208.5f)
                horizontalLineTo(136f)
                verticalLineTo(152.18f)
                close()
            }
            path(fill = SolidColor(Color(0xFFE9EAE2))) {
                moveTo(136f, 152.18f)
                lineTo(132.31f, 148.37f)
                curveTo(125.91f, 141.74f, 118.36f, 136.34f, 110.02f, 132.42f)
                lineTo(108.71f, 131.8f)
                curveTo(100.59f, 127.98f, 91.72f, 126f, 82.74f, 126f)
                horizontalLineTo(61.6f)
                curveTo(56.03f, 126f, 51.08f, 129.55f, 49.29f, 134.82f)
                lineTo(30.51f, 190.08f)
                curveTo(30.27f, 190.77f, 30.84f, 191.48f, 31.58f, 191.39f)
                lineTo(71.79f, 186.61f)
                curveTo(81.12f, 185.5f, 90.58f, 186.56f, 99.43f, 189.7f)
                lineTo(104.49f, 191.5f)
                curveTo(112.25f, 194.26f, 119.64f, 198.01f, 126.44f, 202.66f)
                lineTo(135f, 208.5f)
                horizontalLineTo(136f)
                verticalLineTo(152.18f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0.43f to Color(0xFFF2F3EB),
                        1f to Color(0xFFF1F3E7)
                    ),
                    start = Offset(50f, 151f),
                    end = Offset(136f, 178.5f)
                )
            ) {
                moveTo(136f, 150.18f)
                lineTo(132.31f, 146.37f)
                curveTo(125.91f, 139.74f, 118.36f, 134.34f, 110.02f, 130.42f)
                lineTo(108.71f, 129.8f)
                curveTo(100.59f, 125.98f, 91.72f, 124f, 82.74f, 124f)
                horizontalLineTo(61.6f)
                curveTo(56.03f, 124f, 51.08f, 127.55f, 49.29f, 132.82f)
                lineTo(30.51f, 188.08f)
                curveTo(30.27f, 188.77f, 30.84f, 189.48f, 31.58f, 189.39f)
                lineTo(71.79f, 184.61f)
                curveTo(81.12f, 183.5f, 90.58f, 184.56f, 99.43f, 187.7f)
                lineTo(104.78f, 189.6f)
                curveTo(112.35f, 192.3f, 119.56f, 195.93f, 126.23f, 200.43f)
                lineTo(136f, 207f)
                verticalLineTo(150.18f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color(0xFFFAFCF0),
                        1f to Color(0xFFE5E7DB)
                    ),
                    start = Offset(203f, 139.5f),
                    end = Offset(139.5f, 187.5f)
                )
            ) {
                moveTo(136f, 150.18f)
                lineTo(139.69f, 146.37f)
                curveTo(146.09f, 139.74f, 153.65f, 134.34f, 161.98f, 130.42f)
                lineTo(163.29f, 129.8f)
                curveTo(171.41f, 125.98f, 180.28f, 124f, 189.26f, 124f)
                horizontalLineTo(210.4f)
                curveTo(215.97f, 124f, 220.91f, 127.55f, 222.71f, 132.82f)
                lineTo(241.49f, 188.08f)
                curveTo(241.73f, 188.77f, 241.15f, 189.48f, 240.42f, 189.39f)
                lineTo(200.21f, 184.61f)
                curveTo(190.88f, 183.5f, 181.42f, 184.56f, 172.57f, 187.7f)
                lineTo(167.23f, 189.6f)
                curveTo(159.65f, 192.3f, 152.44f, 195.93f, 145.77f, 200.43f)
                lineTo(136f, 207f)
                verticalLineTo(150.18f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFB9AAA3)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(86.48f, 169.03f)
                curveTo(74.41f, 168.19f, 62.07f, 169.29f, 54.42f, 170.55f)
                curveTo(53.33f, 170.73f, 52.3f, 169.99f, 52.12f, 168.9f)
                curveTo(51.94f, 167.82f, 52.68f, 166.79f, 53.76f, 166.61f)
                curveTo(61.66f, 165.3f, 74.32f, 164.18f, 86.75f, 165.04f)
                curveTo(99.06f, 165.89f, 111.71f, 168.72f, 119.03f, 175.8f)
                curveTo(119.82f, 176.57f, 119.85f, 177.83f, 119.08f, 178.63f)
                curveTo(118.31f, 179.42f, 117.04f, 179.44f, 116.25f, 178.68f)
                curveTo(110.03f, 172.66f, 98.67f, 169.87f, 86.48f, 169.03f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFB9AAA3)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(187.93f, 169.03f)
                curveTo(199.99f, 168.19f, 212.34f, 169.29f, 219.99f, 170.55f)
                curveTo(221.08f, 170.73f, 222.1f, 169.99f, 222.29f, 168.9f)
                curveTo(222.47f, 167.82f, 221.73f, 166.79f, 220.64f, 166.61f)
                curveTo(212.75f, 165.3f, 200.09f, 164.18f, 187.65f, 165.04f)
                curveTo(175.34f, 165.89f, 162.69f, 168.72f, 155.37f, 175.8f)
                curveTo(154.58f, 176.57f, 154.56f, 177.83f, 155.32f, 178.63f)
                curveTo(156.09f, 179.42f, 157.36f, 179.44f, 158.15f, 178.68f)
                curveTo(164.38f, 172.66f, 175.73f, 169.87f, 187.93f, 169.03f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFB9AAA3)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(88.68f, 157.26f)
                curveTo(76.61f, 156.43f, 64.27f, 157.52f, 56.62f, 158.79f)
                curveTo(55.53f, 158.97f, 54.5f, 158.23f, 54.32f, 157.14f)
                curveTo(54.14f, 156.05f, 54.88f, 155.02f, 55.97f, 154.84f)
                curveTo(63.86f, 153.54f, 76.52f, 152.41f, 88.95f, 153.27f)
                curveTo(101.26f, 154.13f, 113.91f, 156.96f, 121.23f, 164.04f)
                curveTo(122.03f, 164.8f, 122.05f, 166.07f, 121.28f, 166.86f)
                curveTo(120.51f, 167.66f, 119.25f, 167.68f, 118.45f, 166.91f)
                curveTo(112.23f, 160.9f, 100.87f, 158.11f, 88.68f, 157.26f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFB9AAA3)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(185.73f, 157.26f)
                curveTo(197.79f, 156.43f, 210.13f, 157.52f, 217.78f, 158.79f)
                curveTo(218.87f, 158.97f, 219.9f, 158.23f, 220.08f, 157.14f)
                curveTo(220.26f, 156.05f, 219.53f, 155.02f, 218.44f, 154.84f)
                curveTo(210.54f, 153.54f, 197.88f, 152.41f, 185.45f, 153.27f)
                curveTo(173.14f, 154.13f, 160.49f, 156.96f, 153.17f, 164.04f)
                curveTo(152.38f, 164.8f, 152.36f, 166.07f, 153.12f, 166.86f)
                curveTo(153.89f, 167.66f, 155.16f, 167.68f, 155.95f, 166.91f)
                curveTo(162.17f, 160.9f, 173.53f, 158.11f, 185.73f, 157.26f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFB9AAA3)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(90.68f, 144.26f)
                curveTo(78.61f, 143.43f, 66.27f, 144.52f, 58.62f, 145.79f)
                curveTo(57.53f, 145.97f, 56.5f, 145.23f, 56.32f, 144.14f)
                curveTo(56.14f, 143.05f, 56.88f, 142.02f, 57.97f, 141.84f)
                curveTo(65.86f, 140.54f, 78.52f, 139.41f, 90.95f, 140.27f)
                curveTo(103.26f, 141.13f, 115.91f, 143.96f, 123.23f, 151.04f)
                curveTo(124.03f, 151.8f, 124.05f, 153.07f, 123.28f, 153.86f)
                curveTo(122.51f, 154.66f, 121.25f, 154.68f, 120.45f, 153.91f)
                curveTo(114.23f, 147.9f, 102.87f, 145.11f, 90.68f, 144.26f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFB9AAA3)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(183.73f, 144.26f)
                curveTo(195.79f, 143.43f, 208.13f, 144.52f, 215.78f, 145.79f)
                curveTo(216.87f, 145.97f, 217.9f, 145.23f, 218.08f, 144.14f)
                curveTo(218.26f, 143.05f, 217.53f, 142.02f, 216.44f, 141.84f)
                curveTo(208.54f, 140.54f, 195.88f, 139.41f, 183.45f, 140.27f)
                curveTo(171.14f, 141.13f, 158.49f, 143.96f, 151.17f, 151.04f)
                curveTo(150.38f, 151.8f, 150.36f, 153.07f, 151.12f, 153.86f)
                curveTo(151.89f, 154.66f, 153.16f, 154.68f, 153.95f, 153.91f)
                curveTo(160.17f, 147.9f, 171.53f, 145.11f, 183.73f, 144.26f)
                close()
            }
        }.build()

        return _AppIcon!!
    }

@Suppress("ObjectPropertyName")
private var _AppIcon: ImageVector? = null
