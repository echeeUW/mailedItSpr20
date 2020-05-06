package com.ericchee.mailedit.manager

import com.ericchee.mailedit.model.Email

class EmailManager {

    var listOfEmails: List<Email>
    var readEmailCount = 0
    private var recentlyViewedEmail: Email? = null
    var emailReadListener: EmailReadListener? = null

    init {
        listOfEmails = listOf(
            Email("seahawks@gmail.com", "Go Hawks!!! SEA!! HAWKSSS!!!! Go 12s! Legion of boom"),
            Email("49ers@hotmail.com", "Let's go Niners!!! Richard Sherman interception! Ay bay bay"),
            Email("patriots@aol.com", "We like flat footballs and spy cameras")
        )
    }


    fun onEmailRead(email: Email) {
        recentlyViewedEmail = email
        readEmailCount++

        emailReadListener?.onEmailRead(email)
    }

    fun shuffle() {
        listOfEmails = listOfEmails.toMutableList().apply {
            shuffle()
        }.toList()
    }

    fun switchAccount() {

    }

}