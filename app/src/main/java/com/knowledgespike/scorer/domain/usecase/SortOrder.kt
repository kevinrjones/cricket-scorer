package com.knowledgespike.scorer.domain.usecase

sealed class SortOrder()

data object SortByTeam : SortOrder()
data object SortByOpponents : SortOrder()
data object SortByDate : SortOrder()
