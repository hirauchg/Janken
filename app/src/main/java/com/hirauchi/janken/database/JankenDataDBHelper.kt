package com.hirauchi.janken.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class JankenDataDBHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_NAME = "db_janken"
        const val DB_VERSION = 1

        const val TABLE_JANKEN_DATA = "table_janken_data"

        const val CULM_ID = "id"
        const val CULM_WIN = "win"
        const val CULM_LOSE = "lose"
        const val CULM_DRAW = "draw"
        const val CULM_HIGHEST_WIN = "highest_win"
        const val CULM_NOW_WIN = "now_win"
        const val CULM_ROCK = "rock"
        const val CULM_SCISSORS = "scissors"
        const val CULM_PAPER = "paper"

        private var instance: JankenDataDBHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): JankenDataDBHelper {
            if (instance == null) {
                instance = JankenDataDBHelper(ctx.getApplicationContext())
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(TABLE_JANKEN_DATA, true,
                CULM_ID to INTEGER,
                CULM_WIN to INTEGER,
                CULM_LOSE to INTEGER,
                CULM_DRAW to INTEGER,
                CULM_HIGHEST_WIN to INTEGER,
                CULM_NOW_WIN to INTEGER,
                CULM_ROCK to INTEGER,
                CULM_SCISSORS to INTEGER,
                CULM_PAPER to INTEGER)

        db.insert(JankenDataDBHelper.TABLE_JANKEN_DATA,
                JankenDataDBHelper.CULM_ID to 0,
                JankenDataDBHelper.CULM_WIN to 0,
                JankenDataDBHelper.CULM_LOSE to 0,
                JankenDataDBHelper.CULM_DRAW to 0,
                JankenDataDBHelper.CULM_HIGHEST_WIN to 0,
                JankenDataDBHelper.CULM_NOW_WIN to 0,
                JankenDataDBHelper.CULM_ROCK to 0,
                JankenDataDBHelper.CULM_SCISSORS to 0,
                JankenDataDBHelper.CULM_PAPER to 0)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(TABLE_JANKEN_DATA, true)
    }
}
