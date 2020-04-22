package com.ericchee.mailedit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.ericchee.mailedit.R
import com.ericchee.mailedit.fragment.EmailDetailFragment
import com.ericchee.mailedit.model.Email

class UltimateMainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ultimate_main)


        val emailDetailFragment = EmailDetailFragment()

        val argumentBundle = Bundle().apply {
            putParcelable(EmailDetailFragment.ARG_EMAIL, Email("marky@aol.com", "yooooo homieeeee"))
        }

        emailDetailFragment.arguments = argumentBundle

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragContainer, emailDetailFragment)
            .commit()
    }

}
