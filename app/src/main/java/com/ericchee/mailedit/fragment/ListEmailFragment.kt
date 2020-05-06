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
import com.ericchee.mailedit.manager.EmailManager
import com.ericchee.mailedit.model.Email
import kotlinx.android.synthetic.main.fragment_list_emails.*


class ListEmailFragment: Fragment() {

    private lateinit var emailAdapter: EmailAdapter

    private var onEmailSelectedListener: OnEmailSelectedListener? = null


    private lateinit var emailManager: EmailManager



    private lateinit var listOfEmails: List<Email>

    companion object {
        val TAG = ListEmailFragment::class.java.simpleName

        private const val ARG_LIST_OF_EMAILS = "ARG_LIST_OF_EMAILS"

        fun getInstance(): ListEmailFragment {
            return ListEmailFragment()
        }
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)

        emailManager = (context.applicationContext as MailedItApp).emailManager

        if (context is OnEmailSelectedListener) {
            onEmailSelectedListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        this.listOfEmails = emailManager.listOfEmails

        // Same thing as ^
//        application?.listOfEmails?.let {
//            this.listOfEmails = it
//        } ?: run {
//            this.listOfEmails = listOf()
//        }


//        // Same thing as ^
//        val curList = application?.listOfEmails
//        if (curList != null) {
//            this.listOfEmails = curList
//        } else {
//            this.listOfEmails = listOf()
//        }
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

        emailManager.readEmailCount

        emailAdapter.onEmailClicked = { email ->

            val mailApp = (context?.applicationContext as? MailedItApp)
            emailManager.onEmailRead(email)

            onEmailSelectedListener?.onEmailSelected(email)
        }

        btnCompose.setOnClickListener {
            emailManager?.shuffle()
            listOfEmails = emailManager.listOfEmails ?: listOf()
            emailAdapter.notifyDataSetChanged()
        }
    }

    fun shuffle() {
        emailManager.shuffle()
        listOfEmails = emailManager.listOfEmails ?: listOf()
        emailAdapter.updateList(listOfEmails)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}

interface OnEmailSelectedListener {
    fun onEmailSelected(email: Email)
}