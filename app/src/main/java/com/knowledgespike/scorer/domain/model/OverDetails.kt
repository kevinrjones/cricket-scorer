package com.knowledgespike.scorer.domain.model

import kotlinx.serialization.Serializable

@Serializable
sealed class BallState

@Serializable
data class Runs(val runs: Int = 0) : BallState()
@Serializable
data class Wides(val runs: Int = 0) : BallState()
@Serializable
data class NoBalls(val runs: Int = 0) : BallState()
@Serializable
data class Byes(val runs: Int = 0) : BallState()
@Serializable
data class LegByes(val runs: Int = 0) : BallState()
@Serializable
data class Penalties(val runs: Int = 0) : BallState()
@Serializable
data class Wicket(val runs: Int = 0, val batterOut: Int) : BallState()
@Serializable
object Other : BallState()
@Serializable
object Delete : BallState()
@Serializable
object EndOver : BallState()
@Serializable
object EmptyBall : BallState()

