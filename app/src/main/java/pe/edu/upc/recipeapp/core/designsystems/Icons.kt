package pe.edu.upc.recipeapp.core.designsystems

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Suppress("CheckReturnValue")
val recipes: ImageVector
    get() {
        if (_recipes != null) {
            return _recipes!!
        }
        _recipes = icon(name = "recipes") {
            moveTo(11f, 9f)
            horizontalLineTo(9f)
            verticalLineTo(2f)
            horizontalLineTo(7f)
            verticalLineTo(9f)
            horizontalLineTo(5f)
            verticalLineTo(2f)
            horizontalLineTo(3f)
            verticalLineTo(9f)
            curveTo(3f, 11.12f, 4.66f, 12.84f, 6.75f, 12.97f)
            verticalLineTo(22f)
            horizontalLineTo(9.25f)
            verticalLineTo(12.97f)
            curveTo(11.34f, 12.84f, 13f, 11.12f, 13f, 9f)
            verticalLineTo(2f)
            horizontalLineTo(11f)
            close()
            moveTo(16f, 6f)
            verticalLineTo(14f)
            horizontalLineTo(18.5f)
            verticalLineTo(22f)
            horizontalLineTo(21f)
            verticalLineTo(2f)
            curveTo(18.24f, 2f, 16f, 4.24f, 16f, 6f)
            close()
        }
        return _recipes!!
    }

private var _recipes: ImageVector? = null

@Suppress("CheckReturnValue")
val toCook: ImageVector
    get() {
        if (_toCook != null) {
            return _toCook!!
        }
        _toCook = icon(name = "toCook") {
            moveTo(7f, 3f)
            curveTo(5.9f, 3f, 5f, 3.9f, 5f, 5f)
            verticalLineTo(21f)
            lineTo(12f, 18f)
            lineTo(19f, 21f)
            verticalLineTo(5f)
            curveTo(19f, 3.9f, 18.1f, 3f, 17f, 3f)
            close()
        }
        return _toCook!!
    }

private var _toCook: ImageVector? = null

@Suppress("CheckReturnValue")
val arrowBack: ImageVector
    get() {
        if (_arrowBack != null) {
            return _arrowBack!!
        }
        _arrowBack = icon(name = "arrowBack") {
            moveTo(20f, 11f)
            horizontalLineTo(7.83f)
            lineTo(13.42f, 5.41f)
            lineTo(12f, 4f)
            lineTo(4f, 12f)
            lineTo(12f, 20f)
            lineTo(13.41f, 18.59f)
            lineTo(7.83f, 13f)
            horizontalLineTo(20f)
            close()
        }
        return _arrowBack!!
    }

private var _arrowBack: ImageVector? = null

@Suppress("CheckReturnValue")
val bookmark: ImageVector
    get() {
        if (_bookmark != null) {
            return _bookmark!!
        }
        _bookmark = icon(name = "bookmark") {
            moveTo(17f, 3f)
            horizontalLineTo(7f)
            curveTo(5.9f, 3f, 5f, 3.9f, 5f, 5f)
            verticalLineTo(21f)
            lineTo(12f, 18f)
            lineTo(19f, 21f)
            verticalLineTo(5f)
            curveTo(19f, 3.9f, 18.1f, 3f, 17f, 3f)
            close()
        }
        return _bookmark!!
    }

private var _bookmark: ImageVector? = null

@Suppress("CheckReturnValue")
val bookmarkBorder: ImageVector
    get() {
        if (_bookmarkBorder != null) {
            return _bookmarkBorder!!
        }
        _bookmarkBorder = ImageVector.Builder(
            name = "bookmarkBorder",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color.Transparent),
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 1.8f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.NonZero,
            ) {
                moveTo(7.2f, 4.6f)
                horizontalLineTo(16.8f)
                curveTo(17.46f, 4.6f, 18f, 5.14f, 18f, 5.8f)
                verticalLineTo(19.2f)
                lineTo(12f, 16.6f)
                lineTo(6f, 19.2f)
                verticalLineTo(5.8f)
                curveTo(6f, 5.14f, 6.54f, 4.6f, 7.2f, 4.6f)
                close()
            }
        }.build()
        return _bookmarkBorder!!
    }

private var _bookmarkBorder: ImageVector? = null

@Suppress("CheckReturnValue")
val delete: ImageVector
    get() {
        if (_delete != null) {
            return _delete!!
        }
        _delete = icon(name = "delete") {
            moveTo(6f, 19f)
            curveTo(6f, 20.1f, 6.9f, 21f, 8f, 21f)
            horizontalLineTo(16f)
            curveTo(17.1f, 21f, 18f, 20.1f, 18f, 19f)
            verticalLineTo(7f)
            horizontalLineTo(6f)
            close()
            moveTo(19f, 4f)
            horizontalLineTo(15.5f)
            lineTo(14.5f, 3f)
            horizontalLineTo(9.5f)
            lineTo(8.5f, 4f)
            horizontalLineTo(5f)
            verticalLineTo(6f)
            horizontalLineTo(19f)
            close()
        }
        return _delete!!
    }

private var _delete: ImageVector? = null

private fun icon(
    name: String,
    block: androidx.compose.ui.graphics.vector.PathBuilder.() -> Unit
): ImageVector {
    return ImageVector.Builder(
        name = name,
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f,
    ).apply {
        path(
            fill = SolidColor(Color.Black),
            fillAlpha = 1f,
            stroke = null,
            strokeAlpha = 1f,
            strokeLineWidth = 1f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Bevel,
            strokeLineMiter = 1f,
            pathFillType = PathFillType.NonZero,
            pathBuilder = block
        )
    }.build()
}
