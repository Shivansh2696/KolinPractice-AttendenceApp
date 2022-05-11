package com.example.kotlinpractice.Views.Fragments.LogoutFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kotlinpractice.Views.Activities.LoginActivity.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class LogoutFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.viewModelStore?.clear()
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(context, LoginActivity::class.java))
        activity?.finish()
    }
}