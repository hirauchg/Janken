package com.hirauchi.janken.ui

import android.graphics.Color
import com.hirauchi.janken.fragment.MainFragment
import org.jetbrains.anko.*
import com.hirauchi.janken.R

class MainFragmentUI : AnkoComponent<MainFragment> {

    override fun createView(ui: AnkoContext<MainFragment>) = with(ui) {
        relativeLayout {
            lparams(width = matchParent, height = matchParent)

            textView("最高連勝数：5回"){
                id = R.id.HighestWinCount
                textSize = 16F
                textColor = Color.BLACK
            }.lparams {
                alignParentEnd()
                topMargin = dip(4)
                marginEnd = dip(4)
            }

            textView("現在連勝数：2回"){
                textSize = 16F
                textColor = Color.BLACK
            }.lparams {
                alignParentEnd()
                below(R.id.HighestWinCount)
                marginEnd = dip(4)
            }

            textView("じゃん"){
                textSize = 36F
                textColor = Color.BLACK
            }.lparams {
                centerHorizontally()
                topMargin = dip(70)
            }

            imageView(R.drawable.rock).lparams(width = 400, height = 400) {
                centerHorizontally()
                topMargin = dip(130)
            }

            linearLayout {
                imageView(R.drawable.rock).lparams(width = 0, height = 190, weight = 1f)
                imageView(R.drawable.scissors).lparams(width = 0, height = 190, weight = 1f)
                imageView(R.drawable.paper).lparams(width = 0, height = 190, weight = 1f)
            }.lparams(width = matchParent) {
                alignParentBottom()
                bottomMargin = dip(100)
            }

            linearLayout {
                button("データ"){
                    textSize = 20f
                }.lparams(width = 0, height = 120, weight = 1f)
                button("じゃんけん"){
                    textSize = 20f
                }.lparams(width = 0, height = 120, weight = 1f)
            }.lparams(width = matchParent) {
                alignParentBottom()
            }
        }
    }
}