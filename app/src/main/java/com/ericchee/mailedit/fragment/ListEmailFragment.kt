package com.ericchee.mailedit.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ericchee.mailedit.EmailAdapter
import com.ericchee.mailedit.MailedItApp
import com.ericchee.mailedit.R
import com.ericchee.mailedit.activity.UltimateMainActivity
import com.ericchee.mailedit.model.Email
import kotlinx.android.synthetic.main.fragment_list_emails.*


class ListEmailFragment: Fragment() {

    private lateinit var emailAdapter: EmailAdapter

    private var onEmailSelectedListener: OnEmailSelectedListener? = null

    private lateinit var listOfEmails: MutableList<Email>

    companion object {
        val TAG = ListEmailFragment::class.java.simpleName

        private const val ARG_LIST_OF_EMAILS = "ARG_LIST_OF_EMAILS"

        fun getInstance(listOfEmails: List<Email>): ListEmailFragment {
            return ListEmailFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_LIST_OF_EMAILS, ArrayList(listOfEmails))
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val mailedItApp = (context.applicationContext as MailedItApp)

        if (context is OnEmailSelectedListener) {
            onEmailSelectedListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { args ->
            with(args) {
                getParcelableArrayList<Email>(ARG_LIST_OF_EMAILS)?.let { listOfEmails ->
                    this@ListEmailFragment.listOfEmails = listOfEmails.toMutableList()
                }
            }
        }

        arguments?.getParcelableArrayList<Email>(ARG_LIST_OF_EMAILS)?.let { listOfEmails ->
            this.listOfEmails = listOfEmails
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

        emailAdapter = EmailAdapter(listOfEmails)
        rvAllEmails.adapter = emailAdapter

        emailAdapter.onEmailClicked = { email ->

            val mailApp = (context?.applicationContext as? MailedItApp)
            mailApp?.onEmailRead()

            onEmailSelectedListener?.onEmailSelected(email)
        }

        btnCompose.setOnClickListener {

//            startActivityForResult(
//                Intent(context, ComposeActivity::class.java),
//                ListEmailsActivity.COMPOSE_REQUEST_CODE
//            )
        }

//        context?.getString(R.string.app_name)
//        context?.getDrawable(R.drawable.ic_launcher_foreground)
//        context?.startActivity()
    }

    fun addNewEmail() {
        listOfEmails.add(Email("questcrew@gmail.cinm", "Season 3 ABDC Winners"))

        emailAdapter.updateList(listOfEmails)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}

interface OnEmailSelectedListener {
    fun onEmailSelected(email: Email)
}