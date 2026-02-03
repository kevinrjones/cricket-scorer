package com.knowledgespike.scorer.presentation.model

sealed class RunsScoredSelector(val runs: Int = 0)
object Dot : RunsScoredSelector(0)
object One : RunsScoredSelector(1)
object Two : RunsScoredSelector(2)
object Three : RunsScoredSelector(3)
object Four : RunsScoredSelector(4)
object Five : RunsScoredSelector(5)
object Six : RunsScoredSelector(6)
object Wicket : RunsScoredSelector(0)
object Other : RunsScoredSelector(0)
