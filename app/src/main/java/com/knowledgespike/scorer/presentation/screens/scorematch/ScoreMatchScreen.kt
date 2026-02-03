package com.knowledgespike.scorer.presentation.screens.scorematch

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.knowledgespike.scorer.domain.model.Over
import com.knowledgespike.scorer.domain.model.BallState
import com.knowledgespike.scorer.domain.model.Byes
import com.knowledgespike.scorer.domain.model.EmptyBall
import com.knowledgespike.scorer.domain.model.LegByes
import com.knowledgespike.scorer.domain.model.NoBalls
import com.knowledgespike.scorer.domain.model.Runs
import com.knowledgespike.scorer.domain.model.Wicket
import com.knowledgespike.scorer.domain.model.Wides
import com.knowledgespike.scorer.domain.model.toBatters
import com.knowledgespike.scorer.presentation.components.Notes
import com.knowledgespike.scorer.presentation.model.FacingBatter
import com.knowledgespike.scorer.presentation.screens.scorematch.composables.AddBallDetails
import com.knowledgespike.scorer.ui.characterCircle
import com.knowledgespike.scorer.ui.characterCross
import com.knowledgespike.scorer.ui.characterDot
import com.knowledgespike.scorer.ui.characterTriangleDown
import com.knowledgespike.scorer.ui.characterTriangleUp
import timber.log.Timber

@Composable
fun ScoreMatchScreen(
    modifier: Modifier = Modifier,
    isExpandedScreen: Boolean,
    onCloseScreen: () -> Unit,
    viewModel: ScoreMatchViewModel = hiltViewModel()
) {
    // todo: initalise screen with batters, bowlers etc when screen first appears
    Scaffold { padding ->
        // todo: Add a close button?
        Box(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .height(intrinsicSize = IntrinsicSize.Max)
                    .align(Alignment.BottomStart),
            ) {
                BattingDetails(
                    modifier = Modifier,
                    isExpandedScreen,
                    over = viewModel.over.value,
                    facingBatter = viewModel.facingBatter.value,
                    onDetailsEntered = { batter, state ->
                        viewModel.addState(batter, state)
                    })
            }
        }
    }
}

@Composable
fun BattingDetails(
    modifier: Modifier = Modifier,
    isExpandedScreen: Boolean,
    over: Over,
    facingBatter: FacingBatter,
    onDetailsEntered: (FacingBatter, BallState) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        val (batter1Balls, batter2Balls) = over.toBatters()

        if (!isExpandedScreen) {
            Row {
                val (width1, witd2) = if(facingBatter == FacingBatter.First) {
                    Pair(0.5f, 0.3f)
                } else {
                    Pair(0.3f, 0.5f)
                }
                BattingByOver(
                    modifier = modifier.fillMaxWidth(width1),
                    batterTitle = "Batter 1",
                    facing = facingBatter == FacingBatter.First,
                    batter1Balls,
                )
                Spacer(modifier = Modifier.width(4.dp))
                BattingByOver(
                    modifier = modifier.fillMaxWidth(witd2),
                    batterTitle = "Batter 2",
                    facing = facingBatter == FacingBatter.Second,
                    batter2Balls,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Notes(
                    modifier = Modifier
                        .fillMaxWidth(),
                    height = 96.dp,
                    balls = over.balls,
                )
            }
        } else {
            Row {
                BattingByOver(
                    modifier = modifier.fillMaxWidth(0.25f),
                    batterTitle = "Batter 1",
                    facing = facingBatter == FacingBatter.First,
                    batter1Balls,
                )
                Spacer(modifier = Modifier.width(4.dp))
                BattingByOver(
                    modifier = modifier.fillMaxWidth(0.25f),
                    batterTitle = "Batter 2",
                    facing = facingBatter == FacingBatter.Second,
                    batter2Balls,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Notes(
                    modifier = Modifier
                        .fillMaxWidth(),
                    height = 96.dp,
                    balls = over.balls,
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(4.dp))
        AddBallDetails(batter = facingBatter, onDetailsEntered = onDetailsEntered)

        Timber.i("BattingDetails displayed.")
    }
}

@Composable
fun BattingByOver(
    modifier: Modifier = Modifier,
    batterTitle: String,
    facing: Boolean,
    batterBalls: MutableList<BallState>
) {
    Column(
        modifier = modifier
    ) {
        var fontSize = 16.sp
        var height = 24.dp
        if (facing) {
            fontSize = 32.sp
            height = 48.dp
        }
        Text(batterTitle)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(4.dp))
        ) {

            val text = buildAnnotatedString {
                batterBalls.forEach {
                    appendBall(it, fontSize)
                }

            }

            Text(
                text = text,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp),
            )
        }
    }
}

private fun AnnotatedString.Builder.appendBall(
    ball: BallState,
    fontSize: TextUnit
) {
    withStyle(
        style = SpanStyle(
            fontSize = fontSize,
            fontWeight = FontWeight.Normal
        )
    ) {

        when (ball) {
            is Byes -> this.append(characterTriangleUp)
            is LegByes -> this.append(characterTriangleDown)
            is NoBalls -> this.append(characterCircle)
            is Runs -> {
                if (ball.runs > 0)
                    this.append(ball.runs.toString())
                else
                    this.append(characterDot)
            }

            is Wicket -> this.append("W")
            is Wides -> this.append(characterCross)
            else -> {}
        }
    }

    if(ball is EmptyBall) {
        withStyle(
            style = SpanStyle(
                fontSize = fontSize,
                fontWeight = FontWeight.ExtraBold,
                color = Color.LightGray
            )
        ) {
            append("_")
        }
    }

}


@Preview
@Composable
fun ScoreMatchScreenPreviewNotExpaned() {
    ScoreMatchScreen(isExpandedScreen = false, onCloseScreen = {})
}

@Preview()
@Composable
fun ScoreMatchScreenPreviewExpaned() {
    ScoreMatchScreen(isExpandedScreen = true, onCloseScreen = {})
}


