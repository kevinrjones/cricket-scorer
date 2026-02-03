package com.knowledgespike.scorer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import com.knowledgespike.scorer.presentation.model.RunsScoredType
import com.knowledgespike.scorer.ui.characterBackspace
import com.knowledgespike.scorer.ui.characterCircle
import com.knowledgespike.scorer.ui.characterCross
import com.knowledgespike.scorer.ui.characterPenalty
import com.knowledgespike.scorer.ui.characterTriangleDown
import com.knowledgespike.scorer.ui.characterTriangleUp


@Composable
fun SelectScoreModifier(
    modifier: Modifier = Modifier,
    onClick: (RunsScoredType) -> Unit,
    isNumberSelected: Boolean
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        IndividualModifierItem(
            modifier = Modifier,
            onClick = { onClick(RunsScoredType.Runs) },
            text = "R",
            runsScoredModifier = RunsScoredType.Runs,
            isNumberSelected = isNumberSelected
        )
        Spacer(modifier = Modifier.width(4.dp))
        IndividualModifierItem(
            onClick = { onClick(RunsScoredType.NoBall) },
            text = characterCircle,
            runsScoredModifier = RunsScoredType.NoBall,
            isNumberSelected = isNumberSelected
        )
        Spacer(modifier = Modifier.width(4.dp))
        IndividualModifierItem(
            onClick = { onClick(RunsScoredType.Wide) },
            text = characterCross,
            runsScoredModifier = RunsScoredType.Wide,
            isNumberSelected = isNumberSelected
        )
        Spacer(modifier = Modifier.width(4.dp))
        IndividualModifierItem(
            onClick = { onClick(RunsScoredType.Bye) },
            text = characterTriangleUp,
            runsScoredModifier = RunsScoredType.Bye,
            isNumberSelected = isNumberSelected
        )
        Spacer(modifier = Modifier.width(4.dp))
        IndividualModifierItem(
            onClick = { onClick(RunsScoredType.LegBye) },
            text = characterTriangleDown,
            runsScoredModifier = RunsScoredType.LegBye,
            isNumberSelected = isNumberSelected
        )
        Spacer(modifier = Modifier.width(4.dp))
        IndividualModifierItem(
            onClick = { onClick(RunsScoredType.Penalty) },
            text = characterPenalty,
            runsScoredModifier = RunsScoredType.Penalty,
            isNumberSelected = isNumberSelected
        )
        Spacer(modifier = Modifier.width(24.dp))
        IndividualModifierItem(
            onClick = { onClick(RunsScoredType.Delete) },
            text = characterBackspace,
            runsScoredModifier = RunsScoredType.Delete,
            isNumberSelected = true
        )
    }
}

@Composable
fun IndividualModifierItem(
    modifier: Modifier = Modifier,
    onClick: (RunsScoredType) -> Unit,
    text: String,
    runsScoredModifier: RunsScoredType,
    isNumberSelected: Boolean
) {

    val fontColor = if (isNumberSelected) {
        Color.Black
    } else {
        Color.LightGray
    }

    val maybeClickable = if (isNumberSelected) {
        modifier.clickable(
            onClick = { onClick(runsScoredModifier) }
        )
    } else {
        modifier
    }

    Box(
        modifier = maybeClickable
            .width(48.dp)
            .height(48.dp)
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