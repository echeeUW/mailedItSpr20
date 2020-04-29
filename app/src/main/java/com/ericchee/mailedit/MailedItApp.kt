package com.ericchee.mailedit

import android.app.Application
import android.util.Log
import com.ericchee.mailedit.model.Email

class MailedItApp: Application() {

    lateinit var listOfEmails: List<Email>
    var readEmailCount = 0

    override fun onCreate() {
        super.onCreate()

        listOfEmails = listOf(
            Email("seahawks@gmail.com", "Go Hawks!!! SEA!! HAWKSSS!!!! Go 12s! Legion of boom"),
            Email("49ers@hotmail.com", "Let's go Niners!!! Richard Sherman interception! Ay bay bay"),
            Email("patriots@aol.com", "We like flat footballs and spy cameras")
        )
    }

    fun onEmailRead() {
        readEmailCount++
    }

}