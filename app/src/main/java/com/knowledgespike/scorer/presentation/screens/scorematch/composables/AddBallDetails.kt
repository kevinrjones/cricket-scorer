package com.knowledgespike.scorer.presentation.screens.scorematch.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.knowledgespike.scorer.domain.model.Byes
import com.knowledgespike.scorer.domain.model.Delete
import com.knowledgespike.scorer.domain.model.LegByes
import com.knowledgespike.scorer.domain.model.NoBalls
import com.knowledgespike.scorer.domain.model.BallState
import com.knowledgespike.scorer.domain.model.Penalties
import com.knowledgespike.scorer.domain.model.Runs
import com.knowledgespike.scorer.domain.model.Wides
import com.knowledgespike.scorer.presentation.components.SelectScore
import com.knowledgespike.scorer.presentation.components.SelectScoreModifier
import com.knowledgespike.scorer.presentation.model.Dot
import com.knowledgespike.scorer.presentation.model.FacingBatter
import com.knowledgespike.scorer.presentation.model.Other
import com.knowledgespike.scorer.presentation.model.RunsScoredType
import com.knowledgespike.scorer.presentation.model.RunsScoredSelector
import com.knowledgespike.scorer.presentation.model.Wicket

@Composable
fun AddBallDetails(modifier: Modifier = Modifier, batter: FacingBatter, onDetailsEntered: (FacingBatter, BallState) -> Unit) {

    var runsScoredSelector: RunsScoredSelector? by remember { mutableStateOf(null) }

    var isNumberSelected by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        SelectScore(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                runsScoredSelector = it
                isNumberSelected = updateSelectScoreUi(it)
                if(runsScoredSelector is Dot)
                    onDetailsEntered(batter, Runs(0))
                if(runsScoredSelector is Wicket) {
                    onDetailsEntered(batter, com.knowledgespike.scorer.domain.model.Wicket(0, 1))
                    // todo: pop up getting wicket details including any runs
                }
            },
            isNumberSelected = isNumberSelected
        )
        Spacer(modifier = Modifier.height(4.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(4.dp))
        SelectScoreModifier(
            modifier = Modifier.fillMaxWidth(),
            isNumberSelected = isNumberSelected,
            onClick = {
                val state= processBallDetails(
                    runsScoredSelector!!,
                    runsScoredType = it,
                    batter = batter
                )
                isNumberSelected = false
                onDetailsEntered(batter, state)
            }
        )
    }
}

fun updateSelectScoreUi(runsScoredSelector: RunsScoredSelector): Boolean {
    return when (runsScoredSelector) {
        Dot -> false
        Other -> false
        Wicket -> false
        else -> true
    }
}

fun processBallDetails(
    runsScoredSelector: RunsScoredSelector,
    runsScoredType: RunsScoredType,
    batter: FacingBatter,
): BallState {

    val runs = runsScoredSelector.runs

    if (runsScoredSelector is Wicket)
        return com.knowledgespike.scorer.domain.model.Wicket(runs = runs, batterOut = batter.number)

    if (runsScoredSelector is Other)
        return com.knowledgespike.scorer.domain.model.Other

    return when (runsScoredType) {
        RunsScoredType.Runs -> Runs(runs)
        RunsScoredType.NoBall -> NoBalls(runs)
        RunsScoredType.Wide -> Wides(runs)
        RunsScoredType.Bye -> Byes(runs)
        RunsScoredType.LegBye -> LegByes(runs)
        RunsScoredType.Penalty -> Penalties(5)
        RunsScoredType.Delete -> Delete
    }
}

@Preview
@Composable
fun AddBallDetailsPreview(modifier: Modifier = Modifier) {
    AddBallDetails(modifier = modifier, batter = FacingBatter.First, onDetailsEntered = {a,b ->})
}


