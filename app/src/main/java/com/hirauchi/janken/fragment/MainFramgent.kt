package com.hirauchi.janken.fragment

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.janken.ui.MainFragmentUI
import org.jetbrains.anko.AnkoContext

interface MainFragmentListener {
    fun onMoveToJankenData()
}

class MainFragment : Fragment() {

    lateinit var mUI: MainFragmentUI

    var mListener: MainFragmentListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mUI = MainFragmentUI()
        return mUI.createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onAttach(context: Context){
        super.onAttach(context)

        if (context is MainFragmentListener){
            mListener = context
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mUI.setWinCount(10, 9)
        mUI.changeAppHand(2000)
    }

    fun moveToJankenData() {
        mListener?.onMoveToJankenData()
    }
}