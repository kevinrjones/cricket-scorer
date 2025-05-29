package com.knowledgespike.scorer.presentation.screens.scorematch

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ScoreMatchScreen(
    modifier: Modifier = Modifier,
    isExpandedScreen: Boolean,
    onDone: () -> Unit
) {
    Scaffold(
    ) { padding ->

        Box(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .height(intrinsicSize = IntrinsicSize.Max)
                    .align(Alignment.BottomStart)
            ) {
                BattingDetails(modifier = Modifier)
            }
        }
    }
}

@Composable
fun BattingDetails(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row {
            BattingByOver(modifier = modifier.fillMaxWidth(0.5f), batter = "Batter 1")
            Spacer(modifier = Modifier.width(4.dp))
            BattingByOver(modifier = modifier.fillMaxWidth(), batter = "Batter 2")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text("Modifier")
        Text("Selector")
    }
}


@Composable
fun BattingByOver(modifier: Modifier = Modifier, batter: String) {
    Column(
        modifier = modifier
    ) {
        Text(batter)
        OutlinedTextField(
            value = "DDD", onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun ScoreMatchScreenPreview() {
    ScoreMatchScreen(isExpandedScreen = false, onDone = {})
}


