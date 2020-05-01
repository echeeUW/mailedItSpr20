package com.ericchee.mailedit.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ericchee.mailedit.MailedItApp
import com.ericchee.mailedit.R
import com.ericchee.mailedit.model.Email
import kotlinx.android.synthetic.main.fragment_email_detail.*

/**
 * A simple [Fragment] subclass.
 */
class EmailDetailFragment : Fragment() {

    private var email: Email? = null
    private var currentUsername : String? = null

    companion object {
        val TAG: String = EmailDetailFragment::class.java.simpleName

        const val ARG_EMAIL = "arg_email"
        const val ARG_USERNAME = "ARG_USERNAME"

        fun getInstance(email: Email, username: String) = EmailDetailFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARG_EMAIL, email)
                        putString(ARG_USERNAME, username)
                    }
                }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(arguments!!) {
            val email = getParcelable<Email>(ARG_EMAIL)



        }

        arguments?.let { args ->
            val email = args.getParcelable<Email>(ARG_EMAIL)
            if (email != null) {
                this.email = email
            }

            args.getString(ARG_USERNAME )?.let { username ->
                currentUsername = username
            }
        }

    }

    fun updateEmail(email: Email) {
        this.email = email
        updateEmailViews()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_email_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val immutableEmail = this.email
        if (immutableEmail != null) {
            val nonNullEmail: Email = immutableEmail
        }

        email?.let {
            val nonNullEmail = it
        }

        // get Unread count
        val unreadCount = (context?.applicationContext as MailedItApp).readEmailCount
        Toast.makeText(context, "Number of emails read is $unreadCount", Toast.LENGTH_SHORT).show()

        updateEmailViews()
    }

    private fun updateEmailViews() {
        email?.let {
            tvFrom.text = it.from
            tvContent.text = it.content
        }
    }



}
