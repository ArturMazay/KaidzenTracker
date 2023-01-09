package com.aistudio.kaidzentracker.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aistudio.kaidzentracker.android.theme.Primary

val fontsRegular = FontFamily(
    Font(R.font.roboto_regular, weight = FontWeight.Bold))

val fontsMedium = FontFamily(
    Font(R.font.roboto_medium, weight = FontWeight.Medium)
)


@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Color(0xFF17418C),
            primaryVariant = Color(0xFF4CBBBF),
            secondary = Color(0xFF03DAC5)
        )
    } else {
        lightColors(
            primary = Color(0xFF17418C),
            primaryVariant = Color(0xFF4CBBBF),
            secondary = Color(0xFF03DAC5)
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = fontsRegular,
            color = Primary
        ),
        body2 = TextStyle(
            fontFamily = fontsRegular,
            color = Primary,
            fontSize = 10.sp
        ),
        h1 = TextStyle(
            fontFamily = fontsMedium,
            color = Primary
        ),

        h2 = TextStyle(
            fontFamily = fontsMedium,
            fontSize = 24.sp,
            color = Primary
        ),

        h3 = TextStyle(
            fontFamily = fontsMedium,
           // fontSize = 44.sp,
            color = Primary
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
