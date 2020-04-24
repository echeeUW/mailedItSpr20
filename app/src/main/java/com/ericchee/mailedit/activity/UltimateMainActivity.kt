package com.ericchee.mailedit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.ericchee.mailedit.R
import com.ericchee.mailedit.fragment.EmailDetailFragment
import com.ericchee.mailedit.fragment.ListEmailFragment
import com.ericchee.mailedit.fragment.OnEmailSelectedListener
import com.ericchee.mailedit.model.Email
import kotlinx.android.synthetic.main.activity_ultimate_main.*

class UltimateMainActivity : AppCompatActivity(), OnEmailSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ultimate_main)

        val emailDetailFragment = EmailDetailFragment()
        val argumentBundle = Bundle().apply {
            val email =  Email("marky@aol.com", "yooooo homieeeee")

            putParcelable(EmailDetailFragment.ARG_EMAIL, email)
        }
        emailDetailFragment.arguments = argumentBundle

        val emailListFragment = ListEmailFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragContainer, emailListFragment)
            .commit()

        supportFragmentManager.addOnBackStackChangedListener {
            val hasBackStack = supportFragmentManager.backStackEntryCount > 0

            if (hasBackStack) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }
    }

    private fun getEmailDetailFragment() = supportFragmentManager.findFragmentByTag(EmailDetailFragment.TAG) as? EmailDetailFragment

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return super.onNavigateUp()
    }

    override fun onEmailSelected(email: Email) {
        var emailDetailFragment = getEmailDetailFragment()

        if (emailDetailFragment == null) {
            emailDetailFragment = EmailDetailFragment()
            val argumentBundle = Bundle().apply {
                putParcelable(EmailDetailFragment.ARG_EMAIL, email)
            }
            emailDetailFragment.arguments = argumentBundle

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragContainer, emailDetailFragment, EmailDetailFragment.TAG)
                .addToBackStack(EmailDetailFragment.TAG)
                .commit()
        } else {
            emailDetailFragment.updateEmail(email)
        }

    }
}


