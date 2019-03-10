package com.hirauchi.janken.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.janken.model.JankenData
import com.hirauchi.janken.ui.JankenDataFragmentUI
import org.jetbrains.anko.AnkoContext

class JankenDataFragment : Fragment() {

    lateinit var mUI: JankenDataFragmentUI

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mUI = JankenDataFragmentUI()
        return mUI.createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jankenData = JankenData(20, 12, 8, 10, 5, 60, 9, 10, 11)
        mUI.setJankenData(jankenData)
    }
}