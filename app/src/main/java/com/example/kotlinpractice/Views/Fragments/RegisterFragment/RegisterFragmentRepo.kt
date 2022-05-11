package com.example.kotlinpractice.Views.Fragments.RegisterFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinpractice.Model.AuthenticationModels.RegisterUser
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterFragmentRepo {
    private var completeLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun setUser(registerUser : RegisterUser){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(registerUser.email,registerUser.password)
            .addOnCompleteListener { task : Task<AuthResult> ->
                if(task.isSuccessful){
                    FirebaseDatabase.getInstance().getReference("User")
                        .child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .setValue(registerUser).addOnCompleteListener {
                            if(it.isSuccessful){
                                completeLiveData.value = true
                            }
                        }
                }
            }
    }

    fun getCompleteLiveData(): LiveData<Boolean?> {
        return completeLiveData
    }
}