package com.knowledgespike.scorer.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Primary80,
    onPrimary = Primary20,
    primaryContainer = Primary30,
    onPrimaryContainer = Primary90,
    inversePrimary = Primary40,

    secondary = Secondary80,
    onSecondary = Secondary20,
    secondaryContainer = Secondary30,
    onSecondaryContainer = Secondary90,

    tertiary = Tertiary80,
    onTertiary = Tertiary20,
    tertiaryContainer = Tertiary30,
    onTertiaryContainer = Tertiary90,

    error = Error80,
    onError = Error20,
    errorContainer = Error30,
    onErrorContainer = Error90,

    background = Neutral10,
    onBackground = Neutral90,

    surface = PrimaryNeutral30,
    onSurface = PrimaryNeutral80,
    inverseSurface = Neutral90,
    inverseOnSurface = Neutral10,

    surfaceVariant = PrimaryNeutral30,
    onSurfaceVariant = PrimaryNeutral80,

    outline = PrimaryNeutral80,
)

private val LightColorScheme = lightColorScheme(
    primary = Primary40,
    onPrimary = Color.White,
    primaryContainer = Primary90,
    onPrimaryContainer = Primary10,
    inversePrimary = Primary80,

    secondary = Secondary40,
    onSecondary = Color.White,
    secondaryContainer = Secondary90,
    onSecondaryContainer = Secondary10,

    tertiary = Tertiary40,
    onTertiary = Color.White,
    tertiaryContainer = Tertiary90,
    onTertiaryContainer = Tertiary10,

    error = Error40,
    onError = Color.White,
    errorContainer = Error90,
    onErrorContainer = Error10,

    background = Neutral99,
    onBackground = Neutral10,

    surface = PrimaryNeutral90,
    onSurface = PrimaryNeutral30,
    inverseSurface = Neutral20,
    inverseOnSurface = Neutral95,

    surfaceVariant = PrimaryNeutral90,
    onSurfaceVariant = PrimaryNeutral30,

    outline = PrimaryNeutral50

)

@Composable
fun ScorerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}