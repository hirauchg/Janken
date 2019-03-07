package com.hirauchi.janken.ui

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.view.View
import android.widget.TextView
import com.hirauchi.janken.fragment.MainFragment
import org.jetbrains.anko.*
import com.hirauchi.janken.R

class MainFragmentUI : AnkoComponent<MainFragment> {

    lateinit var mContext: Context

    lateinit var mHighestWinTextView: TextView
    lateinit var mNowWinTextView: TextView
    lateinit var mCallTextView: TextView

    override fun createView(ui: AnkoContext<MainFragment>) = with(ui) {
        mContext = ctx

        relativeLayout {
            lparams(width = matchParent, height = matchParent)

            mHighestWinTextView = textView {
                id = R.id.HighestWinCount
                textSize = 16F
                textColor = Color.BLACK
            }.lparams {
                alignParentEnd()
                topMargin = dip(4)
                marginEnd = dip(4)
            }

            mNowWinTextView = textView {
                textSize = 16F
                textColor = Color.BLACK
            }.lparams {
                alignParentEnd()
                below(R.id.HighestWinCount)
                marginEnd = dip(4)
            }

            mCallTextView = textView {
                textSize = 36F
                textColor = Color.BLACK
                visibility = View.GONE
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

    fun setWinCount(highestWinCount: Int, nowWinCount: Int) {
        mHighestWinTextView.text = mContext.getString(R.string.highest_win_count, highestWinCount)
        mNowWinTextView.text = mContext.getString(R.string.now_win_count, nowWinCount)

    }

    fun callJanken(calls: Array<String>) {
        val handler = Handler()
        val r = object : Runnable {
            var count = 0
            override fun run() {
                if (count >= calls.size) {
                    return
                }
                mCallTextView.visibility = View.VISIBLE
                mCallTextView.text = calls[count]
                count++
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(r)
    }
}