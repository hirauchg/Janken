package com.hirauchi.janken.fragment

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.janken.controller.JankenDataController
import com.hirauchi.janken.ui.MainFragmentUI
import org.jetbrains.anko.AnkoContext

interface MainFragmentListener {
    fun onMoveToJankenData()
}

class MainFragment : Fragment() {

    lateinit var mUI: MainFragmentUI
    lateinit var mContext: Context

    var mListener: MainFragmentListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mUI = MainFragmentUI()
        return mUI.createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onAttach(context: Context){
        super.onAttach(context)
        mContext = context

        if (context is MainFragmentListener){
            mListener = context
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUI.changeAppHand(2000)
    }

    override fun onResume() {
        super.onResume()
        setWinCount()
    }

    fun setWinCount() {
        val jankenData = JankenDataController(mContext).getJankenData()
        if (jankenData != null) {
            mUI.setWinCount(jankenData.highestWin, jankenData.nowWin)
        }
    }

    fun moveToJankenData() {
        mListener?.onMoveToJankenData()
    }

    fun updateData(result: Int, hand: Int) {
        val jankenData = JankenDataController(mContext).getJankenData()
        if (jankenData != null) {
            when (result) {
                MainFragmentUI.WIN -> {
                    jankenData.win++
                    jankenData.nowWin++
                    if (jankenData.nowWin >= jankenData.highestWin) {
                        jankenData.highestWin = jankenData.nowWin
                    }
                }
                MainFragmentUI.LOSE -> {
                    jankenData.lose++
                    jankenData.nowWin = 0
                }
                MainFragmentUI.DRAW -> jankenData.draw++
            }

            when (hand) {
                MainFragmentUI.ROCK -> jankenData.rock++
                MainFragmentUI.SCISSORS -> jankenData.scissors++
                MainFragmentUI.PAPER -> jankenData.paper++
            }

            JankenDataController(mContext).updateJankenData(jankenData)
            setWinCount()
        }
    }
}