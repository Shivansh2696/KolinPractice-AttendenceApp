package com.example.kotlinpractice.Views.Activities.ProfileActivity

import com.example.kotlinpractice.Model.AuthenticationModels.EmailDetails
import com.google.firebase.auth.FirebaseAuth

class ProfileActivityRepo() {
    private var emailDetails = EmailDetails()

    init {
        emailDetails.setEmail(FirebaseAuth.getInstance().currentUser?.email)
        emailDetails.setImage(FirebaseAuth.getInstance().currentUser?.photoUrl)
        emailDetails.setUsername(FirebaseAuth.getInstance().currentUser?.displayName)
    }

    fun getEmailDetail() : EmailDetails {
        return emailDetails
    }

}