package com.hirauchi.janken.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hirauchi.janken.ui.MainActivityUI
import com.hirauchi.janken.fragment.MainFragment
import com.hirauchi.janken.R
import com.hirauchi.janken.fragment.MainFragmentListener
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), MainFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)

        fragmentManager.beginTransaction().replace(R.id.Container, MainFragment()).commit()
    }

    override fun onMoveToJankenData() {
        startActivity<JankenDataActivity>()
    }
}
