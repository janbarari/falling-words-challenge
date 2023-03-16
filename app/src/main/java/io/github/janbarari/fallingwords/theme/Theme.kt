package io.github.janbarari.fallingwords.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = BrandColor,
    primaryVariant = BrandColor,
    secondary = Color.Black
)

private val LightColorPalette = lightColors(
    primary = BrandColor,
    primaryVariant = BrandColor,
    secondary = Color.Black
)

@Composable
fun FallingWordsTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
