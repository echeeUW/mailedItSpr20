package com.ericchee.mailedit.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.mailedit.EmailAdapter
import com.ericchee.mailedit.R
import com.ericchee.mailedit.model.Email
import kotlinx.android.synthetic.main.activity_list_emails.*

class ListEmailsActivity : AppCompatActivity() {

    private lateinit var emailAdapter: EmailAdapter

    companion object {
        const val COMPOSE_REQUEST_CODE = 1235
        const val EMAIL_RESULT_DATA = "emailResultData"
    }

    private val initialEmails = mutableListOf(
        Email("seahawks@gmail.com", "Go Hawks!!! SEA!! HAWKSSS!!!! Go 12s! Legion of boom"),
        Email("49ers@hotmail.com", "Let's go Niners!!! Richard Sherman interception! Ay bay bay"),
        Email("patriots@aol.com", "We like flat footballs and spy cameras")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_emails)

        emailAdapter = EmailAdapter(initialEmails)
        rvAllEmails.adapter = emailAdapter

        emailAdapter.onEmailClicked = { email ->

            startActivity(
                Intent(this, EmailDetailActivity::class.java).apply {
                    putExtra(EmailDetailActivity.INTENT_KEY_EMAIL, email)
                }
            )
        }

        btnCompose.setOnClickListener {
            startActivityForResult(Intent(this, ComposeActivity::class.java),
                COMPOSE_REQUEST_CODE
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == COMPOSE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                // Check for data
                data?.let { resultData ->
                    // Get email
                    resultData.getParcelableExtra<Email>(EMAIL_RESULT_DATA)?.let { email ->
                        // Add email to
                        emailAdapter.addEmail(email)
                    }
                }
            }
        }
    }

}
