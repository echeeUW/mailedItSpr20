package com.ericchee.mailedit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.mailedit.R
import com.ericchee.mailedit.fragment.EmailDetailFragment
import com.ericchee.mailedit.fragment.ListEmailFragment
import com.ericchee.mailedit.fragment.OnEmailSelectedListener
import com.ericchee.mailedit.model.Email
import kotlinx.android.synthetic.main.activity_ultimate_main.*

class UltimateMainActivity : AppCompatActivity(), OnEmailSelectedListener {

    private lateinit var listOfEmails: List<Email>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ultimate_main)

        listOfEmails = listOf(
            Email("seahawks@gmail.com", "Go Hawks!!! SEA!! HAWKSSS!!!! Go 12s! Legion of boom"),
            Email("49ers@hotmail.com", "Let's go Niners!!! Richard Sherman interception! Ay bay bay"),
            Email("patriots@aol.com", "We like flat footballs and spy cameras")
        )
//        val email =  Email("marky@aol.com", "yooooo homieeeee")
//        val emailDetailFragment = EmailDetailFragment.getInstance(email, "jabbawockeez")

        if (supportFragmentManager.findFragmentByTag(EmailDetailFragment.TAG) == null) {
            // There is no email detail fragment
            val listFragment = ListEmailFragment.getInstance(listOfEmails)

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragContainer, listFragment, ListEmailFragment.TAG)
                .addToBackStack(ListEmailFragment.TAG)
                .commit()
        } else {
            // Email Detail Fragment already exists

        }

        btnShuffle.setOnClickListener {
            val listFragment = supportFragmentManager.findFragmentByTag(ListEmailFragment.TAG) as ListEmailFragment
            listFragment.addNewEmail()
        }

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
            emailDetailFragment = EmailDetailFragment.getInstance(email, "Eric chee")

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


