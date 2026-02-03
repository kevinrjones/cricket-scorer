package com.knowledgespike.scorer.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.knowledgespike.scorer.domain.model.Ball
import com.knowledgespike.scorer.domain.model.Byes
import com.knowledgespike.scorer.domain.model.LegByes
import com.knowledgespike.scorer.domain.model.NoBalls
import com.knowledgespike.scorer.domain.model.Penalties
import com.knowledgespike.scorer.domain.model.Wicket
import com.knowledgespike.scorer.domain.model.Wides

@Composable
fun Notes(
    modifier: Modifier = Modifier,
    height: Dp,
    balls: MutableList<Ball>
) {
    Box(
        modifier = modifier.height(height)
    ) {

        val fontSize = 32.sp
        val text = buildAnnotatedString {
            balls.forEach {
                generateNote(it, fontSize)
            }

        }

        Text(
            text = text,
            modifier = modifier
                .align(Alignment.CenterStart),
            fontSize = 32.sp,
        )
    }
}

private fun AnnotatedString.Builder.generateNote(
    ball: Ball,
    fontSize: TextUnit
) {
    // todo: exactly how do these look?
    withStyle(
        style = SpanStyle(
            fontSize = fontSize,
            fontWeight = FontWeight.Normal,
            color = Color.Green
        ),

    ) {
        val text = when (ball.ballState) {
            is Byes -> "B"
            is LegByes -> "LB"
            is Wides -> "W"
            is NoBalls -> "NB"
            else -> {""}
        }
        append(text)
    }

    withStyle(
        style = SpanStyle(
            fontSize = fontSize/2,
            fontWeight = FontWeight.Normal,
            color = Color.Green,
            baselineShift = BaselineShift.Superscript
        ),

    ) {
        val text = when (ball.ballState) {
            is Byes -> ball.ballState.runs
            is LegByes -> ball.ballState.runs
            is Wides -> ball.ballState.runs
            is NoBalls -> ball.ballState.runs
            else -> {""}
        }

        append(text.toString())
    }


    withStyle(
        style = SpanStyle(
            fontSize = fontSize,
            fontWeight = FontWeight.Normal,
            color = Color.Red
        ),

    ) {
        val text = when (ball.ballState) {
            is Wicket -> "W"
            else -> {""}
        }

        append(text)
    }

    withStyle(
        style = SpanStyle(
            fontSize = fontSize/2,
            fontWeight = FontWeight.Normal,
            color = Color.Red,
            baselineShift = BaselineShift.Superscript
        ),

        ) {
        val text = when (ball.ballState) {
            is Wicket -> ball.ballState.runs
            else -> {""}
        }

        append(text.toString())
    }

    // todo: pens
    withStyle(
        style = SpanStyle(
            fontSize = fontSize,
            fontWeight = FontWeight.Normal,
            color = Color.Blue
        ),

    ) {
       val text =  when (ball.ballState) {
            is Penalties -> "${ball.ballState.runs}P"
            else -> {""}
        }
        append(text)
    }

}
