package com.ericchee.mailedit

import android.app.Application
import com.ericchee.mailedit.manager.AccountManager
import com.ericchee.mailedit.manager.EmailManager

class MailedItApp: Application() {


    lateinit var emailManager: EmailManager
    lateinit var accountManager: AccountManager

    override fun onCreate() {
        super.onCreate()

        emailManager = EmailManager()

        accountManager = AccountManager()
    }

}

