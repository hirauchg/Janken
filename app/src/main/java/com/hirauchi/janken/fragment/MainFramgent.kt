package com.hirauchi.janken.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.janken.ui.MainFragmentUI
import org.jetbrains.anko.AnkoContext

class MainFragment : Fragment() {

    lateinit var mUI: MainFragmentUI

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mUI = MainFragmentUI()
        return mUI.createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUI.setWinCount(10, 9)

        mUI.changeAppHand(2000)
    }
}