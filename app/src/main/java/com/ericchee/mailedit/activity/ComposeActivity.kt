package com.ericchee.mailedit.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.mailedit.R
import com.ericchee.mailedit.model.Email
import kotlinx.android.synthetic.main.activity_compose.*

class ComposeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose)

        // title
        title = "Compose an Email"


        btnSend.setOnClickListener {
            val emailAddress = etEmail.text.toString()
            val content = etContent.text.toString()

            val email = Email(emailAddress, content)


            val resultData = Intent().apply {
                putExtra(ListEmailsActivity.EMAIL_RESULT_DATA, email)
            }
            setResult(Activity.RESULT_OK, resultData)
            // Kill this activity
            finish()
        }

    }
}
