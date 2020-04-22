package com.ericchee.mailedit.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ericchee.mailedit.EmailAdapter
import com.ericchee.mailedit.R
import com.ericchee.mailedit.activity.ComposeActivity
import com.ericchee.mailedit.activity.EmailDetailActivity
import com.ericchee.mailedit.activity.ListEmailsActivity
import com.ericchee.mailedit.model.Email
import kotlinx.android.synthetic.main.fragment_list_emails.*


class ListEmailFragment: Fragment() {

    private lateinit var emailAdapter: EmailAdapter

    private val initialEmails = listOf(
        Email("seahawks@gmail.com", "Go Hawks!!! SEA!! HAWKSSS!!!! Go 12s! Legion of boom"),
        Email("49ers@hotmail.com", "Let's go Niners!!! Richard Sherman interception! Ay bay bay"),
        Email("patriots@aol.com", "We like flat footballs and spy cameras")
    )





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return layoutInflater.inflate(R.layout.fragment_list_emails, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailAdapter = EmailAdapter(initialEmails)
        rvAllEmails.adapter = emailAdapter

        emailAdapter.onEmailClicked = { email ->

            val intent = Intent(context, EmailDetailActivity::class.java).apply {
                putExtra(EmailDetailActivity.INTENT_KEY_EMAIL, email)
            }

            startActivity(intent)
        }


        btnCompose.setOnClickListener {
            startActivityForResult(Intent(context, ComposeActivity::class.java),
                ListEmailsActivity.COMPOSE_REQUEST_CODE
            )
        }
    }
}