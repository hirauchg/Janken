package com.hirauchi.janken.ui

import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import com.hirauchi.janken.fragment.JankenDataFragment
import org.jetbrains.anko.*
import com.hirauchi.janken.R

class JankenDataFragmentUI : AnkoComponent<JankenDataFragment> {

    override fun createView(ui: AnkoContext<JankenDataFragment>) = with(ui) {
        scrollView {
            verticalLayout {
                textView(ctx.getString(R.string.data_total, 10, 5, 5)){
                    textSize = 34F
                    textColor = Color.BLACK
                    textAlignment = LinearLayout.TEXT_ALIGNMENT_CENTER
                }.lparams(width = matchParent) {
                    verticalMargin = dip(40)
                }

                val dataTitleList = arrayOf(R.string.data_highest_win_title, R.string.data_draw_title, R.string.data_win_rate_title)
                val dataUnitList = arrayOf(R.string.data_count, R.string.data_count, R.string.data_persent)

                for (i in 0..2) {
                    linearLayout {
                        textView(dataTitleList[i]){
                            textAlignment = LinearLayout.TEXT_ALIGNMENT_TEXT_END
                            textSize = 18f
                            textColor = Color.BLACK
                        }.lparams(width = 0, weight = 1f)

                        textView(R.string.data_separate){
                            textAlignment = LinearLayout.TEXT_ALIGNMENT_CENTER
                            textSize = 18f
                            textColor = Color.BLACK
                        }.lparams(width = 0, weight = 0.4f)

                        textView(ctx.getString(dataUnitList[i], 3)){
                            textSize = 18f
                            textColor = Color.BLACK
                        }.lparams(width = 0, weight = 1f)
                    }.lparams(width = matchParent) {
                        bottomMargin = dip(16)
                    }
                }

                textView(R.string.data_use_hand_title){
                    textSize = 20F
                    textColor = Color.BLACK
                    textAlignment = LinearLayout.TEXT_ALIGNMENT_CENTER
                }.lparams(width = matchParent) {
                    topMargin = dip(50)
                    bottomMargin = dip(22)
                }

                linearLayout {
                    val handImageList = arrayOf(R.drawable.rock, R.drawable.scissors, R.drawable.paper)
                    handImageList.forEach { it ->
                        verticalLayout {
                            imageView(it).lparams(height = 170) {
                                bottomMargin = dip(5)
                            }
                            textView(ctx.getString(R.string.data_count, 10)) {
                                textAlignment = LinearLayout.TEXT_ALIGNMENT_CENTER
                                textSize = 16f
                                textColor = Color.BLACK
                            }
                        }.lparams(width = 0, weight = 1f)
                    }
                }

                button(R.string.data_reset).lparams {
                    gravity = Gravity.CENTER_HORIZONTAL
                    verticalMargin = dip(20)
                }
            }
        }
    }
}