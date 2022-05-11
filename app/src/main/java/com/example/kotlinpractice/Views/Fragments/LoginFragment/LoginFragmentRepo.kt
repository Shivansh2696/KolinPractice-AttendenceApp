package com.example.kotlinpractice.Views.Fragments.LoginFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinpractice.Model.AuthenticationModels.LoginUser
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginFragmentRepo() {
    private var completeLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun setUser(user: LoginUser){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task: Task<AuthResult?> ->
                if (task.isSuccessful) {
                    val firebaseUser = FirebaseAuth.getInstance().currentUser
                    if (firebaseUser != null) {
                        completeLiveData.value = true
                    }
                }
            }
    }


    fun getCompleteLiveData(): LiveData<Boolean?> {
        return completeLiveData
    }
}