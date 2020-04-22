package com.ericchee.mailedit.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ericchee.mailedit.R
import com.ericchee.mailedit.model.Email
import kotlinx.android.synthetic.main.fragment_email_detail.*

/**
 * A simple [Fragment] subclass.
 */
class EmailDetailFragment : Fragment() {

    private var email: Email? = null

    companion object {
        const val ARG_EMAIL = "arg_email"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { args ->
            val email = args.getParcelable<Email>(ARG_EMAIL)
            if (email != null) {
                this.email = email
            }
        }

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

        email?.let {
            tvFrom.text = it.from
            tvContent.text = it.content
        }
    }



}
