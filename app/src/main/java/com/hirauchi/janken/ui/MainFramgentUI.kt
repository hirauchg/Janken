package com.hirauchi.janken.ui

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.hirauchi.janken.fragment.MainFragment
import org.jetbrains.anko.*
import com.hirauchi.janken.R

class MainFragmentUI : AnkoComponent<MainFragment> {

    lateinit var mContext: Context

    lateinit var mHighestWinTextView: TextView
    lateinit var mNowWinTextView: TextView
    lateinit var mCallTextView: TextView
    lateinit var mAppHandImageView: ImageView

    lateinit var mJankenHandler: Handler
    lateinit var mJankenRunnable: Runnable

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

            mAppHandImageView = imageView().lparams(width = 400, height = 400) {
                centerHorizontally()
                topMargin = dip(130)
            }

            linearLayout {
                val handImageList = arrayOf(R.drawable.rock, R.drawable.scissors, R.drawable.paper)
                handImageList.forEach { it ->
                    imageView(it) {
                        // ユーザーが手を選択したら、画像の高速切り替えを止める
                        setOnTouchListener { v, event ->
                            when (event.action) {
                                MotionEvent.ACTION_DOWN -> {
                                    mJankenHandler.removeCallbacks(mJankenRunnable)
                                    mCallTextView.text = mContext.getText(R.string.call_pon)
                                }
                            }
                            return@setOnTouchListener true
                        }
                    }.lparams(width = 0, height = 190, weight = 1f)
                }
            }.lparams(width = matchParent) {
                alignParentBottom()
                bottomMargin = dip(100)
            }

            linearLayout {
                button(R.string.button_data){
                    textSize = 20f
                }.lparams(width = 0, height = 120, weight = 1f)
                button(R.string.button_janken){
                    textSize = 20f
                    setOnClickListener {
                        // 掛け声表示
                        val calls = arrayOf("", mContext.getString(R.string.call_jan), mContext.getString(R.string.call_ken))
                        callJanken(calls)

                        // 2秒間隔表示の停止
                        mJankenHandler.removeCallbacks(mJankenRunnable)
                        // アプリの手の高速切り替え
                        changeAppHand(50)
                    }
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
                handler.postDelayed(this, 700)
            }
        }
        handler.post(r)
    }

    fun changeAppHand(delayMillis: Long) {
        val hands = arrayOf(R.drawable.rock, R.drawable.scissors, R.drawable.paper)
        mJankenHandler = Handler()
        mJankenRunnable = object : Runnable {
            var count = 0
            override fun run() {
                if (count >= hands.size) {
                    count = 0
                }
                mAppHandImageView.setImageResource(hands[count])
                count++
                mJankenHandler.postDelayed(this, delayMillis)
            }
        }
        mJankenHandler.post(mJankenRunnable)
    }
}