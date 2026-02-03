package com.knowledgespike.scorer.presentation.screens.scorematch

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.knowledgespike.scorer.domain.model.Ball
import com.knowledgespike.scorer.domain.model.BallState
import com.knowledgespike.scorer.domain.model.Byes
import com.knowledgespike.scorer.domain.model.Delete
import com.knowledgespike.scorer.domain.model.EmptyBall
import com.knowledgespike.scorer.domain.model.EndOver
import com.knowledgespike.scorer.domain.model.LegByes
import com.knowledgespike.scorer.domain.model.NoBalls
import com.knowledgespike.scorer.domain.model.Other
import com.knowledgespike.scorer.domain.model.Over
import com.knowledgespike.scorer.domain.model.Penalties
import com.knowledgespike.scorer.domain.model.Runs
import com.knowledgespike.scorer.domain.model.Wicket
import com.knowledgespike.scorer.domain.model.Wides
import com.knowledgespike.scorer.domain.usecase.ScorerUseCases
import com.knowledgespike.scorer.presentation.model.FacingBatter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScoreMatchViewModel @Inject constructor(
    private val useCases: ScorerUseCases
) : ViewModel() {
    //    private val _scorecardState: MutableState<List<OverDetailsState>> =
//        mutableStateOf(listOf<OverDetailsState>())
//    val scorecardState: State<List<OverDetailsState>> = _scorecardState
    private val _over = mutableStateOf(Over())
    val over: State<Over> = _over

    // todo: load from database
    val overs = mutableListOf<Over>()

    private val _facingBatter = mutableStateOf(FacingBatter.First)
    val facingBatter: State<FacingBatter> = _facingBatter

    fun addState(batter: FacingBatter, state: BallState) {
        val balls = over.value.balls.toMutableList()
        balls.add(Ball(batter.number, state))
        _over.value = Over(balls)
        if (state is EndOver) {
            // write to database
            overs.add(_over.value)
            _over.value = Over()
        }

        updateFacingBatter(state)
    }

    private fun updateFacingBatter(
        state: BallState
    ) {
        _facingBatter.value = when (state) {
            is Byes -> if (state.runs.odd) {
                swapBatters()
            } else {
                facingBatter.value
            }

            Delete -> TODO()
            EmptyBall -> facingBatter.value
            is LegByes -> if (state.runs.odd) {
                swapBatters()
            } else {
                facingBatter.value
            }
            is NoBalls -> if (state.runs.even) {
                swapBatters()
            } else {
                facingBatter.value
            }
            Other -> TODO()
            is Penalties -> facingBatter.value
            is Runs -> if (state.runs.odd) {
                swapBatters()
            } else {
                facingBatter.value
            }
            is Wicket -> facingBatter.value
            is Wides -> if (state.runs.even) {
                swapBatters()
            } else {
                facingBatter.value
            }
            EndOver -> swapBatters()

        }
    }

    private fun swapBatters(): FacingBatter = if (facingBatter.value == FacingBatter.First) {
        FacingBatter.Second
    } else {
        FacingBatter.First
    }

    fun deleteState() {
        _over.value.balls.removeAt(_over.value.balls.lastIndex)
        // todo: if the over is now empty, remove it from the list and load the previous over from the list
    }
}

inline val Int.even: Boolean
    get() = this % 2 == 0

inline val Int.odd: Boolean
    get() = !this.even
