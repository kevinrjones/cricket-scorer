package com.knowledgespike.scorer.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Ball(val batter: Int, val ballState: BallState)

@Serializable
data class Over(val balls: MutableList<Ball> = mutableListOf())


fun Over.toBatters(): Pair<MutableList<BallState>, MutableList<BallState>> {
    val batter1 = mutableListOf<BallState>()
    val batter2 = mutableListOf<BallState>()
    balls.forEach {
        if(it.batter == 1) {
            batter1.add(it.ballState)
            batter2.add(EmptyBall)
        } else {
            batter2.add(it.ballState)
            batter1.add(EmptyBall)
        }
        if(it.ballState == Wicket) {
            if(it.batter == 1)
                batter1.clear()
            else
                batter2.clear()
        }
    }
    return Pair(batter1, batter2)
}
