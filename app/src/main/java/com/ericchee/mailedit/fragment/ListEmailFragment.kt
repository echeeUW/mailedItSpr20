package com.ericchee.mailedit.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ericchee.mailedit.EmailAdapter
import com.ericchee.mailedit.MailedItApp
import com.ericchee.mailedit.R
import com.ericchee.mailedit.activity.ComposeActivity
import com.ericchee.mailedit.activity.EmailDetailActivity
import com.ericchee.mailedit.activity.ListEmailsActivity
import com.ericchee.mailedit.activity.UltimateMainActivity
import com.ericchee.mailedit.model.Email
import kotlinx.android.synthetic.main.fragment_list_emails.*


class ListEmailFragment: Fragment() {

    private lateinit var emailAdapter: EmailAdapter

    private var onEmailSelectedListener: OnEmailSelectedListener? = null

    private lateinit var initialEmails: List<Email>

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val mailedItApp = (context.applicationContext as MailedItApp)

        this.initialEmails = mailedItApp.listOfEmails


        if (context is OnEmailSelectedListener) {
            onEmailSelectedListener = context
        }
    }


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

            (context?.applicationContext as? MailedItApp)?.let { mailedItApp ->
                mailedItApp.onEmailRead()
            }

            onEmailSelectedListener?.onEmailSelected(email)
        }

        btnCompose.setOnClickListener {
            startActivityForResult(
                Intent(context, ComposeActivity::class.java),
                ListEmailsActivity.COMPOSE_REQUEST_CODE
            )
        }

//        context?.getString(R.string.app_name)
//        context?.getDrawable(R.drawable.ic_launcher_foreground)
//        context?.startActivity()
    }

}

interface OnEmailSelectedListener {
    fun onEmailSelected(email: Email)
}