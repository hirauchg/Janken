package com.hirauchi.janken.controller

import android.content.Context
import com.hirauchi.janken.database.JankenDataDBHelper
import com.hirauchi.janken.model.JankenData
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update

class JankenDataController(ctx: Context) {

    private val mDB = JankenDataDBHelper.getInstance(ctx)
    private val mJankenDataID: Int = 0

    // データの取得
    fun getJankenData() : JankenData? {
        var jankenData: JankenData? = null
        mDB.use {
            jankenData = select(JankenDataDBHelper.TABLE_JANKEN_DATA)
                    .whereArgs("(id = {Id})", "Id" to mJankenDataID).parseSingle(classParser<JankenData>())
        }
        return jankenData
    }

    // データの更新
    fun updateJankenData(jankenData: JankenData) {
        mDB.use {
            update(JankenDataDBHelper.TABLE_JANKEN_DATA,
                    JankenDataDBHelper.CULM_WIN to jankenData.win,
                    JankenDataDBHelper.CULM_LOSE to jankenData.lose,
                    JankenDataDBHelper.CULM_DRAW to jankenData.draw,
                    JankenDataDBHelper.CULM_HIGHEST_WIN to jankenData.highestWin,
                    JankenDataDBHelper.CULM_NOW_WIN to jankenData.nowWin,
                    JankenDataDBHelper.CULM_ROCK to jankenData.rock,
                    JankenDataDBHelper.CULM_SCISSORS to jankenData.scissors,
                    JankenDataDBHelper.CULM_PAPER to jankenData.paper)
                    .whereSimple(JankenDataDBHelper.CULM_ID + " = ?", mJankenDataID.toString()).exec()
        }
    }

    // データのリセット
    fun resetData() {
        mDB.use {
            update(JankenDataDBHelper.TABLE_JANKEN_DATA,
                    JankenDataDBHelper.CULM_WIN to 0,
                    JankenDataDBHelper.CULM_LOSE to 0,
                    JankenDataDBHelper.CULM_DRAW to 0,
                    JankenDataDBHelper.CULM_HIGHEST_WIN to 0,
                    JankenDataDBHelper.CULM_ROCK to 0,
                    JankenDataDBHelper.CULM_SCISSORS to 0,
                    JankenDataDBHelper.CULM_PAPER to 0)
                    .whereSimple(JankenDataDBHelper.CULM_ID + " = ?", mJankenDataID.toString()).exec()
        }
    }
}