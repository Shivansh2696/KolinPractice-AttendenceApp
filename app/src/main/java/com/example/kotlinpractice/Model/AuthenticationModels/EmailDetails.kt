package com.example.kotlinpractice.Model.AuthenticationModels

import android.net.Uri
import java.net.URL

class EmailDetails {


    private var username: String? = null
    private  var email: String? = null
    private var image: Uri? = null

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getImage(): Uri? {
        return image
    }

    fun setImage(image: Uri?) {
        this.image = image
    }
}