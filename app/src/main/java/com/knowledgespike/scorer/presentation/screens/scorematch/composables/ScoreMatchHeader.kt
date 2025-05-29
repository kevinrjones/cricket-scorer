package com.knowledgespike.scorer.presentation.screens.scorematch.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.knowledgespike.scorer.R

@Composable
fun ScoreMatchHeader(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(128.dp)
    ) {

        TimeComposable()
        VerticalDivider()
//        Spacer(modifier = Modifier.width(8.dp))
        OversComposable()
//        Spacer(modifier = Modifier.width(8.dp))
        VerticalDivider()
    }
}

@Composable
private fun TimeComposable() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(64.dp)
    ) {

        Text(
            text = stringResource(R.string.time_label).uppercase(),
            modifier = Modifier
                .align(Alignment.Center)
                .width(10.dp),
            textAlign = TextAlign.Center
        )

    }
}

@Composable
private fun OversComposable() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(42.dp)
    ) {

        Text(
            text = stringResource(R.string.over_label).uppercase(),
            modifier = Modifier
                .align(Alignment.Center)
                .width(10.dp),
            textAlign = TextAlign.Center
        )
    }
}

private val bowlersComposableWidth = 256.dp

@Composable
private fun BowlersComposable(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .width(bowlersComposableWidth)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            ) {
                Text(
                    text = stringResource(R.string.bowlers_label).uppercase(),
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
        }
        Row(modifier = Modifier) {
            EndComposable(stringResource = R.string.end_label)
            EndComposable(stringResource = R.string.end_label)
        }
    }
}

@Composable
private fun EndComposable(modifier: Modifier = Modifier, stringResource: Int) {
    Row(
        modifier = modifier
            .width(bowlersComposableWidth / 2)
            .height(IntrinsicSize.Min)
    ) {
        TextField(
            modifier = Modifier.width(bowlersComposableWidth - 160.dp),
            value = "", onValueChange = {})
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                text = stringResource(stringResource).uppercase(),
            )
        }
    }
}


@Composable
@Preview
fun ScoreMatchHeaderPreview() {
    ScoreMatchHeader()
}

@Composable
@Preview
fun BowlersComposablePreview() {
    BowlersComposable()
}
