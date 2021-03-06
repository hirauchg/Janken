package com.hirauchi.janken.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hirauchi.janken.R
import com.hirauchi.janken.fragment.JankenDataFragment
import com.hirauchi.janken.ui.JankenDataActivityUI
import org.jetbrains.anko.setContentView

class JankenDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        JankenDataActivityUI().setContentView(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.data_title)

        fragmentManager.beginTransaction().replace(R.id.Container, JankenDataFragment()).commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
