package com.ericchee.mailedit.manager

import com.ericchee.mailedit.model.Email

interface EmailReadListener {
    fun onEmailRead(email: Email)
}