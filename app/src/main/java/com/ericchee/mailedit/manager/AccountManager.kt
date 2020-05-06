package com.ericchee.mailedit.manager

class AccountManager {

    var username: String? = null
    var oauthKey: String? = null
    var firstName: String? = null
    var emailAddress :String? = null

    fun changeUsername(newUsername: String) {
        this.username = newUsername
    }

    fun sign(userName: String, password: String) {

    }

    fun signOut() {

    }
}