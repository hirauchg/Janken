package com.hirauchi.janken.ui

import com.hirauchi.janken.R
import com.hirauchi.janken.activity.JankenDataActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.verticalLayout

class JankenDataActivityUI : AnkoComponent<JankenDataActivity> {

    override fun createView(ui: AnkoContext<JankenDataActivity>) = with(ui) {
        verticalLayout {
            id = R.id.Container
        }
    }
}