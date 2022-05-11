package com.example.kotlinpractice.Views.Fragments.LoginFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinpractice.Model.AuthenticationModels.LoginUser

class LoginFragmentViewModel : ViewModel() {
    private val loginFragmentRepo = LoginFragmentRepo()

    fun setLoginUser(loginUser: LoginUser) {
        loginFragmentRepo.setUser(loginUser)
    }

    fun getCompleteLiveData(): LiveData<Boolean?> {
        return loginFragmentRepo.getCompleteLiveData()
    }
}