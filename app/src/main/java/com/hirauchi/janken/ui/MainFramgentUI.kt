package com.hirauchi.janken.ui

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.hirauchi.janken.fragment.MainFragment
import org.jetbrains.anko.*
import com.hirauchi.janken.R
import java.util.*
import android.os.Looper

class MainFragmentUI : AnkoComponent<MainFragment> {

    companion object {
        const val ROCK = 0
        const val SCISSORS = 1
        const val PAPER = 2

        const val WIN = 10
        const val LOSE = 11
        const val DRAW = 12
    }

    lateinit var mContext: Context

    lateinit var mHighestWinTextView: TextView
    lateinit var mNowWinTextView: TextView
    lateinit var mCallTextView: TextView
    lateinit var mAppHandImageView: ImageView
    lateinit var mJankenButton: Button

    lateinit var mJankenHandler: Handler
    lateinit var mJankenRunnable: Runnable

    var mIsCalling: Boolean = false

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
                var tagNumber: Int = 0
                val handImageList = arrayOf(R.drawable.rock, R.drawable.scissors, R.drawable.paper)
                handImageList.forEach { it ->
                    imageView(it) {
                        tag = tagNumber
                        tagNumber++

                        setOnTouchListener { v, event ->
                            if (!mIsCalling && !mJankenButton.isClickable) {
                                when (event.action) {
                                    MotionEvent.ACTION_DOWN -> {
                                        mJankenHandler.removeCallbacks(mJankenRunnable)
                                        mCallTextView.text = mContext.getString(R.string.call_pon)

                                        val r = Random().nextInt(3)

                                        when (r) {
                                            ROCK -> mAppHandImageView.setImageResource(R.drawable.rock)
                                            SCISSORS -> mAppHandImageView.setImageResource(R.drawable.scissors)
                                            PAPER -> mAppHandImageView.setImageResource(R.drawable.paper)
                                        }

                                        if (v.tag == r) {
                                            toast(mContext.getString(R.string.janken_draw))
                                            ui.owner.updateData(DRAW, v.tag as Int)
                                            mIsCalling = true
                                            Handler(Looper.getMainLooper()).postDelayed({
                                                val calls = arrayOf("", mContext.getString(R.string.call_ai), mContext.getString(R.string.call_kode))
                                                callJanken(calls)
                                                changeAppHand(50)
                                            }, 1000)
                                            return@setOnTouchListener true
                                        }

                                        mJankenButton.isClickable = true

                                        when (v.tag) {
                                            ROCK -> {
                                                if (r == SCISSORS) {
                                                    toast(mContext.getString(R.string.janken_win))
                                                    ui.owner.updateData(WIN, v.tag as Int)
                                                } else {
                                                    toast(mContext.getString(R.string.janken_lose))
                                                    ui.owner.updateData(LOSE, v.tag as Int)
                                                }
                                            }
                                            SCISSORS -> {
                                                if (r == ROCK) {
                                                    toast(mContext.getString(R.string.janken_lose))
                                                    ui.owner.updateData(LOSE, v.tag as Int)
                                                } else {
                                                    toast(mContext.getString(R.string.janken_win))
                                                    ui.owner.updateData(WIN, v.tag as Int)
                                                }
                                            }
                                            PAPER -> {
                                                if (r == ROCK) {
                                                    toast(mContext.getString(R.string.janken_win))
                                                    ui.owner.updateData(WIN, v.tag as Int)
                                                } else {
                                                    toast(mContext.getString(R.string.janken_lose))
                                                    ui.owner.updateData(LOSE, v.tag as Int)
                                                }
                                            }
                                        }
                                    }
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
                    setOnClickListener {
                        ui.owner.moveToJankenData()
                    }
                }.lparams(width = 0, height = 120, weight = 1f)

                mJankenButton = button(R.string.button_janken){
                    textSize = 20f
                    setOnClickListener {
                        mJankenButton.isClickable = false

                        val calls = arrayOf("", mContext.getString(R.string.call_jan), mContext.getString(R.string.call_ken))
                        callJanken(calls)

                        mJankenHandler.removeCallbacks(mJankenRunnable)
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
        mIsCalling = true
        val handler = Handler()
        val r = object : Runnable {
            var count = 0
            override fun run() {
                if (count >= calls.size) {
                    mIsCalling = false
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