package com.knowledgespike.scorer.type.error


sealed class ScorecardError(val message: Int)

class EmptyStringError(messageId: Int) : ScorecardError(messageId)