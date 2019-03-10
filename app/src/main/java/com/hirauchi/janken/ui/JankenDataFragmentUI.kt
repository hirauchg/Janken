package com.hirauchi.janken.ui

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.hirauchi.janken.fragment.JankenDataFragment
import org.jetbrains.anko.*
import com.hirauchi.janken.R
import com.hirauchi.janken.model.JankenData

class JankenDataFragmentUI : AnkoComponent<JankenDataFragment> {

    lateinit var mContext: Context

    lateinit var mTotalTextView: TextView
    lateinit var mHighestWinTextView: TextView
    lateinit var mDrawTextView: TextView
    lateinit var mWinRateTextView: TextView
    lateinit var mRockCountTextView: TextView
    lateinit var mScissorsCountTextView: TextView
    lateinit var mPaperCountTextView: TextView
    lateinit var mResetButton: Button

    override fun createView(ui: AnkoContext<JankenDataFragment>) = with(ui) {
        mContext = ctx

        scrollView {
            verticalLayout {
                mTotalTextView = textView {
                    textSize = 34F
                    textColor = Color.BLACK
                    textAlignment = LinearLayout.TEXT_ALIGNMENT_CENTER
                }.lparams(width = matchParent) {
                    verticalMargin = dip(40)
                }

                val dataTitleList = arrayOf(R.string.data_highest_win_title, R.string.data_draw_title, R.string.data_win_rate_title)

                for (i in 0..2) {
                    linearLayout {
                        textView(dataTitleList[i]) {
                            textAlignment = LinearLayout.TEXT_ALIGNMENT_TEXT_END
                            textSize = 18f
                            textColor = Color.BLACK
                        }.lparams(width = 0, weight = 1f)

                        textView(R.string.data_separate) {
                            textAlignment = LinearLayout.TEXT_ALIGNMENT_CENTER
                            textSize = 18f
                            textColor = Color.BLACK
                        }.lparams(width = 0, weight = 0.4f)

                        val textView = textView {
                            textSize = 18f
                            textColor = Color.BLACK
                        }.lparams(width = 0, weight = 1f)

                        when (i) {
                            0 -> mHighestWinTextView = textView
                            1 -> mDrawTextView = textView
                            2 -> mWinRateTextView = textView
                        }
                    }.lparams(width = matchParent) {
                        bottomMargin = dip(16)
                    }
                }

                textView(R.string.data_use_hand_title) {
                    textSize = 20F
                    textColor = Color.BLACK
                    textAlignment = LinearLayout.TEXT_ALIGNMENT_CENTER
                }.lparams(width = matchParent) {
                    topMargin = dip(50)
                    bottomMargin = dip(22)
                }

                linearLayout {
                    val handImageList = arrayOf(R.drawable.rock, R.drawable.scissors, R.drawable.paper)
                    for (i in 0..2) {
                        verticalLayout {
                            imageView(handImageList[i]).lparams(height = 170) {
                                bottomMargin = dip(5)
                            }

                            val textView = textView {
                                textAlignment = LinearLayout.TEXT_ALIGNMENT_CENTER
                                textSize = 16f
                                textColor = Color.BLACK
                            }

                            when (i) {
                                0 -> mRockCountTextView = textView
                                1 -> mScissorsCountTextView = textView
                                2 -> mPaperCountTextView = textView
                            }
                        }.lparams(width = 0, weight = 1f)
                    }
                }

                mResetButton = button(R.string.data_reset).lparams {
                    gravity = Gravity.CENTER_HORIZONTAL
                    verticalMargin = dip(20)
                }
            }
        }
    }

    fun setJankenData(jankenData: JankenData) {
        mTotalTextView.text = mContext.getString(R.string.data_total,
                (jankenData.win + jankenData.lose), jankenData.win, jankenData.lose)
        mHighestWinTextView.text = mContext.getString(R.string.data_count, jankenData.highestWin)
        mDrawTextView.text = mContext.getString(R.string.data_count, jankenData.draw)
        mWinRateTextView.text = mContext.getString(R.string.data_persent, jankenData.winRate)
        mRockCountTextView.text = mContext.getString(R.string.data_count, jankenData.rock)
        mScissorsCountTextView.text = mContext.getString(R.string.data_count, jankenData.scissors)
        mPaperCountTextView.text = mContext.getString(R.string.data_count, jankenData.paper)
    }
}