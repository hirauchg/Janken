package com.hirauchi.janken.model

data class JankenData(
        val id: Int,
        var win: Int,
        var lose: Int,
        var draw: Int,
        var highestWin: Int,
        var nowWin: Int,
        var rock: Int,
        var scissors: Int,
        var paper: Int
)