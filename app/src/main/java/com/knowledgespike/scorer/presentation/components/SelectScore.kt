package com.knowledgespike.scorer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knowledgespike.scorer.presentation.model.Dot
import com.knowledgespike.scorer.presentation.model.Five
import com.knowledgespike.scorer.presentation.model.Four
import com.knowledgespike.scorer.presentation.model.One
import com.knowledgespike.scorer.presentation.model.Other
import com.knowledgespike.scorer.presentation.model.RunsScoredSelector
import com.knowledgespike.scorer.presentation.model.Six
import com.knowledgespike.scorer.presentation.model.Three
import com.knowledgespike.scorer.presentation.model.Two
import com.knowledgespike.scorer.presentation.model.Wicket
import com.knowledgespike.scorer.ui.characterDot

@Composable
fun SelectScore(
    modifier: Modifier = Modifier,
    onClick: (RunsScoredSelector) -> Unit,
    isNumberSelected: Boolean
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        IndividualScoreItem(
            modifier = Modifier,
            onClick = { onClick(Dot) },
            text = characterDot,
            runsScoredSelector = Dot,
            isNumberSelected = isNumberSelected
        )
        Spacer(modifier = Modifier.width(4.dp))
        IndividualScoreItem(
            onClick = { onClick(One) },
            text = "1",
            runsScoredSelector = One,
            isNumberSelected = isNumberSelected
        )
        Spacer(modifier = Modifier.width(4.dp))
        IndividualScoreItem(
            onClick = { onClick(Two) },
            text = "2",
            runsScoredSelector = Two,
            isNumberSelected = isNumberSelected
        )
        Spacer(modifier = Modifier.width(4.dp))
        IndividualScoreItem(
            onClick = { onClick(Three) },
            text = "3",
            runsScoredSelector = Three,
            isNumberSelected = isNumberSelected
        )
        Spacer(modifier = Modifier.width(4.dp))
        IndividualScoreItem(
            onClick = { onClick(Four) },
            text = "4",
            runsScoredSelector = Four,
            isNumberSelected = isNumberSelected
        )
        Spacer(modifier = Modifier.width(4.dp))
        IndividualScoreItem(
            onClick = { onClick(Five) },
            text = "5",
            runsScoredSelector = Five,
            isNumberSelected = isNumberSelected
        )
        Spacer(modifier = Modifier.width(4.dp))
        IndividualScoreItem(
            onClick = { onClick(Six) },
            text = "6",
            runsScoredSelector = Six,
            isNumberSelected = isNumberSelected
        )
        Spacer(modifier = Modifier.width(4.dp))
        IndividualScoreItem(
            onClick = { onClick(Wicket) },
            text = "W",
            runsScoredSelector = Wicket,
            isNumberSelected = isNumberSelected
        )
        Spacer(modifier = Modifier.width(4.dp))
        IndividualScoreItem(
            onClick = { onClick(Other) },
            text = "?",
            runsScoredSelector = Other,
            isNumberSelected = isNumberSelected
        )
    }
}

@Composable
fun IndividualScoreItem(
    modifier: Modifier = Modifier,
    onClick: (RunsScoredSelector) -> Unit,
    text: String,
    runsScoredSelector: RunsScoredSelector,
    isNumberSelected: Boolean
) {
    val fontColor = if (!isNumberSelected) {
        Color.Black
    } else {
        Color.LightGray
    }

    val maybeClickable = if (!isNumberSelected) {
        modifier.clickable(
            onClick = { onClick(runsScoredSelector) }
        )
    } else {
        modifier
    }

    Box(
        modifier = maybeClickable
            .width(40.dp)
            .border(1.dp, Color.Black)
            .background(color = Color.White),
    ) {
        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.Center),
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = fontColor
        )
    }
}