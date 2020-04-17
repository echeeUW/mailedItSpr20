package com.ericchee.mailedit

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.all_emails.*

class AllEmailsActivity : AppCompatActivity() {

    private lateinit var emailAdapter: EmailAdapter

    companion object {
        const val COMPOSE_REQUEST_CODE = 1235
        const val EMAIL_RESULT_DATA = "emailResultData"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_emails)


        emailAdapter = EmailAdapter(listOf())
        rvAllEmails.adapter = emailAdapter

        btnCompose.setOnClickListener {
            startActivityForResult(Intent(this, ComposeActivity::class.java), COMPOSE_REQUEST_CODE)
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



//class Foo {
//    public const final Int count = 1234
//
//}
