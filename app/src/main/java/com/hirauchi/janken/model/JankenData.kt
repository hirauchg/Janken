package com.hirauchi.janken.model

data class JankenData(
        val total: Int,
        val win: Int,
        val lose: Int,
        val draw: Int,
        val highestWin: Int,
        val winRate: Int,
        val rock: Int,
        val scissors: Int,
        val paper: Int
)