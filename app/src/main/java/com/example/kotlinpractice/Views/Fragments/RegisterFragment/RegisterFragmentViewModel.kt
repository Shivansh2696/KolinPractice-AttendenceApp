package com.example.kotlinpractice.Views.Fragments.RegisterFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinpractice.Model.AuthenticationModels.RegisterUser

class RegisterFragmentViewModel : ViewModel() {
        private var registerFragmentRepo : RegisterFragmentRepo = RegisterFragmentRepo()

        fun setUser(registerUser: RegisterUser){
                registerFragmentRepo.setUser(registerUser)
        }

        fun getCompleteLiveData(): LiveData<Boolean?> {
                return registerFragmentRepo.getCompleteLiveData()
        }
}