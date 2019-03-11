package com.hirauchi.janken.fragment

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.janken.controller.JankenDataController
import com.hirauchi.janken.ui.JankenDataFragmentUI
import org.jetbrains.anko.AnkoContext

class JankenDataFragment : Fragment() {

    lateinit var mUI: JankenDataFragmentUI
    lateinit var mContext: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mUI = JankenDataFragmentUI()
        return mUI.createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onAttach(context: Context){
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setJankenData()
    }

    fun setJankenData() {
        val jankenData = JankenDataController(mContext).getJankenData()
        if (jankenData != null) {
            mUI.setJankenData(jankenData)
        }
    }

    fun resetData() {
        JankenDataController(mContext).resetData()
        setJankenData()
    }
}