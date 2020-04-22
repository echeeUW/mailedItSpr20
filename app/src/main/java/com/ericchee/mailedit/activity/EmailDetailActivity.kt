package com.ericchee.mailedit.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.mailedit.R
import com.ericchee.mailedit.model.Email
import kotlinx.android.synthetic.main.activity_email_detail.*

class EmailDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_detail)

        val email = intent?.getParcelableExtra<Email>(INTENT_KEY_EMAIL)
        if (email != null) {
            tvFrom.text = email.from
            tvContent.text = email.content

            initSendEmailButton(email)
        }


    }

    private fun initSendEmailButton(email: Email) {
        btnSend.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "*/*"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(email.from))
                    putExtra(Intent.EXTRA_SUBJECT, "Dummy Subject")
                    putExtra(Intent.EXTRA_TEXT, email.content)
                }
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
        }
    }

    companion object {
        const val INTENT_KEY_EMAIL = "INTENT_KEY_EMAIL"
    }

}
