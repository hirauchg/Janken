package com.hirauchi.janken.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hirauchi.janken.ui.MainActivityUI
import com.hirauchi.janken.fragment.MainFragment
import com.hirauchi.janken.R
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)

        fragmentManager.beginTransaction().replace(R.id.Container, MainFragment()).commit()
    }
}
