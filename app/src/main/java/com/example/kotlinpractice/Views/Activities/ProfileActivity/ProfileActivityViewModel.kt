package com.example.kotlinpractice.Views.Activities.ProfileActivity

import androidx.lifecycle.ViewModel
import com.example.kotlinpractice.Model.AuthenticationModels.EmailDetails

class ProfileActivityViewModel : ViewModel() {
    private var profileActivityRepo = ProfileActivityRepo()
    private var emailDetails : EmailDetails = profileActivityRepo.getEmailDetail()

    fun getEmailDetails() : EmailDetails {
        return emailDetails
    }
}