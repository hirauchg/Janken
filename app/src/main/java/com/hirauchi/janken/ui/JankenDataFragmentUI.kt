package com.hirauchi.janken.ui

import com.hirauchi.janken.fragment.JankenDataFragment
import org.jetbrains.anko.*

class JankenDataFragmentUI : AnkoComponent<JankenDataFragment> {
    override fun createView(ui: AnkoContext<JankenDataFragment>) = with(ui) {
        verticalLayout {
            textView("JankenDataFragment")
        }
    }
}